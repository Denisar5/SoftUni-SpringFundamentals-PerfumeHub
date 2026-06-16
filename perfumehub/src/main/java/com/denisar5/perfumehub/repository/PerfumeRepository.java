package com.denisar5.perfumehub.repository;

import com.denisar5.perfumehub.model.entity.Perfume;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PerfumeRepository extends JpaRepository<Perfume, UUID> {
}
