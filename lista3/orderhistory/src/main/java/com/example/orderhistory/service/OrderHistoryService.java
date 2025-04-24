package com.example.orderhistory.service;

import com.example.orderhistory.model.OrderHistory;
import com.example.orderhistory.model.DeliveryStatus;
import com.example.orderhistory.repository.OrderHistoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderHistoryService {

    private final OrderHistoryRepository repository;

    public OrderHistoryService(OrderHistoryRepository repository) {
        this.repository = repository;
    }

    public OrderHistory create(OrderHistory order) {
        order.setStatus(DeliveryStatus.CREATED);
        return repository.save(order);
    }

    public OrderHistory updateStatus(Long id, DeliveryStatus status) {
        OrderHistory order = repository.findById(id).orElseThrow();
        order.setStatus(status);
        return repository.save(order);
    }

    public Optional<OrderHistory> get(Long id) {
        return repository.findById(id);
    }

    public List<OrderHistory> getAll() {
        return repository.findAll();
    }
}
