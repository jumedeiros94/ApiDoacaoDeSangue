package com.doacaosangue.api.repository;

import com.doacaosangue.api.model.HospitalModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HospitalRepository extends JpaRepository<HospitalModel, Long> {
}
