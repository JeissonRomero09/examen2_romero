package co.edu.poli.examen2_Romero.test.integracion;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import co.edu.poli.examen2_Romero.modelo.*;
import co.edu.poli.examen2_Romero.servicios.*;

public class TestDAOInmueble {

	DAOInmueble dao = new DAOInmueble();
	DAOPropietario daoP = new DAOPropietario();

	// ================= TEST CREATE CASA Y READONE =================

	@Test
	void create_casa_y_readone() {

		Propietario p = new Propietario("900", "Prop Casa");
		daoP.create(p);

		Casa casa = new Casa("900", "2026", true, p, 2);

		String result = dao.create(casa);

		assertNotNull(result);

		Inmueble i = dao.readOne("900");

		assertNotNull(i);
		assertTrue(i instanceof Casa);
	}

	// ================= TEST CREATE APARTAMENTO + READONE =================

	@Test
	void create_apartamento_y_readone() {

		Propietario p = new Propietario("901", "Prop Apto");
		daoP.create(p);

		Apartamento apto = new Apartamento("901", "2026", true, p, 5);

		String result = dao.create(apto);

		assertNotNull(result);

		Inmueble i = dao.readOne("901");

		assertNotNull(i);
		assertTrue(i instanceof Apartamento);
	}

	// ================= TEST READONE NO EXISTE =================

	@Test
	void readone_noExiste() {

		Inmueble i = dao.readOne("0000");

		assertNull(i);
	}
}