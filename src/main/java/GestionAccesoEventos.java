import java.util.Scanner;

public class GestionAccesoEventos {

    public static void main(String[] args) {
        String[][] personas = new String[10][5];
        agregarPersona(personas, "Francisco", "21", "VIP", "3", "True");
        agregarPersona(personas, "Leandro", "19", "VIP", "1", "False");
        agregarPersona(personas, "Diego", "20", "General", "2", "True");
        agregarPersona(personas, "Matias", "19", "General", "1", "False");
        agregarPersona(personas, "Sebastian", "17", "General", "0", "False");
        agregarPersona(personas, "Benjamin", "19", "General", "0", "True");
        agregarPersona(personas, "Fernanda", "17", "General", "0", "False");
        agregarPersona(personas, "Lucas", "15", "False", "0", "False");
        agregarPersona(personas, "Maria", "24", "False", "0", "False");
        agregarPersona(personas, "Eugenia", "16", "False", "0", "False");

        ejecutarMenu(personas);
    }

    public static void ejecutarMenu(String[][] personas) {
        int opcion;
        do {
            mostrarMenu();
            opcion = leerOpcion();
            ejecutarOpcion(opcion, personas);
        } while (opcion != 8);
    }

    public static void mostrarMenu() {
        System.out.println("\n---Gestión de Acceso a Eventos---");
        System.out.println("1.- Verificar Edad");
        System.out.println("2.- Verificar Boleto de Entrada");
        System.out.println("3.- Validar Invitados");
        System.out.println("4.- Ver Aforo Disponibles");
        System.out.println("5.- Ingresar Persona");
        System.out.println("6.- Permitir Entrada");
        System.out.println("7.- Remover Persona");
        System.out.println("8.- Salir");
        System.out.print("Seleccione una opción: ");
    }

    public static int leerOpcion() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            if (scanner.hasNextInt()) {
                int opcion = scanner.nextInt();
                if (opcion >= 1 && opcion <= 8) {
                    return opcion;
                } else {
                    System.out.println("Opción inválida. Intente de nuevo.");
                }
            } else {
                System.out.println("Opción inválida. Intente de nuevo.");
                scanner.next();
            }
        }
    }

    public static void ejecutarOpcion(int opcion, String[][] personas) {
        //Los nombres de las personas usados para cada opcion son ejemplos
        switch (opcion) {
            case 1:
                verificarEdad(personas, "Diego");
                break;
            case 2:
                verificarBoleto(personas, "Leandro");
                break;
            case 3:
                validarInvitados(personas, "Francisco");
                break;
            case 4:
                mostrarAforoDisponible(personas);
                break;
            case 5:
                ingresarPersona(personas, "Fernanda");
                break;
            case 6:
                permitirEntrada(personas, "Matias");
                break;
            case 7:
                removerPersona(personas, "Benjamin");
                break;
            case 8:
                System.out.println("Saliendo del programa...");
                break;
            default:
                System.out.println("Opción inválida.");
                break;
        }
    }

    public static void agregarPersona(String[][] personas, String nombre, String edad, String boleto, String invitados, String ingresado) {
        for (int i = 0; i < personas.length; i++) {
            if (personas[i][0] == null) {
                personas[i][0] = nombre;
                personas[i][1] = edad;
                personas[i][2] = boleto;
                personas[i][3] = invitados;
                personas[i][4] = ingresado;
                break;
            }
        }
    }

    public static void verificarEdad(String[][] personas, String nombre) {
        for (String[] persona : personas) {
            if (persona[0] != null && persona[0].equals(nombre)) {
                int edad = Integer.parseInt(persona[1]);
                if (edad >= 18) {
                    System.out.println(nombre + " es mayor de edad.");
                } else {
                    System.out.println(nombre + " es menor de edad.");
                }
                return;
            }
        }
        System.out.println("No se encontró a la persona.");
    }

    public static void verificarBoleto(String[][] personas, String nombre) {
        for (String[] persona : personas) {
            if (persona[0] != null && persona[0].equals(nombre)) {
                switch (persona[2]) {
                    case "VIP":
                        System.out.println(nombre + " tiene boleto VIP.");
                        break;
                    case "General":
                        System.out.println(nombre + " tiene boleto general.");
                        break;
                    default:
                        System.out.println(nombre + " no tiene boleto.");
                        break;
                }
                return;
            }
        }
        System.out.println("No se encontró a la persona.");
    }

    public static void validarInvitados(String[][] personas, String nombre) {
        for (String[] persona : personas) {
            if (persona[0] != null && persona[0].equals(nombre)) {
                if (persona[2].equals("VIP")) {
                    int invitados = Integer.parseInt(persona[3]);
                    if (invitados > 2) {
                        System.out.println(nombre + " excede el número de invitados.");
                    } else {
                        System.out.println(nombre + " tiene " + invitados + " invitados.");
                    }
                } else {
                    System.out.println(nombre + " no tiene derecho a invitados.");
                }
                return;
            }
        }
        System.out.println("No se encontró a la persona.");
    }

    public static void mostrarAforoDisponible(String[][] personas) {
        int aforoMaxVIP = 5;
        int aforoMaxGeneral = 10;
        int aforoVIP = aforoMaxVIP;
        int aforoGeneral = aforoMaxGeneral;

        for (String[] persona : personas) {
            if (persona[4] != null && persona[4].equals("True")) {
                if (persona[2].equals("VIP")) {
                    aforoVIP--;
                    int invitados = Integer.parseInt(persona[3]);
                    if (invitados > 0 && invitados < 3) {
                        aforoVIP -= invitados;
                    }
                } else if (persona[2].equals("General")) {
                    aforoGeneral--;
                }
            }
        }

        System.out.println("Aforo disponible para VIP: " + aforoVIP);
        System.out.println("Aforo disponible para General: " + aforoGeneral);
    }

    public static void ingresarPersona(String[][] personas, String nombre) {
        for (String[] persona : personas) {
            if (persona[0] != null && persona[0].equals(nombre)) {
                if (persona[4].equals("False")) {
                    persona[4] = "True";
                    System.out.println(nombre + " ha sido ingresado al evento.");
                } else {
                    System.out.println(nombre + " ya está ingresado en el evento.");
                }
                return;
            }
        }
        System.out.println("No se encontró a la persona.");
    }

    public static void permitirEntrada(String[][] personas, String nombre) {
        int index = encontrarPersona(nombre, personas);

        if (index == -1) {
            System.out.println("No se encontró a la persona.");
            return;
        }

        if (!esMayor(index, personas)) {
            System.out.println(nombre + " no es mayor de edad.");
            return;
        }

        if (personas[index][4].equals("True")) {
            System.out.println(nombre + " ya está ingresado.");
            return;
        }

        String tipoEntrada = personas[index][2];
        if (aforoDisponible(tipoEntrada, personas) <= 0) {
            System.out.println("Aforo " + tipoEntrada + " completo.");
            return;
        }

        personas[index][4] = "True";
        System.out.println("Entrada permitida para " + nombre + ".");
    }

    public static int aforoDisponible(String tipoEntrada, String[][] personas) {
        int aforoMaxVIP = 5;
        int aforoMaxGeneral = 10;
        int aforoVIP = aforoMaxVIP;
        int aforoGeneral = aforoMaxGeneral;

        for (String[] persona : personas) {
            if (persona[4] != null && persona[4].equals("True")) {
                if (persona[2].equals("VIP")) {
                    aforoVIP--;
                    int invitados = Integer.parseInt(persona[3]);
                    aforoVIP -= invitados;
                } else if (persona[2].equals("General")) {
                    aforoGeneral--;
                }
            }
        }

        switch (tipoEntrada) {
            case "VIP":
                return aforoVIP;
            case "General":
                return aforoGeneral;
            default:
                return -1;
        }
    }

    private static int encontrarPersona(String nombre, String[][] personas) {
        for (int i = 0; i < personas.length; i++) {
            if (personas[i][0] != null && personas[i][0].equals(nombre)) {
                return i;
            }
        }
        return -1;
    }

    private static boolean esMayor(int index, String[][] personas) {
        return Integer.parseInt(personas[index][1]) >= 18;
    }

    public static void removerPersona(String[][] personas, String nombre) {
        int index = encontrarPersona(nombre, personas);

        if (index == -1) {
            System.out.println("No se encontró a la persona con el nombre " + nombre);
            return;
        }

        if (personas[index][4].equals("False")) {
            System.out.println(nombre + " no está ingresado.");
            return;
        }

        personas[index][4] = "False";
        System.out.println(nombre + " ha sido removido del evento.");

        if (personas[index][2].equals("VIP")) {
            int invitados = Integer.parseInt(personas[index][3]);
            for (String[] persona : personas) {
                if (persona[0] != null && !persona[0].equals(nombre) &&
                        persona[2].equals("VIP") &&
                        persona[4].equals("True") &&
                        Integer.parseInt(persona[3]) > 0) {
                    int posiblesInvitados = Integer.parseInt(persona[3]);
                    int aRemover = Math.min(posiblesInvitados, invitados);
                    persona[3] = Integer.toString(posiblesInvitados - aRemover);
                    invitados -= aRemover;
                    if (invitados <= 0) break;
                }
            }
        }
    }
}
