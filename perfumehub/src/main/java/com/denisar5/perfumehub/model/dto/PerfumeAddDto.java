package com.denisar5.perfumehub.model.dto;

import com.denisar5.perfumehub.model.enums.Gender;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class PerfumeAddDto {

    @NotBlank(message = "Name is required")
    @Size(min = 2, max = 60, message = "Name must be between 2 and 60 characters")
    private String name;

    @NotBlank(message = "Brand is required")
    @Size(min = 2, max = 60, message = "Brand must be between 2 and 60 characters")
    private String brand;

    @NotBlank(message = "Description is required")
    @Size(min = 10, max = 500, message = "Description must be between 10 and 500 characters")
    private String description;

    @NotNull(message = "Price is required")
    @DecimalMin(value = "1.00", message = "Price must be at least 1.00")
    private BigDecimal price;

    @NotBlank(message = "Image URL is required")
    private String imageUrl;

    @NotNull(message = "Gender is required")
    private Gender gender;

    @NotNull(message = "Volume is required")
    @Min(value = 10, message = "Volume must be at least 10 ml")
    @Max(value = 500, message = "Volume must be at most 500 ml")
    private Integer volumeMl;

    @NotNull(message = "Stock quantity is required")
    @Min(value = 0, message = "Stock quantity cannot be negative")
    private Integer stockQuantity;
}