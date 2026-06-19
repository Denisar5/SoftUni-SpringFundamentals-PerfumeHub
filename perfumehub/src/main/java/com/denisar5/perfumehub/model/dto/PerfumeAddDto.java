package com.denisar5.perfumehub.model.dto;

import com.denisar5.perfumehub.model.enums.Gender;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class PerfumeAddDto {

    @NotBlank
    @Size(min = 2, max = 60)
    private String name;

    @NotBlank
    @Size(min = 2, max = 60)
    private String brand;

    @NotBlank
    @Size(min = 10, max = 500)
    private String description;

    @Positive
    private BigDecimal price;

    @NotBlank
    private String imageUrl;

    @NotNull
    private Gender gender;

    @Positive
    private Integer volumeMl;

    @PositiveOrZero
    private Integer stockQuantity;
}
