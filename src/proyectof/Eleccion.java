/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyectof;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author herbe
 */
public class Eleccion {

    private int codigoUnico;
    private String titulo;
    private String proposito;
    private String descripcion;
    private LocalDateTime fechaInicioInscripcion;
    private LocalDateTime fechaFinInscripcion;
    private LocalDateTime fechaInicioVotacion;
    private LocalDateTime fechaFinVotacion;

    public Eleccion(int codigoUnico, String titulo, String proposito, String descripcion) {
        this.codigoUnico = codigoUnico;
        this.titulo = titulo;
        this.proposito = proposito;
        this.descripcion = descripcion;
    }

    public LocalDateTime getfechaInicioInscripcion(){
        return fechaInicioInscripcion;
    }
    public LocalDateTime getfechaInicioVotacion() {
        return fechaInicioInscripcion;
    }
    public LocalDateTime getFechaFinInscripcion() {
        return fechaFinInscripcion;
    }
    public LocalDateTime getFechaFinVotacion() {
        return fechaFinVotacion;
    }
    public int getCodigoUnico() {
        return codigoUnico;
    }
    public String getProposito() {
        return proposito;
    }
    public String getDescripcion() {
        return descripcion;
    }
    public String getTitulo() {
        return titulo;
    }

    // Métodos para establecer las fechas y horas de la elección
    public void setFechaInicioInscripcion(LocalDateTime fechaInicioInscripcion) {
        this.fechaInicioInscripcion = fechaInicioInscripcion;
    }

    public void setFechaFinInscripcion(LocalDateTime fechaFinInscripcion) {
        this.fechaFinInscripcion = fechaFinInscripcion;
    }

    public void setFechaInicioVotacion(LocalDateTime fechaInicioVotacion) {
        this.fechaInicioVotacion = fechaInicioVotacion;
    }

    public void setFechaFinVotacion(LocalDateTime fechaFinVotacion) {
        this.fechaFinVotacion = fechaFinVotacion;
    }

    @Override
    public String toString() {
        // Puedes personalizar este método para mostrar información detallada de la elección.
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        return "Eleccion #" + codigoUnico + ": " + titulo + "\n"
                + "Propósito: " + proposito + "\n"
                + "Descripción: " + descripcion + "\n"
                + "Inicio de inscripción: " + fechaInicioInscripcion.format(formatter) + "\n"
                + "Fin de inscripción: " + fechaFinInscripcion.format(formatter) + "\n"
                + "Inicio de votación: " + fechaInicioVotacion.format(formatter) + "\n"
                + "Fin de votación: " + fechaFinVotacion.format(formatter);
    }

    public Eleccion buscarEleccionPorCodigo(int codigo, List<Eleccion> elecciones) {
        for (Eleccion eleccion : elecciones) {
            if (eleccion.getCodigoUnico() == codigo) {
                return eleccion;
            }
        }
        return null; // No se encontró la elección con el código dado.
    }
    private List<Candidato> candidatos = new ArrayList<>();
    
    public void agregarCandidato(Candidato candidato) {
       candidatos.add(candidato);
    }
}
