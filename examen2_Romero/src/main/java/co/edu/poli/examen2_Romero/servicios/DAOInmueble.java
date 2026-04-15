package co.edu.poli.examen2_Romero.servicios;

import co.edu.poli.examen2_Romero.modelo.*;
import java.sql.*;
import java.util.*;

public class DAOInmueble implements CRUD<Inmueble, String> {

	private Connection conn;
	private DAOPropietario daoP;

	public DAOInmueble() {
		try {
			conn = ConexionBD.getInstancia().getConexion();
			daoP = new DAOPropietario();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// ================= UPDATE =================

	public String update(Inmueble i) {
		try {
			String sql = "UPDATE inmueble SET estado=? WHERE numero=?";
			PreparedStatement ps = conn.prepareStatement(sql);

			ps.setBoolean(1, i.isEstado());
			ps.setString(2, i.getNumero());

			ps.executeUpdate();
			return "OK";

		} catch (Exception e) {
			return e.getMessage();
		}
	}
	// ================= CREATE =================

	@Override
	public String create(Inmueble i) {

		try {
			String sql = "INSERT INTO inmueble VALUES (?, ?, ?, ?, ?, ?, ?)";
			PreparedStatement ps = conn.prepareStatement(sql);

			ps.setString(1, i.getNumero());
			ps.setString(2, i.getFechaCompra());
			ps.setBoolean(3, i.isEstado());
			ps.setString(4, i.getPropietario().getId());
			ps.setString(5, i.getClass().getSimpleName());

			if (i instanceof Casa) {
				ps.setInt(6, ((Casa) i).getCantidadPisos());
				ps.setNull(7, Types.INTEGER);
			} else if (i instanceof Apartamento) {
				ps.setNull(6, Types.INTEGER);
				ps.setInt(7, ((Apartamento) i).getNumeroPiso());
			} else {
				ps.setNull(6, Types.INTEGER);
				ps.setNull(7, Types.INTEGER);
			}

			ps.executeUpdate();
			return "OK";

		} catch (Exception e) {
			return e.getMessage();
		}
	}

	// ================= READ ONE =================

	@Override
	public Inmueble readOne(String numero) {

		try {
			String sql = "SELECT * FROM inmueble WHERE numero=?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, numero);

			ResultSet rs = ps.executeQuery();

			if (rs.next()) {

				Propietario p = daoP.readOne(rs.getString("propietario_id"));

				String tipo = rs.getString("tipo");
				String fecha = rs.getString("fechaCompra");
				boolean estado = rs.getBoolean("estado");

				if (tipo.equals("Casa")) {

					return new Casa(rs.getString("numero"), fecha, estado, p, rs.getInt("numero_pisos"));

				} else if (tipo.equals("Apartamento")) {

					return new Apartamento(rs.getString("numero"), fecha, estado, p, rs.getInt("numero_piso"));
				}

				return new Inmueble(numero, fecha, estado, p);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	// ================= READ ALL =================

	@Override
	public List<Inmueble> readAll() {

		List<Inmueble> lista = new ArrayList<>();

		try {
			String sql = "SELECT * FROM inmueble";
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(sql);

			while (rs.next()) {

				Propietario p = daoP.readOne(rs.getString("propietario_id"));

				String tipo = rs.getString("tipo");
				String fecha = rs.getString("fechaCompra");
				boolean estado = rs.getBoolean("estado");

				if (tipo.equals("Casa")) {

					lista.add(new Casa(rs.getString("numero"), fecha, estado, p, rs.getInt("numero_pisos")));

				} else if (tipo.equals("Apartamento")) {

					lista.add(new Apartamento(rs.getString("numero"), fecha, estado, p, rs.getInt("numero_piso")));

				} else {

					lista.add(new Inmueble(rs.getString("numero"), fecha, estado, p));
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return lista;
	}
}