package model;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Clase que realiza el Algoritmo de Shortest Remaining Time,
 * recibe por parámetros una lista de procesos de la cual va a trabajar.
 * 
 */
public class SRT {
	//Lista que recibe los procesos de la clase Algoritmo.
	private ArrayList<Proceso> listaProcesos;
	//Lista que almacena los Indices de Penalizacion en String junto al Proceso correspondiente.
	ArrayList<String> listaPenalizacionString = new ArrayList<>();
	//Lista que almacena los rendimientos en String junto al Proceso correspondiente.
	ArrayList<Double> listaRendimiento = new ArrayList<>();
	
	public SRT(ArrayList<Proceso> listaProcesos) {
		this.listaProcesos = listaProcesos;
	}
	
	/**
	 * Método que ejecuta todo el algoritmo SRT.
	 */
	public void run() {
		int contadorCiclo = 1;
		System.out.println("SRT (SHORTEST REMAINING TIME): \n");
		while(!listaProcesos.isEmpty()) {
			List<Proceso> listaLlegada = new ArrayList<>();
			
			for(Proceso p:listaProcesos) {
				if(p.getTiempoLlegada()<=contadorCiclo) {
					listaLlegada.add(p);
				}

			}
			try {
			listaLlegada.sort(Comparator.comparing(Proceso::getRafaga));
			
			Integer rafaga = listaLlegada.get(0).getRafaga() - 1;
			
			while(rafaga != null) {
				if(rafaga!=0) {
					for(Proceso p: listaLlegada) {
						if(p.getRafaga() < rafaga) {
							listaLlegada.set(0, p);
						}
					}
					System.out.println("CICLO " + contadorCiclo + " - Proceso [id=" + listaLlegada.get(0).getNombre() + 
							", rafaga pendiente="+ rafaga +"]");
					contadorCiclo++;
					rafaga -=1;
				} else {
					System.out.println("CICLO " + contadorCiclo + " - Proceso [id=" + listaLlegada.get(0).getNombre() + 
							", rafaga pendiente="+ rafaga +"] - TERMINADO");
					Double resultado = (double) (contadorCiclo-listaLlegada.get(0).getTiempoLlegada())/listaLlegada.get(0).getRafaga();
					Double lastResult = Math.round(resultado * 100.0) / 100.0;
					listaRendimiento.add(lastResult);
					listaPenalizacionString.add(listaLlegada.get(0).getNombre() + ": "+lastResult.toString());
					contadorCiclo++;
					rafaga = null;
				}
			}

			listaProcesos.remove(listaLlegada.get(0));

			} catch(IndexOutOfBoundsException e) {
				System.out.println(e);
			}
		}
		indicePenalizacion();
		rendimiento();
	}
	
	/**
	 * Método que escribe por consola los indices de penalizacion que estan en la lista de penalizaciones EN String.
	 */
	public void indicePenalizacion() {
		System.out.println();
		System.out.println("- Indices de Penalizacion de SRT:");
		listaPenalizacionString.sort(String::compareToIgnoreCase);
		for(String s: listaPenalizacionString) {
			System.out.println(s);
		}
	}
	
	/**
	 * Método que escribe por consola el rendimiento que esta en la lista de rendimiento.
	 */
	public void rendimiento() {
		System.out.println("\n- Rendimiento de SRT:");
		Double resultadoRendimiento = 0.0;
		for(Double d:listaRendimiento) {
			resultadoRendimiento+=d;
		}
		Double lastResulte = (double) resultadoRendimiento/listaRendimiento.size();
		Double lastResultee = Math.round(lastResulte * 100.0) / 100.0;

		System.out.println(lastResultee + "\n");
		System.out.println("--------------------\n");
	}

}
