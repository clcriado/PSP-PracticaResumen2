package model;

import java.util.ArrayList;
import java.util.List;

/**
 * Clase que realiza el Algoritmo de selección dependiendo del quantum que se haya establecido,
 * recibe por parámetros una lista de procesos de la cual va a trabajar.
 * 
 */

public class RR {
	//Lista que recibe los procesos de la clase Algoritmo.
	private ArrayList<Proceso> listaProcesos;
	//Lista que almacena los Indices de Penalizacion en String junto al Proceso correspondiente.
	ArrayList<String> listaPenalizacionString = new ArrayList<>();
	//Lista que almacena los rendimientos en String junto al Proceso correspondiente.
	ArrayList<Double> listaRendimiento = new ArrayList<>();
	
	/**
	 * Constructor de la clase RR, recibe por parametros la lista la cual se va a trabajar.
	 * @param ArrayList De la clase Proceso.
	 */
	public RR(ArrayList<Proceso> listaProcesos) {
		this.listaProcesos = listaProcesos;
	}
	
	/**
	 * Método que ejecuta todo el algoritmo RR.
	 */
	public void run() {
		int contadorCiclo = 1;
		System.out.println("RR (Round Robin - Quantum 3): \n");
		while(!listaProcesos.isEmpty()) {
			List<Proceso> listaLlegada = new ArrayList<>();
			for(Proceso p:listaProcesos) {
				if(p.getTiempoLlegada()<=contadorCiclo) {
					listaLlegada.add(p);
				}
			}
			
			int quantum = 3;
			
			Double resultado = 0.0;
			Double lastResult = 0.0;
			
			while(quantum != 0) {
				try {
					listaLlegada.get(0).setRafaga(listaLlegada.get(0).getRafaga()-1);
				} catch(IndexOutOfBoundsException e) {
					
				}
				if(listaLlegada.get(0).getRafaga() !=0) {
					System.out.println("CICLO " + contadorCiclo + " - Proceso [id=" + listaLlegada.get(0).getNombre() + 
							", rafaga pendiente="+ listaLlegada.get(0).getRafaga() +"]");
					contadorCiclo++;
					quantum-=1;
				} else {
					System.out.println("CICLO " + contadorCiclo + " - Proceso [id=" + listaLlegada.get(0).getNombre() + 
							", rafaga pendiente="+ listaLlegada.get(0).getRafaga() +"] - TERMINADO");
					resultado = (double) (contadorCiclo-listaLlegada.get(0).getTiempoLlegada())/listaLlegada.get(0).getRafaga();
					lastResult = Math.round(resultado * 100.0) / 100.0;
					listaRendimiento.add(lastResult);
					listaPenalizacionString.add(listaLlegada.get(0).getNombre() + ": "+lastResult.toString());
					listaProcesos.remove(listaLlegada.get(0));
					contadorCiclo++;
					quantum-=1;
					break;
				}
			}
			if(quantum == 0 && listaLlegada.get(0).getRafaga() != 0) {				
			listaProcesos.add(listaProcesos.get(0));
			listaProcesos.remove(0);
			}
			else if(quantum == 0 && listaLlegada.get(0).getRafaga() == 0) {

				listaRendimiento.add(lastResult);
				listaPenalizacionString.add(listaLlegada.get(0).getNombre() + ": "+lastResult.toString());
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
		System.out.println("- Indices de Penalizacion de RR:");
		listaPenalizacionString.sort(String::compareToIgnoreCase);
		for(String s: listaPenalizacionString) {
			System.out.println(s);
		}
	}
	
	/**
	 * Método que escribe por consola el rendimiento que esta en la lista de rendimiento.
	 */
	public void rendimiento() {
		System.out.println("\n- Rendimiento de RR:");
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
