package com.aduilio.med.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aduilio.med.entity.Doctor;

/**
 * Provides the methods to persist a Doctor.
 */
public interface DoctorRepository extends JpaRepository<Doctor, String> {

}
