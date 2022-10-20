package com.nisum.hackathon.service;

import com.nisum.hackathon.model.dto.InventoryDTO;
import com.nisum.hackathon.model.entity.Inventory;
import org.springframework.data.domain.Page;

import java.util.List;

public interface InventoryService {


    List<InventoryDTO> getAllInventories();

    void saveInventory(InventoryDTO inventory);

    public void save(Inventory inventory);

    InventoryDTO getInventoryById(long id);

    void deleteInventoryById(long id);

    Page<Inventory> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection);

    InventoryDTO copyInventoryEntityToDto(Inventory inventory);

    Inventory copyInventoryDtoToEntity(InventoryDTO inventoryDTO);
}
