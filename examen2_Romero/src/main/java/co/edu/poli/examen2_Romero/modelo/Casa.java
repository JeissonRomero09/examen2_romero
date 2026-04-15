package co.edu.poli.examen2_Romero.modelo;

public class Casa extends Inmueble {

	private int cantidadPisos;

	public Casa() {
	}

	public Casa(String numero, String fechaCompra, boolean estado, Propietario propietario, int cantidadPisos) {
		super(numero, fechaCompra, estado, propietario);
		this.cantidadPisos = cantidadPisos;
	}

	// GETTERS Y SETTERS

	public int getCantidadPisos() {
		return cantidadPisos;
	}

	public void setCantidadPisos(int cantidadPisos) {
		this.cantidadPisos = cantidadPisos;
	}
}