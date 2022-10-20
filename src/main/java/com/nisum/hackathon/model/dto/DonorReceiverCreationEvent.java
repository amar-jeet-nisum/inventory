package com.nisum.hackathon.model.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DonorReceiverCreationEvent {
        private Long personId;
        private String email;
        private InventoryDTO medicineInfo;
        private byte[] prescription;

}
