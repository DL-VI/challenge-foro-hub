package challenge.forohub.controller;

import challenge.forohub.domain.usuario.DtoAutenticacionUsuario;
import challenge.forohub.domain.usuario.Usuario;
import challenge.forohub.infra.security.DtoJWTToken;
import challenge.forohub.infra.security.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/login")
public class AutenticacionController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity autenticarUsuario(@RequestBody @Valid DtoAutenticacionUsuario dtoAutenticacionUsuario) {
        Authentication authToken = new UsernamePasswordAuthenticationToken(dtoAutenticacionUsuario.login(),
                dtoAutenticacionUsuario.clave());
        var usuarioAutenticado = authenticationManager.authenticate(authToken);
        var JWTtoken = tokenService.generarToken((Usuario) usuarioAutenticado.getPrincipal());
        return ResponseEntity.ok(new DtoJWTToken(JWTtoken));
    }
}
