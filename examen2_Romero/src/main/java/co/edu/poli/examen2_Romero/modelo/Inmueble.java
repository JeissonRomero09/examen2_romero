package co.edu.poli.examen2_Romero.modelo;

public class Inmueble {

	protected String numero;
	protected String fechaCompra;
	protected boolean estado;
	protected Propietario propietario;

	public Inmueble() {
	}

	public Inmueble(String numero, String fechaCompra, boolean estado, Propietario propietario) {
		this.numero = numero;
		this.fechaCompra = fechaCompra;
		this.estado = estado;
		this.propietario = propietario;
	}

	// ================= MÉTODOS DE NEGOCIO =================

	public String vender() {
	    this.estado = false;
	    return "Inmueble vendido";
	}

	public String alquilar() {
	    this.estado = true;
	    return "Inmueble alquilado";
	}

	// GETTERS Y SETTERS

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getFechaCompra() {
		return fechaCompra;
	}

	public void setFechaCompra(String fechaCompra) {
		this.fechaCompra = fechaCompra;
	}

	public boolean isEstado() {
		return estado;
	}

	public void setEstado(boolean estado) {
		this.estado = estado;
	}

	public Propietario getPropietario() {
		return propietario;
	}

	public void setPropietario(Propietario propietario) {
		this.propietario = propietario;
	}
}