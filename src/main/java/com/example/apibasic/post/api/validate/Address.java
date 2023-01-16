package com.example.apibasic.post.api.validate;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;

@Setter @Getter @ToString
public class Address {

    @NotBlank
    private String street;

    @NotBlank
    private String postCode;
}