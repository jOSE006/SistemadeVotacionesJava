/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyectof;

import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author herbe
 */
public class AutenticacionVotante {
    
    private List<Votante> votantesAutorizados;

    public AutenticacionVotante() {
        // Inicializar la lista de votantes autorizados (deberías cargarla desde tu sistema)
        votantesAutorizados = new ArrayList<>();
    }

    public Votante autenticar(String correoElectronico, String contrasena) {
        for (Votante votante : votantesAutorizados) {
            if (votante.getCorreoElectronico().equals(correoElectronico) && votante.getContrasena().equals(contrasena)) {
                return votante; // El votante está autenticado
                
            }
        }
        return null; // Las credenciales son incorrectas
    }
    
}
