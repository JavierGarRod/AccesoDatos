package TEMA1.RepasoJava.ejRepaso2.Repository;

import java.time.LocalDate;

import TEMA1.RepasoJava.ejRepaso2.Exception.ConversacionException;
import TEMA1.RepasoJava.ejRepaso2.Models.Conversacion;
import TEMA1.RepasoJava.ejRepaso2.Models.TipoAgente;

public interface IRepositorioConversaciones {

    void agregaConversacion(
            TipoAgente tipo,
            String pregunta,
            String respuesta);

    Conversacion getConversacion(
            LocalDate fecha,
            TipoAgente tipo,
            String pregunta)
            throws ConversacionException;

    boolean contieneConversacion(
            Conversacion conversacion);

    void eliminaConversacion(
            LocalDate fecha,
            TipoAgente tipo,
            String pregunta)
            throws ConversacionException;

    void incrementaNumeroValoraciones(
            LocalDate fecha,
            TipoAgente tipo,
            String pregunta,
            double valoracion)
            throws ConversacionException;
}

