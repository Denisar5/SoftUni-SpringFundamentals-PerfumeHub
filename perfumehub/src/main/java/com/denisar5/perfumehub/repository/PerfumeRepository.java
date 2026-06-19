package com.denisar5.perfumehub.repository;

import com.denisar5.perfumehub.model.entity.Perfume;
import com.denisar5.perfumehub.model.enums.Gender;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface PerfumeRepository extends JpaRepository<Perfume, UUID> {
    List<Perfume> findByNameContainingIgnoreCase(String name);

    List<Perfume> findByBrandContainingIgnoreCase(String brand);

    List<Perfume> findByGender(Gender gender);

    List<Perfume> findAllByOrderByPriceAsc();

    List<Perfume> findAllByOrderByPriceDesc();

    List<Perfume> findAllByOrderByCreatedAtDesc();
}
