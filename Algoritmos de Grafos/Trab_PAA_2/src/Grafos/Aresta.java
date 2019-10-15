package Grafos;

public class Aresta {

	private int peso;
	private String rotulo;
	private int src, dest;

	public Aresta () {}
	
	/**
	 * Construtor da classe Aresta
	 * 
	 * @param peso   valor da distância (aresta) entre vértices
	 * @param rotulo nome da aresta
	 * @param src    vértice de inicio
	 * @param dest   vértice de fim
	 * 
	 * @Pré-Condição: nenhuma
	 * @Pós-Condição: valores inicializados
	 */
	public Aresta(int peso, String rotulo, int src, int dest) {
		this.peso = peso;
		this.rotulo = rotulo;
		this.src = src;
		this.dest = dest;
	}

	public int getPeso() {
		return peso;
	}

	public void setPeso(int peso) {
		this.peso = peso;
	}

	public String getRotulo() {
		return rotulo;
	}

	public void setRotulo(String rotulo) {
		this.rotulo = rotulo;
	}

	public int getSrc() {
		return src;
	}

	public void setSrc(int src) {
		this.src = src;
	}

	public int getDest() {
		return dest;
	}

	public void setDest(int dest) {
		this.dest = dest;
	}
}
