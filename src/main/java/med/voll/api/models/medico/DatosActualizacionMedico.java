package med.voll.api.models.medico;

import jakarta.validation.constraints.NotNull;
import med.voll.api.models.direccion.DatosDireccion;

public record DatosActualizacionMedico(
        @NotNull Long id,
        String nombre,
        String telefono,
        DatosDireccion direccion
) {
}
