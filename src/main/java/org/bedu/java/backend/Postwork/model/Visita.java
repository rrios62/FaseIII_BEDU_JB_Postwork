package org.bedu.java.backend.Postwork.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.bedu.java.backend.Postwork.validation.GlobalInterface;
import org.bedu.java.backend.Postwork.validation.IdValidator;
import org.bedu.java.backend.Postwork.validation.NameValidator;

import javax.validation.constraints.*;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
public class Visita {
    @GlobalInterface(validators = {IdValidator.class},
            message = "El identificador de la visita debe ser un número positivo mayor que cero. " +
                    "No se permite utilizar números negativos ni el valor cero como identificado.")
    private long id;

    @GlobalInterface(validators = {IdValidator.class},
            message = "El identificador del cliente debe ser un número positivo mayor que cero. " +
                    "No se permite utilizar números negativos ni el valor cero como identificador.")
    private long clienteId;

    @Future(message = "La fecha de creación del producto no puede ser una fecha pasada. " +
            "Asegúrate de ingresar una fecha de creación que sea posterior a la fecha actual.")
    private LocalDateTime fechaProgramada;

    @NotEmpty(message = "La dirección es un campo obligatorio.")
    @Size(min = 10, message = "Asegurate que la dirección debe tener al menos 10 letras.")
    private String direccion;

    @NotEmpty(message = "El propósito de la visita es un campo obligatorio y no puede dejarse en blanco..")
    @Size(min = 15, message = "\n" +
            "El propósito de la visita debe tener al menos 15 letras. " +
            "Asegúrate de proporcionar un propósito que cumpla con este requisito mínimo de longitud.")
    private String proposito;

    @GlobalInterface(validators = {NameValidator.class},
            message = "El nombre del vendedor es un campo obligatorio y debe tener al menos 3 letras y como máximo 50 letras." +
                    " Asegúrate de proporcionar un nombre válido que cumpla con estos requisitos")
    private String vendedor;

}
