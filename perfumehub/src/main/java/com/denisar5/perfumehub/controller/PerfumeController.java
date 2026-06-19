package com.denisar5.perfumehub.controller;

import com.denisar5.perfumehub.service.PerfumeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.UUID;

@Controller
@RequestMapping("/perfumes")
public class PerfumeController {

    private final PerfumeService perfumeService;

    public PerfumeController(PerfumeService perfumeService) {
        this.perfumeService = perfumeService;
    }

    @GetMapping
    public String catalog(Model model) {

        model.addAttribute(
                "perfumes",
                perfumeService.getAllPerfumes()
        );

        return "perfume-catalog";
    }

    @GetMapping("/{id}")
    public String details(
            @PathVariable UUID id,
            Model model
    ) {

        model.addAttribute(
                "perfume",
                perfumeService.getPerfumeById(id)
        );

        return "perfume-details";
    }
}
