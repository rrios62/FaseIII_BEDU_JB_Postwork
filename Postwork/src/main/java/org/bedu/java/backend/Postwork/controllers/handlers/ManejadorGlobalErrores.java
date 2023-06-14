package org.bedu.java.backend.Postwork.controllers.handlers;

import org.bedu.java.backend.Postwork.model.RespuestaError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
public class ManejadorGlobalErrores {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleStatusException(MethodArgumentNotValidException ex, WebRequest request) {

        return RespuestaError.builder()
                .exception(ex)
                .mensaje("Ocurrió un error al validar la información de la petición. " +
                        "Por favor, revisa los datos proporcionados y asegúrate de que sean correctos y completos. " +
                        "Si el problema persiste, te recomendamos ponerse en contacto con el soporte técnico para obtener ayuda adicional.")
                .ruta(request.getDescription(false).substring(4))
                .entidad();
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> manejaException(Exception ex, WebRequest request) {
        return RespuestaError.builder()
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .mensaje("Se produjo un error al procesar la solicitud. " +
                        "Por favor, revisa los datos proporcionados y asegúrate de que sean correctos. " +
                        "Si el problema persiste, te recomendamos ponerse en contacto con el soporte técnico para obtener asistencia adicional en la resolución del problema.")
                .ruta(request.getDescription(false).substring(4))
                .entidad();
    }
}


