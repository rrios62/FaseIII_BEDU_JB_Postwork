package org.bedu.java.backend.Postwork.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.bedu.java.backend.Postwork.validation.GlobalInterface;
import org.bedu.java.backend.Postwork.validation.IdValidator;
import org.bedu.java.backend.Postwork.validation.NameValidator;

import javax.validation.constraints.Positive;
@Data
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
public class Etapa {
    @GlobalInterface(validators = {IdValidator.class},
            message = "El identificador de la etapa debe ser un número positivo mayor que cero. " +
                    "No se permite utilizar números negativos ni el valor cero como identificador.")
    private long etapaID;

    @GlobalInterface(validators = {NameValidator.class},
            message = "El nombre de la etapa es un campo obligatorio y debe tener al menos 3 letras y como máximo 50 letras." +
                    " Asegúrate de proporcionar un nombre válido que cumpla con estos requisitos")
    private String nombre;

    @Positive( message = "El orden debe ser un número positivo mayor que cero. " +
            "No se permite utilizar números negativos ni el valor cero como valor.")
    private int orden;
}
