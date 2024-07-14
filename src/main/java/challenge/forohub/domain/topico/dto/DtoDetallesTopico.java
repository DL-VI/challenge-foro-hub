package challenge.forohub.domain.topico.dto;

import challenge.forohub.domain.topico.Topico;

import java.time.LocalDateTime;

public record DtoDetallesTopico(Long idTopico,
                                String titulo,
                                String mensaje,
                                String usuario,
                                String curso,
                                LocalDateTime fecha)
{
    public DtoDetallesTopico(Topico topico) {
    this(topico.getId(), topico.getTitulo(), topico.getMensaje(),
            topico.getUsuario().getLogin(), topico.getCurso(), topico.getFecha());
    }
}
