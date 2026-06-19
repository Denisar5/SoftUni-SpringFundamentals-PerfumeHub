package com.denisar5.perfumehub.service;

import com.denisar5.perfumehub.model.dto.PerfumeAddDto;
import com.denisar5.perfumehub.model.dto.PerfumeEditDto;
import com.denisar5.perfumehub.model.entity.Perfume;

import java.util.List;
import java.util.UUID;

public interface PerfumeService {

    void addPerfume(PerfumeAddDto perfumeAddDto);

    void editPerfume(UUID id, PerfumeEditDto perfumeEditDto);

    void deletePerfume(UUID id);

    List<Perfume> getAllPerfumes();

    Perfume getPerfumeById(UUID id);
}