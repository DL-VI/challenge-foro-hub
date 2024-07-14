package challenge.forohub.controller;

import challenge.forohub.domain.topico.dto.DtoActualizarTopico;
import challenge.forohub.domain.topico.dto.DtoDetallesTopico;
import challenge.forohub.domain.topico.dto.DtoRegistroTopico;
import challenge.forohub.domain.usuario.Usuario;
import challenge.forohub.infra.errores.ValidacionDeIntegridad;
import challenge.forohub.repository.TopicoRespositoy;
import challenge.forohub.service.TopicoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import challenge.forohub.repository.UsuarioRepository;

import java.net.URI;
import java.util.Optional;


@RequestMapping("/topicos")
@Controller
public class TopicoController {

    @Autowired
    private TopicoService topicoService;
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private TopicoRespositoy topicoRepositoy;

    @PostMapping
    @Transactional
    public ResponseEntity<DtoDetallesTopico> agregarTopico(@RequestBody @Valid DtoRegistroTopico dtoRegistroTopico,
                                                           UriComponentsBuilder uriBuilder) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();   // Obtiene el nombre de usuario autenticado
        String username = authentication.getName();

        Optional<Usuario> usuario = usuarioRepository.getUsuarioByLogin(username);

        if (usuario.isEmpty())
            throw new ValidacionDeIntegridad("El id del usuario no existe.");

        var getUsuario = usuario.get();
        var response = topicoRepositoy.save(topicoService.agregar(dtoRegistroTopico, getUsuario));
        URI uri = uriBuilder.path("/topicos/{id}").buildAndExpand(response.getId()).toUri();
        return ResponseEntity.created(uri).body(new DtoDetallesTopico(response));
    }

    @GetMapping
    public ResponseEntity<Page<DtoDetallesTopico>> listarTopicos(@PageableDefault(size = 10, sort = "fecha", direction = Sort.Direction.ASC) Pageable pageable) {
        return ResponseEntity.ok(topicoService.listar(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<DtoDetallesTopico> detalleDeTopico(@PathVariable Long id) {
        return ResponseEntity.ok(topicoService.respuesta(id));
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<DtoDetallesTopico> actualizarTopico(@PathVariable Long id, @RequestBody @Valid DtoActualizarTopico dtoActualizarTopico) {
        return ResponseEntity.ok(topicoService.actulizar(id, dtoActualizarTopico));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity eliminarTipico(@PathVariable Long id) {
        topicoService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
