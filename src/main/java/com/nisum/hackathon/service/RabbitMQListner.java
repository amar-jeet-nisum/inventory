package com.nisum.hackathon.service;

import com.nisum.hackathon.model.dto.DonorReceiverCreationEvent;
import com.nisum.hackathon.model.dto.InventoryDTO;
import com.nisum.hackathon.model.entity.Inventory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQListner {

    @Autowired
    public InventoryService inventoryService;


    @RabbitListener(queues = {"${rabbitmq.queue}"})
    public void onMessage(DonorReceiverCreationEvent message) {
        System.out.println("Consuming Message - " + new String(message.toString()));

       InventoryDTO inventoryDTO = message.getMedicineInfo();
       inventoryDTO.setDonorReceiverId(message.getPersonId());
        inventoryDTO.setStatus(message.getMedicineInfo().getInventoryStatus());
        Inventory inventory = inventoryService.copyInventoryDtoToEntity(inventoryDTO);
        inventoryService.save(inventory);



    }

}
