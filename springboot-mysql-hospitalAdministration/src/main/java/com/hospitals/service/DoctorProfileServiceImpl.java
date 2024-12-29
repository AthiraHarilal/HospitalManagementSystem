package com.hospitals.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hospitals.entity.DoctorEntity;
import com.hospitals.repository.DoctorRepository;

@Service
public class DoctorProfileServiceImpl implements DoctorProfileService {

    @Autowired
    private DoctorRepository doctorRepository;

    @Override
    public DoctorEntity getDoctorProfile(Long doctorId) {
        return doctorRepository.findById(doctorId).orElseThrow(() -> new RuntimeException("Doctor not found"));
    }

    @Override
    public void updateDoctorProfile(DoctorEntity doctor) {
        doctorRepository.save(doctor);
    }
}
