package challenge.forohub.service;

import challenge.forohub.domain.topico.Topico;
import challenge.forohub.domain.topico.dto.DtoActualizarTopico;
import challenge.forohub.domain.topico.dto.DtoDetallesTopico;
import challenge.forohub.domain.topico.dto.DtoRegistroTopico;
import challenge.forohub.domain.topico.validar.ValidadorDeTopicos;
import challenge.forohub.domain.usuario.Usuario;
import challenge.forohub.infra.errores.ValidacionDeIntegridad;
import challenge.forohub.repository.TopicoRespositoy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class TopicoService {


    @Autowired
    private TopicoRespositoy topicoRespositoy;

    @Autowired
    List<ValidadorDeTopicos> validadores;

    public Topico agregar(DtoRegistroTopico dtoRegistroTopico, Usuario getUsuario) {
        validadores.forEach(v-> v.validar(dtoRegistroTopico));
        return new Topico(dtoRegistroTopico, getUsuario);
    }

    public DtoDetallesTopico respuesta(Long idTopico) {
        return topicoRespositoy.findById(idTopico).map(DtoDetallesTopico::new)
                .orElseThrow(() -> new ValidacionDeIntegridad("El id del topico no existe."));
    }

    public Page<DtoDetallesTopico> listar(Pageable pageable) {
        return topicoRespositoy.findAll(pageable).map(DtoDetallesTopico::new);
    }

    public DtoDetallesTopico actulizar(Long id, DtoActualizarTopico dtoActualizarTopico) {
        if (!topicoRespositoy.findById(id).isPresent())
            throw new ValidacionDeIntegridad("El id del topico no existe.");

        if (topicoRespositoy.existsByTitulo(dtoActualizarTopico.titulo()))
            throw new ValidacionDeIntegridad("El titulo ya existe");

        if (topicoRespositoy.existsByMensaje(dtoActualizarTopico.mensaje()))
            throw new ValidacionDeIntegridad("El mensaje ya existe");

        var topico = topicoRespositoy.getReferenceById(id);
        topico.actualizar(dtoActualizarTopico);
        return new DtoDetallesTopico(topico);
    }

    public void eliminar(Long id) {
        if (!topicoRespositoy.findById(id).isPresent())
            throw new ValidacionDeIntegridad("El id del topico no existe.");

        topicoRespositoy.deleteById(id);
    }
}
