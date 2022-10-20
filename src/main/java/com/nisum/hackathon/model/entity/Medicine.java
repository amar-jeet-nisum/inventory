package com.nisum.hackathon.model.entity;

import lombok.*;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
@Table(name = "medicines")
public class Medicine {
    @Id
    private String id;
    private String medicineName;
    private String medicineFormula;
}
