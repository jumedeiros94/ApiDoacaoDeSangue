package com.doacaosangue.api.repository;

import com.doacaosangue.api.model.DoadorModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DoadorRepository extends JpaRepository<DoadorModel, Long> {
}
