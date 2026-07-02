package com.utp.DemoOratorIA.infraestructure.mappers;

import org.springframework.stereotype.Component;

import com.utp.DemoOratorIA.domain.model.aggregate.ResultadoIA;
import com.utp.DemoOratorIA.infraestructure.DTO.ResultadoIADTO;
import com.utp.DemoOratorIA.infraestructure.entities.ResultadoIAEntity;

@Component
public class ResultadoMapper {

    public ResultadoIA toDomain(ResultadoIAEntity entity) {
        if (entity == null) {
            return null;
        }

        return new ResultadoIA.Builder()
                .idResultado(entity.getIdResultado())
                .idAnalisis(entity.getIdAnalisis())
                .fluidez(entity.getFluidez())
                .claridad(entity.getClaridad())
                .volumen(entity.getVolumen())
                .velocidad(entity.getVelocidad())
                .postura(entity.getPostura())
                .contactoVisual(entity.getContactoVisual())
                .confianza(entity.getConfianza())
                .expresionFacial(entity.getExpresionFacial())
                .muletillasDetectadas(entity.getMuletillasDetectadas())
                .pausasIncomodas(entity.getPausasIncomodas())
                .puntuacionGeneral(entity.getPuntuacionGeneral())
                .nivel(entity.getNivel())
                .observaciones(entity.getObservaciones())
                .fechaResultado(entity.getFechaResultado())
                .entradaUsuario(entity.getEntradaUsuario())
                .respuestaIA(entity.getRespuestaIA())
                .puntuacion(entity.getPuntuacion())
                .fecha(entity.getFecha())
                .build();
    }

    public ResultadoIAEntity toEntity(ResultadoIA resultado) {
        if (resultado == null) {
            return null;
        }

        ResultadoIAEntity entity = new ResultadoIAEntity();

        entity.setIdResultado(resultado.getIdResultado());
        entity.setIdAnalisis(resultado.getIdAnalisis());
        entity.setFluidez(resultado.getFluidez());
        entity.setClaridad(resultado.getClaridad());
        entity.setVolumen(resultado.getVolumen());
        entity.setVelocidad(resultado.getVelocidad());
        entity.setPostura(resultado.getPostura());
        entity.setContactoVisual(resultado.getContactoVisual());
        entity.setConfianza(resultado.getConfianza());
        entity.setExpresionFacial(resultado.getExpresionFacial());
        entity.setMuletillasDetectadas(resultado.getMuletillasDetectadas());
        entity.setPausasIncomodas(resultado.getPausasIncomodas());
        entity.setPuntuacionGeneral(resultado.getPuntuacionGeneral());
        entity.setNivel(resultado.getNivel());
        entity.setObservaciones(resultado.getObservaciones());
        entity.setFechaResultado(resultado.getFechaResultado());
        entity.setEntradaUsuario(resultado.getEntradaUsuario());
        entity.setRespuestaIA(resultado.getRespuestaIA());
        entity.setPuntuacion(resultado.getPuntuacion());
        entity.setFecha(resultado.getFecha());

        return entity;
    }

    public ResultadoIADTO toDTO(ResultadoIA resultado) {

        if (resultado == null) {
            return null;
        }

        return new ResultadoIADTO(
                resultado.getIdResultado(),
                resultado.getIdAnalisis(),
                resultado.getFluidez(),
                resultado.getClaridad(),
                resultado.getVolumen(),
                resultado.getVelocidad(),
                resultado.getPostura(),
                resultado.getContactoVisual(),
                resultado.getConfianza(),
                resultado.getExpresionFacial(),
                resultado.getMuletillasDetectadas(),
                resultado.getPausasIncomodas(),
                resultado.getPuntuacionGeneral(),
                resultado.getObservaciones(),
                resultado.getFechaResultado(),
                resultado.getEntradaUsuario(),
                resultado.getRespuestaIA(),
                resultado.getPuntuacion(),
                resultado.getFecha(),
                resultado.getNivel()

        );
    }
}