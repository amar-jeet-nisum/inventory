package com.nisum.hackathon.controller;

import com.nisum.hackathon.model.dto.InventoryDTO;
import com.nisum.hackathon.model.entity.Inventory;
import com.nisum.hackathon.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class InventoryViewController {

    @Autowired
    private InventoryService inventoryService;

    // display list of inventoriess
    @GetMapping("/")
    public String viewHomePage(Model model) {
        return findPaginated(1, "medicineName", "asc", model);
    }

    @GetMapping("/showNewInventoryForm")
    public String showNewInventoryForm(Model model) {
        // create model attribute to bind form data
        Inventory inventory = new Inventory();
        model.addAttribute("inventory", inventoryService.copyInventoryEntityToDto(inventory));
        return "new_inventory";
    }

    @PostMapping("/saveInventory")
    public String saveInventory(@ModelAttribute("inventory") InventoryDTO inventory) {
        // save inventory to database
        inventoryService.saveInventory(inventory);
        return "redirect:/";
    }

    @GetMapping("/showFormForUpdate/{id}")
    public String showFormForUpdate(@PathVariable(value = "id") long id, Model model) {

        // get inventory from the service
        InventoryDTO inventory = inventoryService.getInventoryById(id);

        // set inventory as a model attribute to pre-populate the form
        model.addAttribute("inventory", inventory);
        return "update_inventory";
    }

    @GetMapping("/deleteInventory/{id}")
    public String deleteInventory(@PathVariable(value = "id") long id) {

        // call delete inventory method
        this.inventoryService.deleteInventoryById(id);
        return "redirect:/";
    }


    @GetMapping("/page/{pageNo}")
    public String findPaginated(@PathVariable(value = "pageNo") int pageNo,
                                @RequestParam("sortField") String sortField,
                                @RequestParam("sortDir") String sortDir,
                                Model model) {
        int pageSize = 10;

        Page<Inventory> page = inventoryService.findPaginated(pageNo, pageSize, sortField, sortDir);
        List<Inventory> listInventories = page.getContent();

        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());

        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");

        model.addAttribute("listInventories", listInventories.stream().map(x->inventoryService.copyInventoryEntityToDto(x)).collect(Collectors.toList()));
        return "index";
    }

}
