package org.bedu.java.backend.Postwork.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.bedu.java.backend.Postwork.validation.GlobalInterface;
import org.bedu.java.backend.Postwork.validation.IdValidator;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
public class Venta {
    @GlobalInterface(validators = {IdValidator.class},
            message = "El identificador de la venta debe ser un número positivo mayor que cero. " +
                    "No se permite utilizar números negativos ni el valor cero como identificador.")
    private long ventaId;

    @DecimalMin(value = "1.00", inclusive = true, message = "La venta debe ser de al menos 1.00")
    private float monto;

    @NotEmpty(message = "La venta debe tener por lo menos un producto.")
    private List<Producto> productos;

    @NotNull(message = "La venta debe estar asociada a un cliente válido. " +
            "Asegúrate de seleccionar un cliente al realizar la venta para que la transacción sea válida.")
    private Cliente cliente;

    @PastOrPresent(message = "La fecha de creación del producto no puede ser una fecha futura. " +
            "Asegúrate de ingresar una fecha de creación que sea igual o anterior a la fecha actual.")
    private LocalDateTime fechaCreacion;

}
