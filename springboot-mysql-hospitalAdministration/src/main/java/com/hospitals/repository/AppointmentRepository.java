package com.hospitals.repository;

import com.hospitals.entity.AppointmentEntity;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AppointmentRepository extends JpaRepository<AppointmentEntity, Long> {
    List<AppointmentEntity> findByDoctorId(Long doctorId);
    List<AppointmentEntity> findByPatientId(Long patientId);
  //  List<AppointmentEntity> findByPatientId(Long patientId);
}


