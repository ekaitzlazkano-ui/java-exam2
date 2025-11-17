package atracciones;

import java.util.Comparator;
import java.util.HashSet;
import java.util.Objects;

public class Visitante implements Comparable<Visitante>{
	private static int contador = 1;
	
	protected int codigo;
	protected int altura;
	protected Entrada entrada;
	protected HashSet<Atraccion> atracciones;
	
	public Visitante(int altura, Entrada entrada) {
		super();
		this.codigo = Visitante.contador;
		Visitante.contador++;
		this.altura = altura;
		this.entrada = entrada;
		this.atracciones = new HashSet<Atraccion>();
	}

	public int getCodigo() {
		return codigo;
	}

	public int getAltura() {
		return altura;
	}

	public void setAltura(int altura) {
		this.altura = altura;
	}

	public Entrada getEntrada() {
		return entrada;
	}

	public void setEntrada(Entrada entrada) {
		this.entrada = entrada;
	}

	public HashSet<Atraccion> getAtracciones() {
		return atracciones;
	}

	public void setAtracciones(HashSet<Atraccion> atracciones) {
		this.atracciones = atracciones;
	}

	@Override
	public String toString() {
		return "Visitante [codigo=" + codigo + ", altura=" + altura + ", entrada=" + entrada + ", atracciones="
				+ atracciones + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(codigo);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Visitante other = (Visitante) obj;
		return codigo == other.codigo;
	}

	@Override
	public int compareTo(Visitante o) {
		return Integer.compare(this.getAltura(), o.getAltura());
	}
	
	
	

}
