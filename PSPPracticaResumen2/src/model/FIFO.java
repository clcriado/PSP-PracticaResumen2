package model;

import java.util.ArrayList;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;

/**
 * Clase que realiza el Algoritmo de First In, First Out,
 * recibe por parámetros una lista de procesos de la cual va a trabajar.
 * 
 */
public class FIFO {
	
	//Lista que recibe los procesos de la clase Algoritmo.
	private ArrayList<Proceso> listaProcesos;
	//Lista que almacena los Indices de Penalizacion en String junto al Proceso correspondiente.
	ArrayList<String> listaPenalizacionString = new ArrayList<>();
	//Lista que almacena los rendimientos en String junto al Proceso correspondiente.
	ArrayList<Double> listaRendimiento = new ArrayList<>();
	
	public FIFO(ArrayList<Proceso> listaProcesos) {
		this.listaProcesos = listaProcesos;
		
	}
	
	/**
	 * Método que ejecuta todo el algoritmo FIFO.
	 */
	public void run() {

		int contadorCiclo = 1;
		System.out.println("FIFO (FIRST IN, FIRST OUT): \n");
		for(int i=0;i<listaProcesos.size();i++) {
			int numeroRafaga = listaProcesos.get(i).getRafaga();
			int numeroRafagaPreview = numeroRafaga;
			for(int j=0;j<numeroRafaga;j++) {
				numeroRafagaPreview -= 1;
				if(numeroRafagaPreview != 0) {
					System.out.println("CICLO " + contadorCiclo + " - Proceso [id=" + listaProcesos.get(i).getNombre() + 
							", rafaga pendiente="+ numeroRafagaPreview+"]");
					contadorCiclo++;
				} else {
					System.out.println("CICLO " + contadorCiclo + " - Proceso [id=" + listaProcesos.get(i).getNombre() + 
							", rafaga pendiente="+ numeroRafagaPreview+"] - TERMINADO");					
					Double resultado = (double) (contadorCiclo-listaProcesos.get(i).getTiempoLlegada())/listaProcesos.get(i).getRafaga();
					Double lastResult = Math.round(resultado * 100.0) / 100.0;
					listaRendimiento.add(lastResult);
					listaPenalizacionString.add(listaProcesos.get(i).getNombre() + ": "+lastResult.toString());
					contadorCiclo++;
					
				}

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
		System.out.println("- Indices de Penalizacion de SJF:");
		for(String s: listaPenalizacionString) {
			System.out.println(s);
		}
	}
	
	/**
	 * Método que escribe por consola el rendimiento que esta en la lista de rendimiento.
	 */
	public void rendimiento() {
		System.out.println("\n- Rendimiento de SJF:");
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
