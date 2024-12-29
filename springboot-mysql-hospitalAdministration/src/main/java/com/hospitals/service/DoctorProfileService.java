package com.hospitals.service;

import com.hospitals.entity.DoctorEntity;

public interface DoctorProfileService {
    DoctorEntity getDoctorProfile(Long doctorId);
    void updateDoctorProfile(DoctorEntity doctor);
}
