package challenge.forohub.domain.topico.dto;

import jakarta.validation.constraints.NotBlank;

public record DtoRegistroTopico(@NotBlank(message = "El campo del mensaje es obligatorio")
                                String mensaje,
                                @NotBlank(message = "El campo del curso es obligatorio")
                                String curso,
                                @NotBlank(message = "El campo del titulo es obligatorio")
                                String titulo) {

}
