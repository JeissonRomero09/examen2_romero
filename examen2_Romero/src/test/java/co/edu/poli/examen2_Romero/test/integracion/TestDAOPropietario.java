package co.edu.poli.examen2_Romero.test.integracion;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import co.edu.poli.examen2_Romero.modelo.Propietario;
import co.edu.poli.examen2_Romero.servicios.DAOPropietario;

import java.util.List;

public class TestDAOPropietario {

	DAOPropietario dao = new DAOPropietario();

	// ================= CREATE Y READONE =================
	@Test
	void create_y_readone() {

		String id = "TEST_" + System.currentTimeMillis();

		Propietario p = new Propietario(id, "Carlos Test");

		String result = dao.create(p);

		System.out.println("RESULTADO: " + result);

		assertTrue(result.equalsIgnoreCase("OK") || result.toLowerCase().contains("duplicate"));

		Propietario encontrado = dao.readOne(id);

		assertNotNull(encontrado);
		assertEquals("Carlos Test", encontrado.getNombre());
	}

	// ================= READ ALL NOT NULL =================
	@Test
	void readAll_noDebeRetornarNull() {

		List<Propietario> lista = dao.readAll();

		assertNotNull(lista);
	}

	// ================= VALIDACIÓN DE DATOS =================
	@Test
	void readAll_objetosValidos() {

		List<Propietario> lista = dao.readAll();

		if (!lista.isEmpty()) {

			Propietario p = lista.get(0);

			assertNotNull(p.getId());
			assertNotNull(p.getNombre());
		}
	}
}