/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyectof;
import proyectof.Votante;

/**
 *
 * @author herbe
 */
// Votante.java
public class Votante {
    private String nombres;
    private String apellidos;
    private String cui;
    private String sexo;
    private String fechaNacimiento;
    private String direccion;
    private String departamento;
    private String municipio;
    private String pais;
    private String correoElectronico;
    private boolean elegible;
    private String contrasena;
    private boolean fallecido;

    public Votante(String nombres, String apellidos, String cui, String sexo, String fechaNacimiento,
                   String direccion, String departamento, String municipio, String pais, String correoElectronico, String contrasena) {
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.cui = cui;
        this.sexo = sexo;
        this.fechaNacimiento = fechaNacimiento;
        this.direccion = direccion;
        this.departamento = departamento;
        this.municipio = municipio;
        this.pais = pais;
        this.correoElectronico = correoElectronico;
        this.elegible = esElegible();
        this.contrasena = contrasena;
        this.fallecido = false;
    }

    public boolean esElegible() {
        
        return true; 
    }
    

    public String generarContrasenaAleatoria() {

        return contrasena;
    }

     public String getNombres() {
            return nombres;
        }
     public String getApellidos() {
            return apellidos;
        }
     public String getCUI() {
            return cui;
        }
     public String getSexo() {
            return sexo;
        }
     public String getFechaNacimiento() {
            return fechaNacimiento;
        }
     public String getDireccion() {
            return direccion;
        }
     public String getDepartamento() {
            return direccion;
        }
     public String getMunicipio() {
            return municipio;
        }
     public String getPais() {
            return pais;
        }
     public String getCorreoElectronico() {
            return correoElectronico;
        }
     public String getContrasena() {
            return contrasena;
        }
    public boolean isFallecido() {
        return fallecido;
    }

    public void setFallecido(boolean fallecido) {
        this.fallecido = fallecido;
    }
    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public void setCUI(String cui) {
        this.cui = cui;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public void setFechaNacimiento(String fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    public void setMunicipio(String municipio) {
        this.municipio = municipio;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }
    public void setContrasena(String nuevaContrasena) {
        this.contrasena = nuevaContrasena;
    }
 public boolean autenticar(String correoElectronico, String contrasena, String cui) {
    if (this.correoElectronico.equals(correoElectronico) && this.contrasena.equals(contrasena) && this.cui.equals(cui)) {
        return true; // Autenticación exitosa con CUI
    }
    return false; // Autenticación fallida
}
}

