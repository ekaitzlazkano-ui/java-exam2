package atracciones;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.TreeSet;

public class DeustoAventura {

	public static void main(String[] args) {
		// Lista de atracciones de DeustoAventura
		Atraccion[] ats = {
				new Atraccion("Carrusel", 0, 50),
				new Atraccion("Montaña rusa", 140, 25),
				new Atraccion("Autos locos", 100, 35),
				new Atraccion("Mansión embrujada", 120, 15),
				new Atraccion("Water splash", 90, 40)
		};
		ArrayList<Atraccion> atracciones = new ArrayList<Atraccion>(Arrays.asList(ats));

		
		// EJERCICIO 1: Cargar lista de visitantes de DeustoAventura
		ArrayList<Visitante> visitantes = cargaDatosVisitantes();
		System.out.println(visitantes.size()); // debería mostrar 50
		
		TreeSet<Visitante> visitantesOrden = new TreeSet<Visitante>();
		for (Visitante visitante: visitantes) {
			visitantesOrden.add(visitante);
		}
		
		System.out.println(visitantesOrden); // debería mostrar la lista de visitantes ordenada por altura
		System.out.println("---------------------------");
		// EJERCICIO 2: Asignar atracciones a visitantes
		asignarAtracciones(atracciones, visitantes);
		System.out.println(visitantes.get(0)); // debería mostrar un visitante con 3 atracciones asignadas
		
		// EJERCICIO 3: Contar visitantes por tipo de entrada
		HashMap<Entrada, Integer> numeroPorEntrada = contarVisitantes(visitantes);
		System.out.println(numeroPorEntrada); // debería mostrar {VIP=8, EXPRESS=17, NORMAL=25}
		
		// EJERCICIO 4: Agrupar visitantes por atracción
		HashMap<Atraccion, LinkedList<Visitante>> visitantesPorAtraccion = agruparVisitantes(visitantes);
		System.out.println(visitantesPorAtraccion.get(atracciones.get(0)).size()); // debería mostrar un número aleatorio entre 0 y 50 
		
		// EJERCICIO 5: Simular el comportamiento de los visitantes
		simularComportamiento(visitantesPorAtraccion);
		
		// EXTRA: Simular el comportamiento de los visitantes teniendo en cuenta la duración de las atracciones
		//simularComportamientoExtra(visitantesPorAtraccion);
	}

	//ejercicio 1
	public static ArrayList<Visitante> cargaDatosVisitantes() {
		ArrayList<Visitante> result = new ArrayList<Visitante>();
		try {
			File fichero = new File("visitantes.csv");
			Scanner sc = new Scanner(fichero);
			while (sc.hasNextLine()) {
				String linea = sc.nextLine();
				String[] campos = linea.split(";");
				int altura = Integer.parseInt(campos[0]);
				Entrada entrada = Entrada.valueOf(campos[1]);
				result.add(new Visitante(altura, entrada));
			}
			sc.close();
			
		} catch (Exception e) {
			System.out.println("Error al cargar el fichero visitantes.csv");
		}
		return result;
	}

	//ejercicio 2
	public static void asignarAtracciones(ArrayList<Atraccion> atracciones, ArrayList<Visitante> visitantes) {
		for (Visitante visitante: visitantes) {
			while(visitante.getAtracciones().size()<3) {
				int randomId = ((int)(Math.random()*atracciones.size()));
				visitante.getAtracciones().add(atracciones.get(randomId));
			}
		}
	}

	//ejercicio 3
	public static HashMap<Entrada, Integer> contarVisitantes(ArrayList<Visitante> visitantes) {
		HashMap<Entrada, Integer> result = new HashMap<Entrada, Integer>();
		for (Visitante visitante: visitantes) {
			if (!(result.containsKey(visitante.getEntrada()))) {
				result.put(visitante.getEntrada(), 0);
			}
			int valor = result.get(visitante.getEntrada());
			result.put(visitante.getEntrada(), valor+1);
		}
		return result;
	}
	
	//ejercicio 4
	public static HashMap<Atraccion, LinkedList<Visitante>> agruparVisitantes(ArrayList<Visitante> visitantes) {
		HashMap<Atraccion, LinkedList<Visitante>> result = new HashMap<Atraccion, LinkedList<Visitante>>();
		for (Visitante visitante: visitantes) {
			for (Atraccion atraccion: visitante.getAtracciones()) {
				if (!(result.containsKey(atraccion))) {
					result.put(atraccion, new LinkedList<Visitante>());
				}
				result.get(atraccion).addLast(visitante);
			}
		}
		return result;
	}

	//ejercicio 5
	public static void simularComportamiento(HashMap<Atraccion, LinkedList<Visitante>> atraccionesMapa) {
		
		for (Atraccion atraccion: atraccionesMapa.keySet()) {
			
			System.out.println("Atraccion " + atraccion.getNombre());
			
			int counter = atraccion.getCapacidad();
			int cola = atraccionesMapa.get(atraccion).size();
			LinkedList<Visitante> colaLista = atraccionesMapa.get(atraccion);
			
			while (cola > 0) {
				while((cola > 0) && (counter > 0 )) {
					
					for (int i = 0; i< atraccionesMapa.get(atraccion).size();i++) {		//recorre los visitantes de la cola
						if ( colaLista.getFirst().getAltura() >= atraccion.getAltura()) {		//si puede entrar
							System.out.println("Entra " + colaLista.getFirst().getCodigo());
							counter--;
						} else {													//no puede entrar
							System.out.println(colaLista.getFirst().getCodigo() + " no puede entrar por falta de altura");
						}
						cola--;
						atraccionesMapa.get(atraccion).removeFirst();
					}
				}
			counter = atraccion.getCapacidad();
			}
			if (cola > 0) {
				System.out.println("Es necesario otro turno de " + atraccion.getNombre());
			}
		}
	}
	
	
//	public static ArrayList<Visitante> cargaDatosVisitantes2() {
//		ArrayList<Visitante> resultado = new ArrayList<Visitante>();
//		for (int i = 0; i < 50; i++) {
//			Entrada entrada = Entrada.NORMAL;
//			if (i % 3 == 0) {
//				entrada = Entrada.EXPRESS;
//			} else if (i % 4 == 0) {
//				entrada = Entrada.VIP;
//			}  
//			Visitante nuevo = new Visitante((int)(Math.random()*100)+100, entrada);
//			resultado.add(nuevo);
//		}
//		return resultado;
//	}
//	
	
	
}
