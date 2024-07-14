package challenge.forohub.domain.topico.validar;

import challenge.forohub.domain.topico.dto.DtoRegistroTopico;
import challenge.forohub.infra.errores.ValidacionDeIntegridad;
import challenge.forohub.repository.TopicoRespositoy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RegistrosDuplicados implements ValidadorDeTopicos{

    @Autowired
    private TopicoRespositoy topicoRespositoy;

    @Override
    public void validar(DtoRegistroTopico dtoRegistroTopico) {
        var tituloDuplicado = dtoRegistroTopico.titulo();
        var mensajeDuplicado = dtoRegistroTopico.mensaje();

        if (topicoRespositoy.existsByTitulo(tituloDuplicado))
            throw new ValidacionDeIntegridad("El titulo ya existe");

        if (topicoRespositoy.existsByMensaje(mensajeDuplicado))
            throw new ValidacionDeIntegridad("El mensaje ya existe");
    }
}
