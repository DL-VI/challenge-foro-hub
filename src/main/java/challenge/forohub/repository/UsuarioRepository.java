package challenge.forohub.repository;

import challenge.forohub.domain.usuario.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    Optional<Usuario> getUsuarioByLogin(String nombre);
    UserDetails findByLogin(String nombreUsuario);
}
