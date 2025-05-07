package com.example.orderhistory.controller;

import com.example.orderhistory.model.OrderHistory;
import com.example.orderhistory.model.DeliveryStatus;
import com.example.orderhistory.service.OrderHistoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;


import java.util.List;

@RestController
@RequestMapping("/order-history")
@Tag(name = "Historia Zamówień", description = "Operacje na historii zamówień")
public class OrderHistoryController {

    private final OrderHistoryService service;

    public OrderHistoryController(OrderHistoryService service) {
        this.service = service;
    }
    // tworzenie nowego obiektu OrderHistory
    @Operation(summary = "Utwórz historię zamówienia", tags = {"Tworzenie"})
    @PostMapping
    public ResponseEntity<OrderHistory> create(@RequestBody OrderHistory order) {
        return ResponseEntity.ok(service.create(order));
    }
    //aktualizacja statusu dostawy
    @Operation(summary = "Zaktualizuj status zamówienia", tags = {"Aktualizacja"})
    @PutMapping("/{id}/status")
    public ResponseEntity<OrderHistory> updateStatus(@PathVariable Long id, @RequestParam DeliveryStatus status) {
        return ResponseEntity.ok(service.updateStatus(id, status));
    }
    //odczyt danych na podstawie ID
    @Operation(summary = "Pobierz historię po ID", tags = {"Odczyt"})
    @GetMapping("/{id}")
    public ResponseEntity<OrderHistory> get(@PathVariable Long id) {
        return service.get(id).map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    //odczyt wszystkich zapisanych danych
    @Operation(summary = "Pobierz wszystkie historie", tags = {"Odczyt"})
    @GetMapping
    public List<OrderHistory> getAll() {
        return service.getAll();
    }

    @PostMapping("/sync")
    public ResponseEntity<OrderHistory> syncOrder(@RequestBody OrderHistory order) {
    return ResponseEntity.ok(service.create(order));
    }
}
