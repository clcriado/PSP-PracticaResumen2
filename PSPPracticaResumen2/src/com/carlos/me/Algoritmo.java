package com.carlos.me;

import java.util.ArrayList;

import model.FIFO;
import model.Proceso;
import model.RR;
import model.SJF;
import model.SRT;

/**
 * Clase que crea los procesos y los añade a una lista que luego va a entregar
 * a cada uno de los Algoritmos para que sean realizados.
 */
public class Algoritmo {
	
	int numeroProcesos[];
	boolean fifo = true;
	boolean sjf = true;
	boolean srt = true;
	boolean roundRobin = true;
	
	public Algoritmo() {
		numeroProcesos  = new int[] {1,2,3,4,5,6};
	}

	/**
	 * Funcion que ejecuta los algoritmos y les pasa la lista que necesitan para funcionar.
	 */
	public void run() {
		FIFO fifo = new FIFO(crearProcesos());
		fifo.run();
		
		SJF sjf = new SJF(crearProcesos());
		sjf.run();
		
		SRT srt = new SRT(crearProcesos());
		srt.run();
		
		RR rr = new RR(crearProcesos());
		rr.run();
	}

	/**
	 * Funcion que crea los procesos y los añade a una lista devolviendola.
	 */
	private ArrayList<Proceso> crearProcesos() {
		ArrayList<Proceso> listaProcesos = new ArrayList<>();
		
		  Proceso proceso1 = new Proceso("A: Excel",0,3); 
		  Proceso proceso2 = new Proceso("B: Word",1,4); 
		  Proceso proceso3 = new Proceso("C: Office",3,2);
		  Proceso proceso4 = new Proceso("D: Minecraft",5,3); 
		  Proceso proceso5 = new Proceso("E: League Of Legends",6,4);
		 
		
		/*
		 * Proceso proceso1 = new Proceso("A: Excel",0,5); Proceso proceso2 = new
		 * Proceso("B: Word",2,4); Proceso proceso3 = new Proceso("C: Office",3,3);
		 * Proceso proceso4 = new Proceso("D: Minecraft",5,2); Proceso proceso5 = new
		 * Proceso("E: League Of Legends",6,3);
		 */
		
		listaProcesos.add(proceso1);
		listaProcesos.add(proceso2);
		listaProcesos.add(proceso3);
		listaProcesos.add(proceso4);
		listaProcesos.add(proceso5);
		
		
		return listaProcesos;
	}

}
