package com.example.orderhistory.controller;

import com.example.orderhistory.model.OrderHistory;
import com.example.orderhistory.model.DeliveryStatus;
import com.example.orderhistory.service.OrderHistoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order-history")
public class OrderHistoryController {

    private final OrderHistoryService service;

    public OrderHistoryController(OrderHistoryService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<OrderHistory> create(@RequestBody OrderHistory order) {
        return ResponseEntity.ok(service.create(order));
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<OrderHistory> updateStatus(@PathVariable Long id, @RequestParam DeliveryStatus status) {
        return ResponseEntity.ok(service.updateStatus(id, status));
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderHistory> get(@PathVariable Long id) {
        return service.get(id).map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public List<OrderHistory> getAll() {
        return service.getAll();
    }

    @PostMapping("/sync")
    public ResponseEntity<OrderHistory> syncOrder(@RequestBody OrderHistory order) {
    return ResponseEntity.ok(service.create(order));
    }
}
