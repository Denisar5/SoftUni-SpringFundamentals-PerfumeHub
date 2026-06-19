package com.denisar5.perfumehub.service.impl;

import com.denisar5.perfumehub.model.dto.PerfumeAddDto;
import com.denisar5.perfumehub.model.dto.PerfumeEditDto;
import com.denisar5.perfumehub.model.entity.Perfume;
import com.denisar5.perfumehub.repository.PerfumeRepository;
import com.denisar5.perfumehub.service.PerfumeService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class PerfumeServiceImpl implements PerfumeService {

    private final PerfumeRepository perfumeRepository;

    public PerfumeServiceImpl(PerfumeRepository perfumeRepository) {
        this.perfumeRepository = perfumeRepository;
    }

    @Override
    public void addPerfume(PerfumeAddDto dto) {

        Perfume perfume = Perfume.builder()
                .name(dto.getName())
                .brand(dto.getBrand())
                .description(dto.getDescription())
                .price(dto.getPrice())
                .imageUrl(dto.getImageUrl())
                .gender(dto.getGender())
                .volumeMl(dto.getVolumeMl())
                .stockQuantity(dto.getStockQuantity())
                .createdAt(LocalDateTime.now())
                .build();

        perfumeRepository.save(perfume);
    }

    @Override
    public void editPerfume(UUID id, PerfumeEditDto dto) {

        Perfume perfume = getPerfumeById(id);

        perfume.setName(dto.getName());
        perfume.setBrand(dto.getBrand());
        perfume.setDescription(dto.getDescription());
        perfume.setPrice(dto.getPrice());
        perfume.setImageUrl(dto.getImageUrl());
        perfume.setGender(dto.getGender());
        perfume.setVolumeMl(dto.getVolumeMl());
        perfume.setStockQuantity(dto.getStockQuantity());

        perfumeRepository.save(perfume);
    }

    @Override
    public void deletePerfume(UUID id) {
        perfumeRepository.deleteById(id);
    }

    @Override
    public List<Perfume> getAllPerfumes() {
        return perfumeRepository.findAll();
    }

    @Override
    public Perfume getPerfumeById(UUID id) {
        return perfumeRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Perfume not found"));
    }
}