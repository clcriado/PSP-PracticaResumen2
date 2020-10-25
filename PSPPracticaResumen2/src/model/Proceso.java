package model;

public class Proceso{
	
	String nombre;
	int tiempoLlegada;
	int rafaga;
	int rafagaInicial;
	int quantum;
	
	
	public int getRafagaInicial() {
		return rafagaInicial;
	}

	public Proceso(String nombre, int tiempoLlegada, int rafaga) {
		this.nombre = nombre;
		this.tiempoLlegada = tiempoLlegada;
		this.rafaga = rafaga;
		this.rafagaInicial = rafaga;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getTiempoLlegada() {
		return tiempoLlegada;
	}

	public void setTiempoLlegada(int tiempoLlegada) {
		this.tiempoLlegada = tiempoLlegada;
	}

	public int getRafaga() {
		return rafaga;
	}

	public void setRafaga(int rafaga) {
		this.rafaga = rafaga;
	}

	public int getQuantum() {
		return quantum;
	}

	public void setQuantum(int quantum) {
		this.quantum = quantum;
	}
	
}
