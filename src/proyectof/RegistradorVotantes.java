/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyectof;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import proyectof.Proyectof.user;
import proyectof.Votante;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
/**
 *
 * @author herbe
 */

public class RegistradorVotantes {

    private List<Votante> votantes;
    private Random random;

    public RegistradorVotantes() {
        votantes = new ArrayList<>();
        random = new Random();
    }

    public void registrarVotante(Scanner scanner) {
        // Captura los datos del nuevo votante
        System.out.print("Ingrese el nombre del votante: ");
        String nombres = scanner.nextLine();
        System.out.print("Ingrese los apellidos del votante: ");
        String apellidos = scanner.nextLine();
        System.out.print("Ingrese el CUI del votante: ");
        String cui = scanner.nextLine();
        System.out.print("Ingrese el sexo del votante (M/F): ");
        String sexo = scanner.nextLine();
        System.out.print("Ingrese la fecha de nacimiento del votante (YYYY-MM-DD): ");
        String fechaNacimiento = scanner.next();
        scanner.nextLine(); // Limpia el buffer
        System.out.print("Ingrese la dirección de residencia del votante: ");
        String direccionResidencia = scanner.nextLine();
        System.out.print("Ingrese el departamento de residencia del votante: ");
        String departamentoResidencia = scanner.nextLine();
        System.out.print("Ingrese el municipio de residencia del votante: ");
        String municipioResidencia = scanner.nextLine();
        System.out.print("Ingrese el país de residencia del votante: ");
        String paisResidencia = scanner.nextLine();
        System.out.print("Ingrese el correo electronico del votante: ");
        String correoElectronico = scanner.nextLine();

        // Verifica si el votante es elegible (mayor de 18 años)
        if (calcularEdad(fechaNacimiento) >= 18) {
            // Genera una contraseña aleatoria de 16 caracteres
            String contrasena = generarContrasenaAleatoria(16);

            // Crea el objeto Votante y lo agrega a la lista
            Votante nuevoVotante = new Votante(nombres,apellidos, cui, sexo, fechaNacimiento, direccionResidencia, departamentoResidencia, municipioResidencia, paisResidencia,correoElectronico, contrasena);
            votantes.add(nuevoVotante);

            // Guarda los votantes en un archivo de texto
            guardarVotantesEnArchivo();

            System.out.println("Votante registrado con éxito. Contraseña generada: " + contrasena);
        } else {
            System.out.println("El votante no es elegible (menor de 18 años).");
        }
    }

    public void modificarDatosVotante(Scanner scanner) {
       // Solicita el CUI del votante cuyos datos se van a modificar
    System.out.print("Ingrese el CUI del votante cuyos datos desea modificar: ");
    String cuiModificar = scanner.nextLine();

    // Busca el votante en la lista por su CUI
    Votante votanteModificar = buscarVotantePorCUI(cuiModificar);
        if (votanteModificar != null) {
            // Muestra los datos actuales del votante
            System.out.println("Datos actuales del votante:");
            System.out.println("Nombres: " + votanteModificar.getNombres());
            System.out.println("Apellidos: " + votanteModificar.getApellidos());
            System.out.println("Dirección: " + votanteModificar.getDireccion());
            System.out.println("País: " + votanteModificar.getPais());
            System.out.println("Departamento: " + votanteModificar.getDepartamento());
            System.out.println("Municipio: " + votanteModificar.getMunicipio());
            System.out.println("Correo electrónico: " + votanteModificar.getCorreoElectronico());

            // Solicita al usuario que ingrese los nuevos datos
            System.out.print("Ingrese la nueva dirección de residencia: ");
            String nuevaDireccion = scanner.nextLine();
            System.out.print("Ingrese el nuevo país de residencia: ");
            String nuevoPais = scanner.nextLine();
            System.out.print("Ingrese el nuevo departamento de residencia: ");
            String nuevoDepartamento = scanner.nextLine();
            System.out.print("Ingrese el nuevo municipio de residencia: ");
            String nuevoMunicipio = scanner.nextLine();
            System.out.print("Ingrese el nuevo correo electrónico: ");
            String nuevoCorreoElectronico = scanner.nextLine();

            // Actualiza los datos del votante con los nuevos valores
            votanteModificar.setDireccion(nuevaDireccion);
            votanteModificar.setPais(nuevoPais);
            votanteModificar.setDepartamento(nuevoDepartamento);
            votanteModificar.setMunicipio(nuevoMunicipio);
            votanteModificar.setCorreoElectronico(nuevoCorreoElectronico);

            // Guarda los cambios en el archivo de votantes
            guardarVotantesEnArchivo();

            System.out.println("Datos del votante modificados con éxito.");
        } else {
            System.out.println("No se encontró ningún votante con el CUI proporcionado.");
        }
    }
    private Votante buscarVotantePorCUI(String cui) {
    for (Votante votante : votantes) {
        if (votante.getCUI().equals(cui)) {
            return votante; // Devuelve el votante encontrado
        }
    }
    return null; // Si no se encuentra ningún votante con ese CUI
}

    public void generarNuevaContrasena(Scanner scanner) {

        System.out.print("Ingrese el CUI del votante al que desea generar una nueva contraseña: ");
        String cuiModificar = scanner.nextLine();

        // Busca el votante en la lista por CUI
        Votante votanteModificar = buscarVotantePorCUI(cuiModificar);

        if (votanteModificar != null) {
            // Genera una nueva contraseña aleatoria
            String nuevaContrasena = generarContrasenaAleatoria(16);

            // Establece la nueva contraseña en el votante
            votanteModificar.setContrasena(nuevaContrasena);

            // Guarda los cambios en el archivo de votantes (debes implementar este método)
            guardarVotantesEnArchivo();

            System.out.println("Nueva contraseña generada y asignada al votante con éxito.");
            System.out.println("Votante registrado con éxito. Contraseña generada: " + votanteModificar.getContrasena());
        } else {
            System.out.println("No se encontró ningún votante con el CUI ingresado.");
        }
    }

    public void darDeBajaVotante(Scanner scanner) {
        System.out.print("Ingrese el CUI del votante al que desea dar de baja por fallecimiento: ");
        String cuiDarDeBaja = scanner.nextLine();
 
        // Busca el votante en la lista por CUI
        Votante votanteDarDeBaja = buscarVotantePorCUI(cuiDarDeBaja);

        if (votanteDarDeBaja != null) {
            // Verifica si el votante está marcado como fallecido
            if (!votanteDarDeBaja.isFallecido()) {
                // Marca al votante como fallecido
                votanteDarDeBaja.setFallecido(true);

                // Guarda los cambios en el archivo de votantes (debes implementar este método)
                guardarVotantesEnArchivo();

                System.out.println("Votante marcado como fallecido y dado de baja con éxito.");
            } else {
                System.out.println("El votante ya está marcado como fallecido.");
            }
        } else {
            System.out.println("No se encontró ningún votante con el CUI ingresado.");
        }
    }

    private int calcularEdad(String fechaNacimiento) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate fechaNac = LocalDate.parse(fechaNacimiento, formatter);
        LocalDate fechaActual = LocalDate.now();

        // Calcula la diferencia de años entre la fecha actual y la fecha de nacimiento
        int edad = Period.between(fechaNac, fechaActual).getYears();

        return edad; // Devuelve la edad calculada como un entero
    } 

    private String generarContrasenaAleatoria(int longitud) {
        // Genera una contraseña aleatoria de la longitud especificada
        String caracteres = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder contrasena = new StringBuilder();
        for (int i = 0; i < longitud; i++) {
            int indice = random.nextInt(caracteres.length());
            contrasena.append(caracteres.charAt(indice));
        }
        return contrasena.toString();
    }
    

     private void guardarVotantesEnArchivo() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("votantes.txt"))) {
            for (Votante votante : votantes) {
                // Escribe los datos del votante en el archivo
                writer.write(votante.getNombres()+", " + votante.getApellidos()+"," + votante.getCUI()+ votante.getSexo()+","+votante.getFechaNacimiento()+ ","+votante.getDireccion()+","+ votante.getDepartamento()+","+ votante.getMunicipio()+"," +votante.getPais()+","+votante.getCorreoElectronico());
                
                writer.newLine();
              
                writer.write("Elegible: " + votante.esElegible());
             
                writer.write("Contraseña: " + votante.getContrasena());
               
                writer.write("Estado: " + (votante.isFallecido() ? "Fallecido" : "Vivo")); // Agrega el estado del votante
               
                
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Error al guardar los votantes en el archivo.");
        }
    }
    
}
