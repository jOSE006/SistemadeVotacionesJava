/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyectof;
import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AdministradorElecciones {
    private List<Eleccion> elecciones;
    private List<Candidato> candidatos;
    private static int codigoUnicoEleccion = 1;
    private static int codigoUnicoCandidato = 1;

    public AdministradorElecciones() {
        elecciones = new ArrayList<>();
        candidatos = new ArrayList<>();
    }

    public void crearEleccion(Scanner scanner) {
        System.out.print("Ingrese el título de la elección: ");
        String titulo = scanner.nextLine();
        System.out.print("Ingrese el propósito de la elección: ");
        String proposito = scanner.nextLine();
        System.out.print("Ingrese la descripción de la elección: ");
        String descripcion = scanner.nextLine();
        Eleccion nuevaEleccion = new Eleccion(codigoUnicoEleccion, titulo, proposito, descripcion);
        elecciones.add(nuevaEleccion);
        System.out.println("La elección se ha creado con éxito. Código de elección: " + codigoUnicoEleccion);
        codigoUnicoEleccion++;
        // Captura la fecha y hora de inicio de inscripción
        System.out.print("Ingrese la fecha y hora de inicio de inscripción (YYYY-MM-DD HH:mm): ");
        String fechaInicioInscripcionStr = scanner.nextLine();
        LocalDateTime fechaInicioInscripcion = LocalDateTime.parse(fechaInicioInscripcionStr, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        // Captura la fecha y hora de fin de inscripción
        System.out.print("Ingrese la fecha y hora de fin de inscripción (YYYY-MM-DD HH:mm): ");
        String fechaFinInscripcionStr = scanner.nextLine();
        LocalDateTime fechaFinInscripcion = LocalDateTime.parse(fechaFinInscripcionStr, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        // Captura la fecha y hora de inicio de votación
        System.out.print("Ingrese la fecha y hora de inicio de votación (YYYY-MM-DD HH:mm): ");
        String fechaInicioVotacionStr = scanner.nextLine();
        LocalDateTime fechaInicioVotacion = LocalDateTime.parse(fechaInicioVotacionStr, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        // Captura la fecha y hora de fin de votación
        System.out.print("Ingrese la fecha y hora de fin de votación (YYYY-MM-DD HH:mm): ");
        String fechaFinVotacionStr = scanner.nextLine();
        LocalDateTime fechaFinVotacion = LocalDateTime.parse(fechaFinVotacionStr, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        // Setea las fechas y horas de la elección
        nuevaEleccion.setFechaInicioInscripcion(fechaInicioInscripcion);
        nuevaEleccion.setFechaFinInscripcion(fechaFinInscripcion);
        nuevaEleccion.setFechaInicioVotacion(fechaInicioVotacion);
        nuevaEleccion.setFechaFinVotacion(fechaFinVotacion);
        guardarEleccionesEnArchivo();
    }

    // Resto de los métodos
    public Eleccion buscarEleccionPorCodigo(int codigo) {
        for (Eleccion eleccion : elecciones) {
            if (eleccion.getCodigoUnico() == codigo) {
                return eleccion;
            }
        }
        return null; // No se encontró la elección con el código dado.
    }

    public Candidato buscarCandidatoPorCodigo(int codigo) {
        for (Candidato candidato : candidatos) {
            if (candidato.getCodigoUnico() == codigo) {
                return candidato;
            }
        }
        return null; // No se encontró el candidato con el código dado.
    }

    private void guardarEleccionesEnArchivo() {
        try (PrintWriter writer = new PrintWriter(new FileWriter("elecciones.txt"))) {
            for (Eleccion eleccion : elecciones) {
                writer.println(
                        eleccion.getCodigoUnico() + ","
                        + eleccion.getTitulo() + ","
                        + eleccion.getProposito() + ","
                        + eleccion.getDescripcion() + ","
                        + eleccion.getfechaInicioInscripcion() + ","
                        + eleccion.getFechaFinInscripcion() + ","
                        + eleccion.getfechaInicioVotacion() + ","
                        + eleccion.getFechaFinVotacion()
                );
            }
        } catch (IOException e) {
            System.err.println("Error al guardar elecciones en el archivo.");
            e.printStackTrace();
        }
    }


    public void cargarEleccionesDesdeArchivo() {
        try (BufferedReader reader = new BufferedReader(new FileReader("elecciones.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 8) {
                    int codigoUnico = Integer.parseInt(parts[0]);
                    String titulo = parts[1];
                    String proposito = parts[2];
                    String descripcion = parts[3];
                    LocalDateTime fechaInicioInscripcion = LocalDateTime.parse(parts[4]);
                    LocalDateTime fechaFinInscripcion = LocalDateTime.parse(parts[5]);
                    LocalDateTime fechaInicioVotacion = LocalDateTime.parse(parts[6]);
                    LocalDateTime fechaFinVotacion = LocalDateTime.parse(parts[7]);
                    Eleccion eleccion = new Eleccion(codigoUnico, titulo, proposito, descripcion);
                    eleccion.setFechaInicioInscripcion(fechaInicioInscripcion);
                    eleccion.setFechaFinInscripcion(fechaFinInscripcion);
                    eleccion.setFechaInicioVotacion(fechaInicioVotacion);
                    eleccion.setFechaFinVotacion(fechaFinVotacion);
                    elecciones.add(eleccion);
                    if (codigoUnico > codigoUnicoEleccion) {
                        codigoUnicoEleccion = codigoUnico + 1;
                    }
                }
            }
        } catch (IOException e) {
            System.err.println("Error al cargar elecciones desde el archivo.");
            e.printStackTrace();
        }
    }
    public void establecerFechasInscripcion(Scanner scanner, int codigoEleccion) {
        Eleccion eleccionSeleccionada = buscarEleccionPorCodigo(codigoEleccion);
        if (eleccionSeleccionada != null) {
            // Captura la nueva fecha y hora de inicio de inscripción
            System.out.print("Ingrese la nueva fecha y hora de inicio de inscripción (YYYY-MM-DD HH:mm): ");
            String fechaInicioInscripcionStr = scanner.nextLine();
            LocalDateTime fechaInicioInscripcion = LocalDateTime.parse(fechaInicioInscripcionStr, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
            eleccionSeleccionada.setFechaInicioInscripcion(fechaInicioInscripcion);

            // Captura la nueva fecha y hora de fin de inscripción
            System.out.print("Ingrese la nueva fecha y hora de fin de inscripción (YYYY-MM-DD HH:mm): ");
            String fechaFinInscripcionStr = scanner.nextLine();
            LocalDateTime fechaFinInscripcion = LocalDateTime.parse(fechaFinInscripcionStr, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
            eleccionSeleccionada.setFechaFinInscripcion(fechaFinInscripcion);

            guardarEleccionesEnArchivo(); // Guarda los cambios en el archivo
        } else {
            System.out.println("No se encontró ninguna elección con el código ingresado.");
        }
    }

    public void establecerFechasVotacion(Scanner scanner, int codigoEleccion) {
        Eleccion eleccionSeleccionada = buscarEleccionPorCodigo(codigoEleccion);
        if (eleccionSeleccionada != null) {
            // Captura la nueva fecha y hora de inicio de votación
            System.out.print("Ingrese la nueva fecha y hora de inicio de votación (YYYY-MM-DD HH:mm): ");
            String fechaInicioVotacionStr = scanner.nextLine();
            LocalDateTime fechaInicioVotacion = LocalDateTime.parse(fechaInicioVotacionStr, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
            eleccionSeleccionada.setFechaInicioVotacion(fechaInicioVotacion);

            // Captura la nueva fecha y hora de fin de votación
            System.out.print("Ingrese la nueva fecha y hora de fin de votación (YYYY-MM-DD HH:mm): ");
            String fechaFinVotacionStr = scanner.nextLine();
            LocalDateTime fechaFinVotacion = LocalDateTime.parse(fechaFinVotacionStr, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
            eleccionSeleccionada.setFechaFinVotacion(fechaFinVotacion);

            guardarEleccionesEnArchivo(); // Guarda los cambios en el archivo
        } else {
            System.out.println("No se encontró ninguna elección con el código ingresado.");
        }
    }

    public void eliminarEleccion(Scanner scanner, int codigoEleccion) {
    // Buscar la elección por su código único
    Eleccion eleccionSeleccionada = buscarEleccionPorCodigo(codigoEleccion);

    if (eleccionSeleccionada != null) {
        // Verificar si la elección aún no ha comenzado
        LocalDateTime fechaActual = LocalDateTime.now();
        if (fechaActual.isBefore(eleccionSeleccionada.getfechaInicioInscripcion())) {
            // Eliminar la elección si aún no ha comenzado
            elecciones.remove(eleccionSeleccionada);
            System.out.println("La elección ha sido eliminada con éxito.");
            guardarEleccionesEnArchivo(); // Guardar los cambios en el archivo
        } else {
            System.out.println("No se puede eliminar la elección, ya ha comenzado.");
        }
    } else {
        System.out.println("No se encontró ninguna elección con el código ingresado.");
    }
}
    public void guardarCandidatosEnArchivo() {
        try (PrintWriter writer = new PrintWriter(new FileWriter("candidatos.txt"))) {
            for (Candidato candidato : candidatos) {
                writer.println(
                        candidato.getCodigoUnico() + ","
                        + candidato.getNombre() + ","
                        + candidato.getFormacion() + ","
                        + candidato.getExperiencia() + ","
                        + candidato.getApellido() + ","
                        + candidato.getPartidoPolitico()
                );
            }
        } catch (IOException e) {
            System.err.println("Error al guardar candidatos en el archivo.");
            e.printStackTrace();
        }
    }

    public void cargarCandidatosDesdeArchivo() {
        try (BufferedReader reader = new BufferedReader(new FileReader("candidatos.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 6) {
                    int codigoUnico = Integer.parseInt(parts[0]);
                    String nombre = parts[1];
                    String formacion = parts[2];
                    String experiencia = parts[3];
                    String apellido = parts[4];
                    String partidoPolitico = parts[5];
                    Candidato candidato = new Candidato(codigoUnico, nombre, formacion, experiencia, apellido, partidoPolitico);
                    candidatos.add(candidato);
                    if (codigoUnico > codigoUnicoCandidato) {
                        codigoUnicoCandidato = codigoUnico + 1;
                    }
                }
            }
        } catch (IOException e) {
            System.err.println("Error al cargar candidatos desde el archivo.");
            e.printStackTrace();
        }
    }
    public void agregarCandidato(Scanner scanner) {
        System.out.print("Ingrese el nombre del candidato: ");
        String nombre = scanner.nextLine();
        System.out.print("Ingrese el apellido del candidato: ");
        String apellido = scanner.nextLine();
        System.out.print("Ingrese la formación del candidato: ");
        String formacion = scanner.nextLine();
        System.out.print("Ingrese la experiencia del candidato: ");
        String experiencia = scanner.nextLine();
        System.out.print("Ingrese el partido político del candidato: ");
        String partidoPolitico = scanner.nextLine();

        Candidato nuevoCandidato = new Candidato(codigoUnicoCandidato, nombre, formacion, experiencia, apellido, partidoPolitico);
        candidatos.add(nuevoCandidato);
        codigoUnicoCandidato++;

        guardarCandidatosEnArchivo();
    }

    public void modificarCandidato(Scanner scanner, int codigoCandidato) {
        Candidato candidato = buscarCandidatoPorCodigo(codigoCandidato);

        if (candidato != null) {
            System.out.print("Ingrese el nuevo nombre del candidato: ");
            String nuevoNombre = scanner.nextLine();
            System.out.print("Ingrese el nuevo apellido del candidato: ");
            String nuevoApellido = scanner.nextLine();
            System.out.print("Ingrese la nueva formación del candidato: ");
            String nuevaFormacion = scanner.nextLine();
            System.out.print("Ingrese la nueva experiencia del candidato: ");
            String nuevaExperiencia = scanner.nextLine();
            System.out.print("Ingrese el nuevo partido político del candidato: ");
            String nuevoPartidoPolitico = scanner.nextLine();

            candidato.setNombre(nuevoNombre);
            candidato.setApellido(nuevoApellido);
            candidato.setFormacion(nuevaFormacion);
            candidato.setExperiencia(nuevaExperiencia);
            candidato.setPartidoPolitico(nuevoPartidoPolitico);

            guardarCandidatosEnArchivo();
        } else {
            System.out.println("No se encontró ningún candidato con el código ingresado.");
        }
    }

    public void eliminarCandidato(int codigoCandidato) {
        Candidato candidato = buscarCandidatoPorCodigo(codigoCandidato);

        if (candidato != null) {
            candidatos.remove(candidato);
            guardarCandidatosEnArchivo();
            System.out.println("Candidato eliminado con éxito.");
        } else {
            System.out.println("No se encontró ningún candidato con el código ingresado.");
        }
    }
    
    public void configurarCandidatosParaEleccion(Scanner scanner) {
        // Solicitar al administrador que ingrese el código único de la elección a configurar
        System.out.print("Ingrese el código único de la elección que desea configurar: ");
        int codigoEleccion = scanner.nextInt();
        scanner.nextLine();  // Consumir la nueva línea

        // Buscar la elección seleccionada
        Eleccion eleccionSeleccionada = buscarEleccionPorCodigo(codigoEleccion);

        if (eleccionSeleccionada != null) {
            // Verificar si la elección aún no ha comenzado
            LocalDateTime fechaActual = LocalDateTime.now();
            if (fechaActual.isBefore(eleccionSeleccionada.getfechaInicioInscripcion())) {
                // Mostrar los candidatos disponibles
                System.out.println("Candidatos disponibles:");
                for (Candidato candidato : candidatos) {
                    System.out.println(candidato.getCodigoUnico() + ": " + candidato.getNombre());
                }

                // Solicitar al administrador que seleccione candidatos para agregar a la elección
                System.out.print("Ingrese los códigos únicos de los candidatos que desea agregar (separados por comas): ");
                String candidatosSeleccionadosStr = scanner.nextLine();
                String[] codigosCandidatos = candidatosSeleccionadosStr.split(",");

                // Asociar los candidatos seleccionados con la elección
                for (String codigoCandidato : codigosCandidatos) {
                    int codigo = Integer.parseInt(codigoCandidato);
                    Candidato candidato = buscarCandidatoPorCodigo(codigo);
                    if (candidato != null) {
                        eleccionSeleccionada.agregarCandidato(candidato);
                    }
                }

                // Guardar los cambios en un archivo de texto
                guardarEleccionesEnArchivo();

                System.out.println("Candidatos configurados para la elección con éxito.");
            } else {
                System.out.println("No se pueden configurar candidatos para esta elección, ya ha comenzado.");
            }
        } else {
            System.out.println("No se encontró ninguna elección con el código ingresado.");
        }
    }


}

