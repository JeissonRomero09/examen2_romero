package co.edu.poli.examen2_Romero.modelo;

public class Apartamento extends Inmueble {

	private int numeroPiso;

	public Apartamento() {
	}

	public Apartamento(String numero, String fechaCompra, boolean estado, Propietario propietario, int numeroPiso) {
		super(numero, fechaCompra, estado, propietario);
		this.numeroPiso = numeroPiso;
	}

	// GETTERS Y SETTERS

	public int getNumeroPiso() {
		return numeroPiso;
	}

	public void setNumeroPiso(int numeroPiso) {
		this.numeroPiso = numeroPiso;
	}
}