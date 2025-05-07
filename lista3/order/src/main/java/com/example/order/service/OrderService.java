package com.example.order.service;

import com.example.order.model.Order;
import com.example.order.model.DeliveryStatus;
import com.example.order.repository.OrderRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class OrderService {

    private final OrderRepository repository;

    public OrderService(OrderRepository repository) {
        this.repository = repository;
    }

    public Order create(Order order) {
        order.setStatus(DeliveryStatus.CREATED);
        return repository.save(order);
    }

    public Order updateStatus(Long id, DeliveryStatus status) {
        Order order = repository.findById(id).orElseThrow();
        order.setStatus(status);
        Order saved = repository.save(order); 

        sendToHistory(saved); 

        return saved;
    }

    private void sendToHistory(Order order) {
        RestTemplate restTemplate = new RestTemplate();

        String historyUrl = "http://localhost:8081/order-history/sync";

        Map<String, Object> request = new HashMap<>();
        request.put("customerName", order.getCustomerName());
        request.put("deliveryAddress", order.getDeliveryAddress());
        request.put("products", String.join(", ", order.getProducts()));
        request.put("totalValue", 0); 
        request.put("status", order.getStatus().name());

        restTemplate.postForObject(historyUrl, request, Void.class);
    }

    public Optional<Order> get(Long id) {
        return repository.findById(id);
    }
}
