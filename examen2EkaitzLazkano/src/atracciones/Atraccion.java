package atracciones;

import java.util.Objects;

public class Atraccion {
	protected String nombre;
	protected int altura;
	protected int capacidad;
	
	public Atraccion(String nombre, int altura, int capacidad) {
		super();
		this.nombre = nombre;
		this.altura = altura;
		this.capacidad = capacidad;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getAltura() {
		return altura;
	}

	public void setAltura(int altura) {
		this.altura = altura;
	}

	public int getCapacidad() {
		return capacidad;
	}

	public void setCapacidad(int capacidad) {
		this.capacidad = capacidad;
	}

	@Override
	public String toString() {
		return "Atraccion [nombre=" + nombre + ", altura=" + altura + ", capacidad=" + capacidad + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(nombre);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Atraccion other = (Atraccion) obj;
		return Objects.equals(nombre, other.nombre);
	}
	
}
