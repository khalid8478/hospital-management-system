package com.pathshala.repository;

import com.pathshala.entity.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface DoctorRepository extends JpaRepository<Doctor, Long> {

    @Query("SELECT d FROM Doctor d LEFT JOIN FETCH d.departments WHERE d.id = :id")
    Doctor findByIdWithDepartments(@Param("id") Long id);

}
