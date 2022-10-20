package com.nisum.hackathon.repository;

import com.nisum.hackathon.model.entity.Inventory;
import com.nisum.hackathon.model.entity.Medicine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MedicineRepository extends JpaRepository<Medicine, String> {

}