package com.nisum.hackathon.model.dto;

import com.nisum.hackathon.model.contants.ShippingMethod;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class InventoryDTO {
    private Long inventoryId;
    private String medicineId;
    private String medicineName;
    private String medicineFormula;
    private Integer quantity;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date expiryDate;
    private ShippingMethod shippingMethod;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date shippingDate;
    private Long donorReceiverId;
}
