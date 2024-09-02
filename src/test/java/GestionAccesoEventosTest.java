import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GestionAccesoEventosTest {

    private String[][] personas;

    @BeforeEach
    void setUp() {
        personas = new String[10][5];
        GestionAccesoEventos.agregarPersona(personas, "Francisco", "21", "VIP", "3", "True");
        GestionAccesoEventos.agregarPersona(personas, "Leandro", "19", "VIP", "1", "False");
        GestionAccesoEventos.agregarPersona(personas, "Diego", "20", "General", "2", "True");
        GestionAccesoEventos.agregarPersona(personas, "Matias", "19", "General", "1", "False");
        GestionAccesoEventos.agregarPersona(personas, "Sebastian", "17", "General", "0", "False");
        GestionAccesoEventos.agregarPersona(personas, "Benjamin", "19", "General", "0", "True");
        GestionAccesoEventos.agregarPersona(personas, "Fernanda", "17", "General", "0", "False");
        GestionAccesoEventos.agregarPersona(personas, "Lucas", "15", "False", "0", "False");
        GestionAccesoEventos.agregarPersona(personas, "Maria", "24", "False", "0", "False");
        GestionAccesoEventos.agregarPersona(personas, "Eugenia", "16", "False", "0", "False");
    }

    @Test
    void testAgregarPersona() {
        String[][] personasTest = new String[10][5];
        GestionAccesoEventos.agregarPersona(personasTest, "Test", "23", "VIP", "2", "True");
        assertEquals("Test", personasTest[0][0]);
        assertEquals("23", personasTest[0][1]);
        assertEquals("VIP", personasTest[0][2]);
        assertEquals("2", personasTest[0][3]);
        assertEquals("True", personasTest[0][4]);
    }

    @Test
    void testVerificarEdad() {
        assertDoesNotThrow(() -> GestionAccesoEventos.verificarEdad(personas, "Diego"));
        assertDoesNotThrow(() -> GestionAccesoEventos.verificarEdad(personas, "Sebastian"));
    }

    @Test
    void testVerificarBoleto() {
        assertDoesNotThrow(() -> GestionAccesoEventos.verificarBoleto(personas, "Leandro"));
        assertDoesNotThrow(() -> GestionAccesoEventos.verificarBoleto(personas, "Maria"));
    }

    @Test
    void testValidarInvitados() {
        assertDoesNotThrow(() -> GestionAccesoEventos.validarInvitados(personas, "Francisco"));
        assertDoesNotThrow(() -> GestionAccesoEventos.validarInvitados(personas, "Matias"));
    }

    @Test
    void testMostrarAforoDisponible() {
        assertDoesNotThrow(() -> GestionAccesoEventos.mostrarAforoDisponible(personas));
    }

    @Test
    void testIngresarPersona() {
        int index = encontrarPersona("Leandro");
        assertNotNull(personas[index]);
        assertEquals("False", personas[index][4]);

        GestionAccesoEventos.ingresarPersona(personas, "Leandro");

        assertEquals("True", personas[index][4]);
    }

    @Test
    void testPermitirEntrada() {
        int index = encontrarPersona("Diego");
        assertNotNull(personas[index]);
        assertEquals("True", personas[index][4]);

        GestionAccesoEventos.permitirEntrada(personas, "Diego");

        assertEquals("True", personas[index][4]);
    }

    @Test
    void testRemoverPersona() {
        int index = encontrarPersona("Francisco");
        assertNotNull(personas[index]);
        assertEquals("True", personas[index][4]);

        GestionAccesoEventos.removerPersona(personas, "Francisco");

        assertEquals("False", personas[index][4]);
    }

    private int encontrarPersona(String nombre) {
        for (int i = 0; i < personas.length; i++) {
            if (personas[i][0] != null && personas[i][0].equals(nombre)) {
                return i;
            }
        }
        return -1;
    }
}

