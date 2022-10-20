package com.nisum.hackathon.model.entity;

import com.nisum.hackathon.model.contants.ShippingMethod;
import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "inventory")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Inventory {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;
    private String medicineName;
    private String medicineFormula;
    private Integer quantity;
    private Date expiryDate;
    private ShippingMethod shippingMethod;
    private Date shippingDate;
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name="donor_receiver_id", nullable=false)
    private DonorReceiver donorReceiver;
    @Column(name = "image", unique = false)
    private byte[] prescription;
    private String status;
}
