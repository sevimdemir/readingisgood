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
public class UserForm {

    @ApiModelProperty(
            value = "first name of the user",
            name = "firstName",
            dataType = "String",
            example = "name")
    @NotBlank
    @NotNull
    @Size(max = 20)
    private String firstName;

    @ApiModelProperty(
            value = "last name of the user",
            name = "lastName",
            dataType = "String",
            example = "surname")
    @NotBlank
    @NotNull
    @Size(max = 20)
    private String lastName;

    @ApiModelProperty(
            value = "email the user",
            name = "email",
            dataType = "String",
            example = "email@example.com")
    @NotBlank
    @NotNull
    @Email
    private String email;

    @ApiModelProperty(
            value = "password for the user length of [6-12]",
            name = "password",
            dataType = "String",
            example = "123456")
    @Size(min = 6, max = 12)
    @NotNull
    @NotBlank
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private String password;
}
