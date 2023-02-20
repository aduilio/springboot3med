package com.aduilio.med.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.aduilio.med.entity.Doctor;

/**
 * Provides the methods to persist a Doctor.
 */
public interface DoctorRepository extends JpaRepository<Doctor, String> {

    /**
     * Returns all the active doctors.
     * 
     * @param pageable the page information
     * @return Page of Doctor
     */
    Page<Doctor> findAllByActiveTrue(Pageable pageable);

}
