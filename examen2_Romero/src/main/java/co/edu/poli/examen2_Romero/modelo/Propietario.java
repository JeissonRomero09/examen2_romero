package co.edu.poli.examen2_Romero.modelo;

public class Propietario {

	private String id;
	private String nombre;

	public Propietario() {
	}

	public Propietario(String id, String nombre) {
		this.id = id;
		this.nombre = nombre;

	}

	// GETTERS Y SETTERS

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
}