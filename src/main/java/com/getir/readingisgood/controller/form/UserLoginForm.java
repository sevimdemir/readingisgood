package com.getir.readingisgood.controller.form;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserLoginForm {
    @ApiModelProperty(
            value = "email of the user",
            name = "email",
            dataType = "String",
            example = "email@example.com")
    @NotBlank
    @NotNull
    @Email
    private String email;

    @ApiModelProperty(
            value = "password of the user",
            name = "password",
            dataType = "String",
            example = "123456")
    @NotNull
    @NotBlank
    @Size(min = 6, max = 12)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private String password;
}
