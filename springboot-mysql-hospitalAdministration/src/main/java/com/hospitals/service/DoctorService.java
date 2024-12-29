package com.hospitals.service;

import java.util.List;
import com.hospitals.entity.DoctorEntity;

public interface DoctorService {
    List<DoctorEntity> getAllDoctors();
    DoctorEntity getDoctorById(Long id);
    void saveDoctor(DoctorEntity doctor);
    void deleteDoctorById(Long id);
}
