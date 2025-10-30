package com.uady.blackWolfCinema.validation;

import org.springframework.web.multipart.MultipartFile;

import jakarta.persistence.Transient;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class MovieValidation {

    @NotBlank(message = "El nombre de la película no puede estar vacío")
    private String name;

    @NotBlank(message = "La sinopsis no puede estar vacía")
    private String synopsis;

    @NotNull(message = "La duración no puede estar vacía")
    @Min(value = 1, message = "La duración debe ser mayor que cero")
    private int duration;

    @NotBlank(message = "El trailer no puede estar vacío")
    private String trailer;

    @Transient
    private MultipartFile portada;

    public MovieValidation() {

    }
}
