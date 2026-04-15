package co.edu.poli.examen2_Romero.servicios;

import co.edu.poli.examen2_Romero.modelo.Propietario;
import java.sql.*;
import java.util.*;

public class DAOPropietario implements CRUD<Propietario, String> {

	private Connection conn;

	// ================= CONSTRUCTOR =================
	public DAOPropietario() {
		try {
			conn = ConexionBD.getInstancia().getConexion();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// ================= CREATE =================
	@Override
	public String create(Propietario p) {

		try {
			String sql = "INSERT INTO propietario (id, nombre) VALUES (?, ?)";
			PreparedStatement ps = conn.prepareStatement(sql);

			ps.setString(1, p.getId());
			ps.setString(2, p.getNombre());

			ps.executeUpdate();
			return "OK";

		} catch (Exception e) {
			return e.getMessage();
		}
	}

	// ================= READ ONE =================
	@Override
	public Propietario readOne(String id) {

		try {
			String sql = "SELECT * FROM propietario WHERE id=?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, id);

			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				return new Propietario(rs.getString("id"), rs.getString("nombre"));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	// ================= READ ALL =================
	@Override
	public List<Propietario> readAll() {

		List<Propietario> lista = new ArrayList<>();

		try {
			String sql = "SELECT * FROM propietario";
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(sql);

			while (rs.next()) {
				lista.add(new Propietario(rs.getString("id"), rs.getString("nombre")));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return lista;
	}

}