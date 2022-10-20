package com.nisum.hackathon.model.dto;


import com.nisum.hackathon.model.contants.PersonType;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DonorReceiverDTO implements Serializable {

    private Long id;
    private String personName;
    private String address;
    private PersonType personType;
    private InventoryDTO inventoryDTO;
}
