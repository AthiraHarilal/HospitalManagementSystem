package com.hospitals.service;

import com.hospitals.entity.PatientEntity;

import java.util.List;

public interface PatientService {
	List<PatientEntity> getAllPatients();

	PatientEntity getPatientById(Long id);

	void savePatient(PatientEntity patient);

	void deletePatient(Long id);
}
