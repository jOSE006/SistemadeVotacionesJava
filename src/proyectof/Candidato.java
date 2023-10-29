/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyectof;

/**
 *
 * @author herbe
 */
public class Candidato {
    private int codigoUnico;
    private String nombre;
    private String formacion;
    private String experiencia;
    private String apellido;
    private String partidoPolitico;

    public Candidato(int codigoUnico, String nombre, String formacion, String experiencia, String apellido, String partidoPolitico) {
        this.codigoUnico = codigoUnico;
        this.nombre = nombre;
        this.formacion = formacion;
        this.experiencia = experiencia;
        this.apellido = apellido;
        this.partidoPolitico = partidoPolitico;
    }

    public int getCodigoUnico() {
        return codigoUnico;
    }

    public String getNombre() {
        return nombre;
    }
      public String getApellido() {
        return apellido;
    }
        public String getPartidoPolitico() {
        return partidoPolitico;
    }

    public String getFormacion() {
        return formacion;
    }

    public String getExperiencia() {
        return experiencia;
    }

    public void setCodigoUnico(int codigoUnico) {
        this.codigoUnico = codigoUnico;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public void setApellido(String apellido) {
        this.apellido = apellido;
    }
    public void setPartidoPolitico(String partidoPolitico) {
        this.partidoPolitico = partidoPolitico;
    }
    public void setFormacion(String formacion) {
        this.formacion = formacion;
    }
    public void setExperiencia(String experiencia) {
        this.experiencia = experiencia;
    }
    @Override
    public String toString() {
        return "Candidato #" + codigoUnico + ": " + nombre;
    }
}


    
