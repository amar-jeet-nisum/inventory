package com.nisum.hackathon.repository;

import com.nisum.hackathon.model.entity.DonorReceiver;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DonorReceiverRepository extends JpaRepository<DonorReceiver, Long> {

}
