package com.learnsyc.appweb.serializers.FormData;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.Objects;

@Data
public class FormData {
    @NotBlank(message = "Se ha detectado un espacio en blanco")
    @NotNull(message = "No se pueden enviar datos vacios")
    @Size(min = 8, message = "La contraseña debe tener al menos 8 carácteres")
    private String password;

    @NotBlank(message = "Se ha detectado un espacio en blanco")
    @NotNull(message = "No se pueden enviar datos vacios")
    @Size(min = 8, message = "La contraseña debe tener al menos 8 carácteres")
    private String confirmPassword;
}
