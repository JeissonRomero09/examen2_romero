package co.edu.poli.examen2_Romero.test.unitario;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import co.edu.poli.examen2_Romero.modelo.*;

public class DAOTest {

	// ================= TEST CREACIÓN Y VALIDACIÓN DE DATOS =================

	@Test
	void crear_objetos_y_validar_datos() {

		Propietario p = new Propietario("1", "Test");

		Casa casa = new Casa("100", "2026", true, p, 2);
		Apartamento apto = new Apartamento("200", "2025", false, p, 5);

		assertNotNull(casa);
		assertNotNull(apto);

		// ================= VALIDAR DATOS BASE =================
		assertEquals("100", casa.getNumero());
		assertEquals("200", apto.getNumero());

		assertEquals("2026", casa.getFechaCompra());
		assertEquals("2025", apto.getFechaCompra());

		// ================= VALIDAR ESTADO =================
		assertTrue(casa.isEstado());
		assertFalse(apto.isEstado());

		// ================= VALIDAR RELACIÓN =================
		assertEquals(p, casa.getPropietario());
		assertEquals(p, apto.getPropietario());
	}

	// ================= TEST HERENCIA =================

	@Test
	void herencia_funciona_correctamente() {

		Propietario p = new Propietario("2", "Test2");

		Inmueble casa = new Casa("300", "2024", true, p, 3);
		Inmueble apto = new Apartamento("400", "2023", true, p, 7);

		// ================= VALIDAR TIPO =================
		assertTrue(casa instanceof Casa);
		assertTrue(apto instanceof Apartamento);

		// ================= CASTEO =================
		Casa c = (Casa) casa;
		Apartamento a = (Apartamento) apto;

		// ================= VALIDAR ATRIBUTOS ESPECÍFICOS =================
		assertEquals(3, c.getCantidadPisos());
		assertEquals(7, a.getNumeroPiso());
	}
}