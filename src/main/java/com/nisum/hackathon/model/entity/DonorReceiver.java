package com.nisum.hackathon.model.entity;

import com.nisum.hackathon.model.contants.PersonType;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "donor_receiver")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class DonorReceiver {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;
    private String personName;
    private String address;
    private PersonType personType;
    @OneToMany(mappedBy="donorReceiver", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Inventory> inventoryList;
}
