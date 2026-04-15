package co.edu.poli.examen2_Romero.servicios;

import java.sql.Connection;
import java.sql.DriverManager;

import io.github.cdimascio.dotenv.Dotenv;

public class ConexionBD {

	private static ConexionBD instancia;
	private Connection conexion;

	private ConexionBD() throws Exception {

		Dotenv dotenv = Dotenv.configure().ignoreIfMissing().load();

		String url = dotenv.get("DB_URL");
		String user = dotenv.get("DB_USER");
		String pass = dotenv.get("DB_PASSWORD");

		Class.forName("com.mysql.cj.jdbc.Driver");
		conexion = DriverManager.getConnection(url, user, pass);

		System.out.println("Conectado a BD ");
	}

	public static ConexionBD getInstancia() throws Exception {
		if (instancia == null) {
			instancia = new ConexionBD();
		}
		return instancia;
	}

	public Connection getConexion() throws Exception {
		if (conexion == null || conexion.isClosed()) {
			instancia = new ConexionBD();
			return instancia.conexion;
		}
		return conexion;
	}
}