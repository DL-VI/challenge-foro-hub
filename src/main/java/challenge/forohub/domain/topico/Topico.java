package challenge.forohub.domain.topico;

import challenge.forohub.domain.topico.dto.DtoActualizarTopico;
import challenge.forohub.domain.topico.dto.DtoRegistroTopico;
import challenge.forohub.domain.usuario.Usuario;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "topicos")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Topico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private String mensaje;
    @Column(name = "fecha_creacion")
    private LocalDateTime fecha;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "autor_id")
    private Usuario usuario;
    private String curso;

    public Topico(DtoRegistroTopico dtoRegistroTopico, Usuario usuario) {
        this.titulo = dtoRegistroTopico.titulo();
        this.mensaje = dtoRegistroTopico.mensaje();
        this.fecha = LocalDateTime.now();
        this.usuario = usuario;
        this.curso = dtoRegistroTopico.curso();
    }

    public void actualizar(DtoActualizarTopico dtoActualizarTopico) {
        if (dtoActualizarTopico.mensaje() != null)
            this.mensaje = dtoActualizarTopico.mensaje();
        if (dtoActualizarTopico.titulo() != null)
            this.titulo = dtoActualizarTopico.titulo();
    }

}
