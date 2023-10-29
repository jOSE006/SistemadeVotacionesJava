/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyectof;

import java.util.List;
import java.util.Scanner;
import proyectof.Proyectof.user;



public class SegundoLogin {
    public boolean realizarSegundoLogin(Scanner scanner, List<user> users) {
        System.out.print("Nombre de usuario: ");
        String nombreUsuarioIngresado = scanner.next();
        System.out.print("Contraseña: ");
        String contrasenaIngresada = scanner.next();

        // Verificar si el usuario existe y las credenciales son válidas
        user usuario = buscarUsuarioPorNombre(users, nombreUsuarioIngresado);
        if (usuario != null && usuario.getcontrasena().equals(contrasenaIngresada)) {
            // Verificar los roles del usuario
            String roles = usuario.getroles();
            if (roles.contains("Administrador")) {
                // Interfaz de administrador
                System.out.println("Bienvenido, Administrador.");
                // Declarar e inicializar un objeto AdministradorElecciones
                AdministradorElecciones admin = new AdministradorElecciones();

                // Crear un menú de opciones para el administrador
                boolean salir = false;
               
                while (!salir) {
                    System.out.println("Seleccione una opción:");
                    System.out.println("1. Crear nueva elección");
                    System.out.println("2. Establecer fechas de inscripción");
                    System.out.println("3. Establecer fechas de votación");
                    System.out.println("4. Eliminar elección");
                    System.out.println("5. Agregar candidato");
                    System.out.println("6. Modificar candidato");
                    System.out.println("7. Eliminar candidato");
                    System.out.println("8. Configurar candidatos para elección");
                    System.out.println("9. Salir");

                    int opcion = scanner.nextInt();
                    scanner.nextLine();  // Consumir la nueva línea

                    switch (opcion) {
                        case 1:
                            admin.crearEleccion(scanner);
                            break;
                        case 2:
                            System.out.print("Ingrese el código de la elección: ");
                            int codigoEleccion = scanner.nextInt();
                            scanner.nextLine();  // Consumir la nueva línea
                            admin.establecerFechasInscripcion(scanner, codigoEleccion);
                            break;
                        case 3:
                            System.out.print("Ingrese el código de la elección: ");
                            codigoEleccion = scanner.nextInt();
                            scanner.nextLine();  // Consumir la nueva línea
                            admin.establecerFechasVotacion(scanner, codigoEleccion);
                            break;
                        case 4:
                            System.out.print("Ingrese el código de la elección a eliminar: ");
                            codigoEleccion = scanner.nextInt();
                            scanner.nextLine();  // Consumir la nueva línea
                            admin.eliminarEleccion(scanner, codigoEleccion);
                            break;
                        case 5:
                            admin.agregarCandidato(scanner);
                            break;
                        case 6:
                            System.out.print("Ingrese el código del candidato a modificar: ");
                            int codigoCandidato = scanner.nextInt();
                            scanner.nextLine();  // Consumir la nueva línea
                            admin.modificarCandidato(scanner, codigoCandidato);
                            break;
                        case 7:
                            System.out.print("Ingrese el código del candidato a eliminar: ");
                            codigoCandidato = scanner.nextInt();
                            scanner.nextLine();  // Consumir la nueva línea
                            admin.eliminarCandidato(codigoCandidato);
                            break;
                        case 8:
                            admin.configurarCandidatosParaEleccion(scanner);
                            break;
                        case 9:
                            salir = true;
                            break;
                        default:
                            System.out.println("Opción no válida.");
                    }
                }
                
            } else if (roles.contains("Registrador de Votantes")) {
                // Interfaz de registrador de votantes
                System.out.println("Bienvenido, Registrador de Votantes.");
                // Puedes agregar aquí las opciones específicas del registrador de votantes
                RegistradorVotantes registradorVotantes = new RegistradorVotantes();
                while (true) {
                    // Mostrar el menú de opciones para el registrador de votantes
                    System.out.println("Opciones disponibles:");
                    System.out.println("1. Registrar Votante");
                    System.out.println("2. Modificar Datos de Votante");
                    System.out.println("3. Generar Nueva Contraseña");
                    System.out.println("4. Dar de Baja Votante");
                    System.out.println("5. Salir");

                    System.out.print("Seleccione una opción: ");
                    int opcion = scanner.nextInt();
                    scanner.nextLine(); // Limpiar el salto de línea

                    switch (opcion) {
                        case 1:
                            registradorVotantes.registrarVotante(scanner);
                            break;
                        case 2:
                            registradorVotantes.modificarDatosVotante(scanner);
                            break;
                        case 3:
                            registradorVotantes.generarNuevaContrasena(scanner);
                            break;
                        case 4:
                            registradorVotantes.darDeBajaVotante(scanner);
                            break;
                        case 5:
                            System.out.println("Saliendo del sistema.");
                            scanner.close();
                            System.exit(0);
                        default:
                            System.out.println("Opción no válida. Intente de nuevo.");
                            break;
                    }
                }
            } else if (roles.contains("Auditor")) {
                // Interfaz de auditor
                System.out.println("Bienvenido, Auditor.");
                // Puedes agregar aquí las opciones específicas del auditor
            } else {
                System.out.println("Inicio de sesión exitoso.");
            }
            return true; // Inicio de sesión exitoso
        } else {
            return false; // Inicio de sesión fallido
        }
        
        
        
    }

    private user buscarUsuarioPorNombre(List<user> users, String nombreUsuario) {
        for (user usuario : users) {
            if (usuario.getnombre().equalsIgnoreCase(nombreUsuario)) {
                return usuario; // Devuelve el usuario encontrado
            }
        }
         // Si no se encuentra ningún usuario con ese nombre de usuario
        return null;
    }
    
 
    
}

