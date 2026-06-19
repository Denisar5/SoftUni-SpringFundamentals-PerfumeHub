package com.denisar5.perfumehub.controller;

import com.denisar5.perfumehub.model.dto.PerfumeAddDto;
import com.denisar5.perfumehub.model.dto.PerfumeEditDto;
import com.denisar5.perfumehub.model.entity.Perfume;
import com.denisar5.perfumehub.service.PerfumeService;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Controller
@RequestMapping("/admin/perfumes")
public class AdminPerfumeController {

    private final PerfumeService perfumeService;

    public AdminPerfumeController(PerfumeService perfumeService) {
        this.perfumeService = perfumeService;
    }

    @GetMapping("/add")
    public String add(Model model) {

        model.addAttribute(
                "perfumeAddDto",
                new PerfumeAddDto()
        );

        return "add-perfume";
    }

    @PostMapping("/add")
    public String add(
            @Valid @ModelAttribute PerfumeAddDto perfumeAddDto,
            BindingResult bindingResult
    ) {

        if (bindingResult.hasErrors()) {
            return "add-perfume";
        }

        perfumeService.addPerfume(perfumeAddDto);

        return "redirect:/perfumes";
    }

    @GetMapping("/edit/{id}")
    public String edit(
            @PathVariable UUID id,
            Model model
    ) {

        Perfume perfume =
                perfumeService.getPerfumeById(id);

        PerfumeEditDto dto =
                new PerfumeEditDto();

        BeanUtils.copyProperties(perfume, dto);

        model.addAttribute("perfumeEditDto", dto);
        model.addAttribute("perfumeId", id);

        return "edit-perfume";
    }

    @PostMapping("/edit/{id}")
    public String edit(
            @PathVariable UUID id,
            @Valid @ModelAttribute PerfumeEditDto dto,
            BindingResult bindingResult
    ) {

        if (bindingResult.hasErrors()) {
            return "edit-perfume";
        }

        perfumeService.editPerfume(id, dto);

        return "redirect:/perfumes/" + id;
    }

    @PostMapping("/delete/{id}")
    public String delete(
            @PathVariable UUID id
    ) {

        perfumeService.deletePerfume(id);

        return "redirect:/perfumes";
    }
}
