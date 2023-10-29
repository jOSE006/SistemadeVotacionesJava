/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package proyectof;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import proyectof.SegundoLogin;
import proyectof.RegistradorVotantes;
import proyectof.AdministradorElecciones;
import proyectof.Votante;



public class Proyectof {
    
   
    private static List<user> users = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        int opcion = 0;
  
        
        User1 administrador = new User1("a", "1");
        System.out.print("Nombre de usuario: ");
        String nombreUsuarioIngresado = scanner.nextLine();
        System.out.print("Contraseña: ");
        String contrasenaIngresada = scanner.nextLine();

        if (nombreUsuarioIngresado.equals(administrador.getNombreuser()) &&
                contrasenaIngresada.equals(administrador.getContrasena())) {
            
            boolean salir = false;
          
            while (opcion != 6) {
                System.out.println("\nSelecciona una opción por favor\n");
                System.out.println("1. Registro de Usuarios del Sistema\n");
                System.out.println("2. Modificar Usuarios del Sistema\n");
                System.out.println("3. Eliminar Usuarios del Sistema\n");
                System.out.println("4. Reiniciar Contraseña\n");
                System.out.println("5. Logearte como usuario(administrador,registrador de votantes,aduditor)\n");
                System.out.println("6. Logearte como votante\n");
                System.out.println("7. Salir\n");
                opcion = scanner.nextInt();
                switch (opcion) {
                    case 1:
                        scanner.nextLine(); // Limpia el buffer después de nextInt()
                        registrarUsuario(scanner);
                        break;
                    case 2:
                        cargarUsuariosDesdeArchivo(); // Cargar usuarios desde el archivo antes de modificar
                        modificarUsuario(scanner);
                        break;
                    case 3:
                        cargarUsuariosDesdeArchivo(); // Cargar usuarios desde el archivo antes de eliminar
                        eliminarUsuario(scanner);
                        break;
                    case 4:
                        scanner.nextLine(); // Limpia el buffer después de nextInt()
                        System.out.print("Ingrese su dirección de correo electrónico: ");
                        String correoRecuperacion = scanner.nextLine();

                        // Buscar el usuario por correo electrónico
                        user usuarioRecuperacion = buscarUsuarioPorCorreo(correoRecuperacion);

                        if (usuarioRecuperacion != null) {
                            // Generar una nueva contraseña temporal o permitir al usuario ingresar una nueva contraseña
                            System.out.print("Desea generar una nueva contraseña temporal (Sí/No): ");
                            String respuesta = scanner.nextLine();

                            if (respuesta.equalsIgnoreCase("Sí")) {
                                String nuevaContrasenaTemporal = generarContrasenaTemporal(); // Implementa esta función
                                usuarioRecuperacion.setcontrasena(nuevaContrasenaTemporal);
                                System.out.println("Su nueva contraseña temporal es: " + nuevaContrasenaTemporal);
                            } else {
                                System.out.print("Ingrese su nueva contraseña: ");
                                String nuevaContrasena = scanner.nextLine();
                                usuarioRecuperacion.setcontrasena(nuevaContrasena);
                                System.out.println("Su contraseña ha sido actualizada con éxito.");
                            }

                            // Actualizar la contraseña en el archivo de texto
                            actualizarUsuariosEnArchivo();
                        } else {
                            System.out.println("No se encontró ningún usuario con ese correo electrónico.");
                        }

                        break;
                        case 5: // Opción para el segundo login
                         
                            SegundoLogin segundoLogin = new SegundoLogin(); // Crear instancia de SegundoLogin y pasarle el administrador
                            boolean segundoLoginExitoso = segundoLogin.realizarSegundoLogin(scanner, users);
                            if (!segundoLoginExitoso) {
                                System.out.println("Inicio de sesión fallido.");
                            } else {
                                System.out.println("Inicio de sesión exitoso.");
                            }
                        break;

                    case 6:
                        
                        List<Votante> votantes = new ArrayList<>();

                        try (BufferedReader reader = new BufferedReader(new FileReader("votantes.txt"))) {
                            String linea;
                            while ((linea = reader.readLine()) != null) {
                                // Dividir la línea en campos, asumiendo que está en un formato específico
                                String[] campos = linea.split(",");

                                if (campos.length >= 5) {
                                    String nombres = campos[0];
                                    String apellidos = campos[1];
                                    String cui = campos[2];
                                    String correoElectronico = campos[3];
                                    String contrasena = campos[4];
                                    String sexo = "No especificado";
                                    String fechaNacimiento = "No especificada";
                                    String direccion = "No especificada";
                                    String departamento = "No especificado";
                                    String municipio = "No especificado";
                                    String pais = "No especificado";

                                    // Crea un nuevo votante con valores predeterminados
                                    Votante votante = new Votante(nombres, apellidos, cui, sexo, fechaNacimiento, direccion, departamento, municipio, pais, correoElectronico, contrasena);
                                    votantes.add(votante); // Agrega el votante a la lista
                                }
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                            System.err.println("Error al leer el archivo de votantes.");
                        }

                        System.out.print("Ingrese su correo electrónico: ");
                        String correoIngresado = scanner.next();
                        System.out.print("Ingrese su contraseña: ");
                        String contra = scanner.next();
                        System.out.print("Ingrese su CUI: ");
                        String cuiIngresado = scanner.next();

                        // Autenticar al votante
                        boolean autenticado = false;
                        for (Votante votante : votantes) {
                            if (votante.autenticar(correoIngresado, contra, cuiIngresado)) {
                                autenticado = true;
                                break;
                            }
                        }

                        if (autenticado) {
                            System.out.println("Inicio de sesión exitoso como votante.");
                            // Aquí puedes proporcionar las opciones específicas para votantes.
                        } else {
                            System.out.println("Inicio de sesión fallido. Por favor, verifique sus credenciales.");
                        }
                        break;
                    case 7:
                        System.out.println("Saliendo del programa.");
                        break;
                    default:
                        System.out.println("Opción no válida. Intente de nuevo.");
                        break;
                }

            }
        } else {
            System.out.println("Credenciales incorrectas. No tienes acceso.");
        }
    }

    static class user {
        private String nombre;
        private String apellido;
        private String correo;
        private String contrasena;
        private String roles;
        private boolean habilitado;

        public user(String nombre, String apellido, String correo, String contrasena, String roles) {
            this.nombre = nombre;
            this.apellido = apellido;
            this.correo = correo;
            this.contrasena = contrasena;
            this.roles = roles;
            this.habilitado = habilitado;
        }

        public String getnombre() {
            return nombre;
        }

        public String getapellido() {
            return apellido;
        }

        public String getcorreo() {
            return correo;
        }

        public String getcontrasena() {
            return contrasena;
        }

        public String getroles() {
            return roles;
        }

        public boolean isHabilitado() {
            return habilitado;
        }

        public void setHabilitado(boolean habilitado) {
            this.habilitado = habilitado;
        }

        public void setnombre(String nombre) {
            this.nombre = nombre;
        }

        public void setapellido(String apellido) {
            this.apellido = apellido;
        }

        public void setcorreo(String correo) {
            this.correo = correo;
        }

        public void setcontrasena(String contrasena) {
            this.contrasena = contrasena;
        }
    }

    private static void cargarUsuariosDesdeArchivo() {
        users.clear();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream("users.txt"), "UTF-8"))) {
            String linea;
            System.out.println("Usuarios cargados desde el archivo:");
            while ((linea = reader.readLine()) != null) {
                String[] partes = linea.split(",");
                if (partes.length == 6) {
                    String nombre = partes[0];
                    String apellido = partes[1];
                    String correo = partes[2];
                    String contrasena = partes[3];
                    String roles = partes[4];
                   boolean habilitado = partes[5].equalsIgnoreCase("true"); // Parsear habilitado
                    user usuario = new user(nombre, apellido, correo, contrasena, roles);
                    usuario.setHabilitado(habilitado); // Establecer el usuario como habilitado según el archivo

                    users.add(usuario);

                    // Mostrar los datos del usuario cargado
                    System.out.println("Nombre: " + nombre + ", Apellido: " + apellido
                            + ", Correo: " + correo + ", Roles: " + roles
                            + ", Habilitado: " + (habilitado ? "Si" : "No"));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Error al cargar usuarios desde el archivo.");
        }
    }


    private static void registrarUsuario(Scanner scanner) {
        mostrarListaDeUsuarios();
        System.out.print("Ingrese el nombre: ");
        String nombre = scanner.nextLine();
        System.out.print("Ingrese el apellido: ");
        String apellido = scanner.nextLine();
        System.out.print("Ingrese el correo electrónico: ");
        String correo = scanner.nextLine();
        System.out.print("Ingrese la contraseña: ");
        String contrasena = scanner.nextLine();

        List<String> roles = new ArrayList<>();
        boolean continuar = true;

        while (continuar) {
            System.out.print("Ingrese un rol (o escriba 'fin' para terminar): ");
            String rol = scanner.nextLine();
            if (rol.equalsIgnoreCase("fin")) {
                continuar = false;
            } else {
                roles.add(rol);
            }
        }

        String rolesString = String.join(",", roles);
        user nuevoUsuario = new user(nombre, apellido, correo, contrasena, rolesString);
        nuevoUsuario.setHabilitado(true); // Establecer el usuario como habilitado por defecto
        users.add(nuevoUsuario);
        System.out.println("Usuario registrado con éxito.");

        guardarUsuariosEnArchivo();
    }

    private static void guardarUsuariosEnArchivo() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("users.txt", true))) {
            user nuevoUsuario = users.get(users.size() - 1);
            writer.write(nuevoUsuario.getnombre() + "," + nuevoUsuario.getapellido() + ","
                    + nuevoUsuario.getcorreo() + "," + nuevoUsuario.getcontrasena() + ","
                    + nuevoUsuario.getroles() + "," + nuevoUsuario.isHabilitado() + "\n");
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Error al guardar usuarios en el archivo.");
        }
    }


    private static void modificarUsuario(Scanner scanner) {
    mostrarListaDeUsuarios();
    System.out.print("Seleccione el número de usuario que desea modificar: ");
    int indiceUsuario = scanner.nextInt();

    if (indiceUsuario >= 1 && indiceUsuario <= users.size()) {
        scanner.nextLine(); // Limpia el buffer después de nextInt()
        user usuarioAModificar = users.get(indiceUsuario - 1);

        System.out.println("Modificando usuario: " + usuarioAModificar.getnombre());
        System.out.print("Nuevo nombre: ");
        String nuevoNombre = scanner.nextLine();
        System.out.print("Nuevo apellido: ");
        String nuevoApellido = scanner.nextLine();
        System.out.print("Nuevo correo electrónico: ");
        String nuevoCorreo = scanner.nextLine();
        System.out.print("Nueva contraseña: ");
        String nuevaContrasena = scanner.nextLine();

        // Cambiar el estado de habilitación
        System.out.print("¿Está habilitado? (Si/No): ");
        String respuestaHabilitado = scanner.nextLine();
        boolean nuevoHabilitado = respuestaHabilitado.equalsIgnoreCase("Si");

        // Actualiza los datos del usuario en memoria
        usuarioAModificar.setnombre(nuevoNombre);
        usuarioAModificar.setapellido(nuevoApellido);
        usuarioAModificar.setcorreo(nuevoCorreo);
        usuarioAModificar.setcontrasena(nuevaContrasena);
        usuarioAModificar.setHabilitado(nuevoHabilitado);

        System.out.println("Usuario modificado con éxito.");

        // Actualiza el archivo de texto con la lista completa de usuarios
        actualizarUsuariosEnArchivo();
    } else {
        System.out.println("Número de usuario no válido.");
    }
}

    private static void eliminarUsuario(Scanner scanner) {
        mostrarListaDeUsuarios();
        System.out.print("Seleccione el número de usuario que desea eliminar: ");
        int indiceUsuario = scanner.nextInt();

        if (indiceUsuario >= 1 && indiceUsuario <= users.size()) {
            // Elimina el usuario de la lista en memoria
            users.remove(indiceUsuario - 1);

            System.out.println("Usuario eliminado con éxito.");

            // Actualiza el archivo de texto con la lista actualizada
            actualizarUsuariosEnArchivo();
        } else {
            System.out.println("Número de usuario no válido.");
        }
    }

    private static void actualizarUsuariosEnArchivo() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("users.txt"))) {
            for (user usuario : users) {
                String habilitado = usuario.isHabilitado() ? "true" : "false";
                writer.write(usuario.getnombre() + "," + usuario.getapellido() + ","
                        + usuario.getcorreo() + "," + usuario.getcontrasena() + ","
                        + usuario.getroles() + "," + habilitado + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Error al actualizar usuarios en el archivo.");
        }
    }
    private static void mostrarListaDeUsuarios() {
        System.out.println("Lista de usuarios cargados desde el archivo:");
        for (int i = 0; i < users.size(); i++) {
            user usuario = users.get(i);
            System.out.println((i + 1) + ". Nombre: " + usuario.getnombre() +
                    ", Apellido: " + usuario.getapellido() +
                    ", Correo: " + usuario.getcorreo() +
                    ", Roles: " + usuario.getroles() +
                    ", Habilitado: " + (usuario.isHabilitado() ? "Si" : "No"));
        }
    }
    // Función para buscar un usuario por su correo electrónico

    private static user buscarUsuarioPorCorreo(String correo) {
        for (user usuario : users) {
            if (usuario.getcorreo().equalsIgnoreCase(correo)) {
                return usuario; // Devuelve el usuario encontrado
            }
        }
        return null; // Si no se encuentra ningún usuario con ese correo
    }

// Función para generar una contraseña temporal (puedes personalizarla)
    private static String generarContrasenaTemporal() {
        // Genera una contraseña temporal aleatoria de 8 caracteres (puedes personalizarla)
        String caracteres = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder contrasenaTemporal = new StringBuilder();
        for (int i = 0; i < 8; i++) {
            int indice = (int) (Math.random() * caracteres.length());
            contrasenaTemporal.append(caracteres.charAt(indice));
        }
        return contrasenaTemporal.toString();
    }
    

}




