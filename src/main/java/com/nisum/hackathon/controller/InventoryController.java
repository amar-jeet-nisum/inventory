package com.nisum.hackathon.controller;

import com.nisum.hackathon.model.entity.Inventory;
import com.nisum.hackathon.repository.InventoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class InventoryController {

    @Autowired
    private InventoryRepository inventoryRepository;

    @GetMapping("/inventories")
    public List<Inventory> findAll() {
        return inventoryRepository.findAll();
    }

    @GetMapping("/inventories/{inventoryId}")
    public Optional<Inventory> getInventory(@PathVariable Long inventoryId) {
        Optional<Inventory> theInventory = inventoryRepository.findById(inventoryId);
        if (theInventory == null) {
            throw new RuntimeException("Inventory Id not found - " + inventoryId);
        }
        return theInventory;
    }

    @PostMapping("/inventories")
    public Inventory addInventory(@RequestBody Inventory theInventory) {
        inventoryRepository.save(theInventory);
        return theInventory;
    }

    @PutMapping("/inventories")
    public Inventory updateInventory(@RequestBody Inventory theInventory) {
        inventoryRepository.save(theInventory);
        return theInventory;
    }

    @DeleteMapping("/inventories/{inventoryId}")
    public String deleteInventory(@PathVariable Long inventoryId) {
        Optional<Inventory> theInventory = inventoryRepository.findById(inventoryId);
        if (theInventory == null) {
            throw new RuntimeException("Inventory Id not found - " + inventoryId);
        }
        inventoryRepository.deleteById(inventoryId);
        return "Deleted Inventory id - " + inventoryId;
    }
}