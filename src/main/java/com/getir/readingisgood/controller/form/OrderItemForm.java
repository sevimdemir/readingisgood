package com.getir.readingisgood.controller.form;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderItemForm {
    @NotNull
    @NotBlank
    private String bookId;

    @NotNull
    @Min(1)
    private Integer quantity;
}
