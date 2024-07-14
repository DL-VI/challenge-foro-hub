package challenge.forohub.repository;

import challenge.forohub.domain.topico.Topico;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TopicoRespositoy extends JpaRepository<Topico, Long> {
    Boolean existsByTitulo(String tituloDuplicado);
    Boolean existsByMensaje(String mensajeDuplicado);
}
