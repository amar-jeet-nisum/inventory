package com.nisum.hackathon.service;

import com.nisum.hackathon.model.dto.InventoryDTO;
import com.nisum.hackathon.model.entity.Inventory;
import com.nisum.hackathon.repository.DonorReceiverRepository;
import com.nisum.hackathon.repository.InventoryRepository;
import com.nisum.hackathon.repository.MedicineRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class InventoryServiceImpl implements InventoryService {

    @Autowired
    private InventoryRepository inventoryRepository;

    @Autowired
    private DonorReceiverRepository donorReceiverRepository;

    @Override
    public List<InventoryDTO> getAllInventories() {
        List<Inventory> inventorys = inventoryRepository.findAll();
        return inventorys.stream().map(x -> copyInventoryEntityToDto(x)).collect(Collectors.toList());
    }

    @Override
    public void saveInventory(InventoryDTO inventoryDTO) {
        inventoryRepository.save(copyInventoryDtoToEntity(inventoryDTO));
    }

    @Override
    public InventoryDTO getInventoryById(long id) {
        Optional<Inventory> optional = inventoryRepository.findById(id);
        InventoryDTO inventoryDTO = null;
        if (optional.isPresent()) {
            inventoryDTO = copyInventoryEntityToDto(optional.get());
        } else {
            throw new RuntimeException(" Inventory not found for id :: " + id);
        }
        return inventoryDTO;
    }

    @Override
    public void deleteInventoryById(long id) {
        this.inventoryRepository.deleteById(id);
    }

    @Override
    public Page<Inventory> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection) {
        Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() :
                Sort.by(sortField).descending();

        Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
        return this.inventoryRepository.findAll(pageable);
    }


    public Inventory copyInventoryDtoToEntity(InventoryDTO inventoryDTO) {
        Inventory inventory = new Inventory();
        BeanUtils.copyProperties(inventoryDTO, inventory);
        inventory.setDonorReceiver(donorReceiverRepository.findById(inventoryDTO.getDonorReceiverId()).get());
        return inventory;
    }

    public InventoryDTO copyInventoryEntityToDto(Inventory inventory) {
        InventoryDTO inventoryDTO = new InventoryDTO();
        BeanUtils.copyProperties(inventory, inventoryDTO);
        return inventoryDTO;
    }

}
