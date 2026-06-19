package com.denisar5.perfumehub.controller;

import com.denisar5.perfumehub.model.enums.Gender;
import com.denisar5.perfumehub.service.PerfumeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.UUID;

@Controller
@RequestMapping("/perfumes")
public class PerfumeController {

    private final PerfumeService perfumeService;

    public PerfumeController(PerfumeService perfumeService) {
        this.perfumeService = perfumeService;
    }

    @GetMapping
    public String catalog(@RequestParam(required = false) String search,
                          @RequestParam(required = false) String brand,
                          @RequestParam(required = false) Gender gender,
                          @RequestParam(required = false) String sort,
                          Model model) {

        model.addAttribute("perfumes", perfumeService.searchPerfumes(search, brand, gender, sort));

        model.addAttribute("search", search);
        model.addAttribute("brand", brand);
        model.addAttribute("selectedGender", gender);
        model.addAttribute("sort", sort);

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
