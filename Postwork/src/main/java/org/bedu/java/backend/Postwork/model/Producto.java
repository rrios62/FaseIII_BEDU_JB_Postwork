package org.bedu.java.backend.Postwork.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.bedu.java.backend.Postwork.validation.GlobalInterface;
import org.bedu.java.backend.Postwork.validation.IdValidator;
import org.bedu.java.backend.Postwork.validation.NameValidator;

import javax.validation.constraints.*;
import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
public class Producto {
    @GlobalInterface(validators = {IdValidator.class},
            message = "El identificador del producto debe ser un número positivo mayor que cero. " +
                    "No se permite utilizar números negativos ni el valor cero como identificado.")
    private long id;

    @GlobalInterface(validators = {NameValidator.class},
            message = "El nombre del producto es un campo obligatorio y debe tener al menos 3 letras y como máximo 50 letras." +
                    " Asegúrate de proporcionar un nombre válido que cumpla con estos requisitos")
    private String nombre;

    @GlobalInterface(validators = {NameValidator.class},
            message = "La categoría es un campo obligatorio. " +
                    "Asegúrate de proporcionar un nombre de categoría válido, que cumpla con estos requisitos")
    private String categoria;

    @DecimalMin(value = "1.00", inclusive = true, message = "\n" +
            "El precio debe ser un valor mayor o igual a 1. " +
            "Asegúrate de ingresar un precio válido que cumpla con este requisito mínimo.")
    private float precio;

    @NotEmpty(message = "El numero de registro es un campo obligatorio.Con el siguiente formato 000-00-0000")
    @Pattern(regexp = "^(\\d{3}[-]?){2}\\d{4}$")
    private String numeroRegistro;

    @PastOrPresent(message = "La fecha de creación del producto no puede ser una fecha futura. " +
            "Asegúrate de ingresar una fecha de creación que sea igual o anterior a la fecha actual.")
    private LocalDate fechaCreacion;

}
