package com.hospitals.repository;

import com.hospitals.entity.DoctorEntity;

import org.springframework.data.jpa.repository.JpaRepository;

public interface DoctorRepository extends JpaRepository<DoctorEntity, Long> {
  //  DoctorEntity findByUsername(String username);
}



