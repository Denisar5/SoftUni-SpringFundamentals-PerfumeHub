package com.denisar5.perfumehub.model.dto;


import jakarta.validation.constraints.Min;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderCreateDto {

    @Min(1)
    private Integer quantity;
}
