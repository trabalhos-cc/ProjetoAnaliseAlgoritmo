package Grafos;
import java.util.ArrayList;
import java.util.LinkedList;

public class Grafo implements Cloneable{

	private boolean orientado; // true para orientado e false para não orientado
	private int numVer; // quantidade de vertices
	private ArrayList<Vertice> vertices; // lista de todos os vértices
	private LinkedList<Aresta> adjLists[]; // lista de adjacência

	public Grafo() {}
	/**!
	 * Construtor da classe Grafo, inicializa valores
	 * 
	 * @param numVer
	 * @param orientado
	 * 
	 * @Pré-Condição: nenhuma
	 * @Pós-Condição: valores inicializados
	 */
	public Grafo(int numVer, boolean orientado) {
		this.orientado = orientado;
		this.numVer = numVer;
		vertices = new ArrayList<Vertice>();
		adjLists = new LinkedList[this.numVer];
		for (int i = 0; i < numVer; i++) {
			adjLists[i] = new LinkedList<>();
		}
	}

	/**!
	 * Adiciona aresta no grafo
	 * 
	 * @param peso   valor da distância (aresta) entre vértices
	 * @param rotulo nome da aresta
	 * @param src    vértice de inicio
	 * @param dest   vértice de fim
	 * 
	 * @Pré-Condição: nenhuma
	 * @Pós-Condição: aresta criada e adicionada na lista de adjacência
	 */
	public void addAresta(int peso, String rotulo, int src, int dest) {
		// quando não orientado as listas precisam dos valores inversos também
		if (orientado == false) {
			Aresta aresta = new Aresta(peso, rotulo, dest, src);
			adjLists[dest].addFirst(aresta);			
		}
		Aresta aresta = new Aresta(peso, rotulo, src, dest);
		adjLists[src].addFirst(aresta);
	
	}

	/**!
	 * Adiciona vértice no grafo
	 * 
	 * @param rotulo
	 * 
	 * @Pré-Condição: nenhuma
	 * @Pós-Condição: vértice criada e adicionada no array list
	 */
	public void addVertice(String rotulo) {
		Vertice vertice = new Vertice(rotulo);
		vertices.add(vertice);
	}

	/**
	 * Impressão das relações do grafo para teste
	 * 
	 * @Pré-Condição: grafo com valores
	 * @Pós-Condição: relações impressos
	 */
	public void printGrafo() {
		for (int i = 0; i < numVer; i++) {
			LinkedList<Aresta> list = adjLists[i];
			
			System.out.println("\n");
						
			for (int j = 0; j < list.size(); j++) {
				System.out.println("vertice " + vertices.get(i).getRotulo() + " conectado a "
						+ vertices.get(list.get(j).getDest()).getRotulo() + " com distancia " + list.get(j).getPeso());
			}
		}
	}

	@Override
	public Grafo clone() throws CloneNotSupportedException {
		return (Grafo) super.clone();
	}

	public int getNumVer() {
		return numVer;
	}

	public void setNumVer(int numVer) {
		this.numVer = numVer;
	}

	public boolean isOrientado() {
		return orientado;
	}

	public void setOrientado(boolean orientado) {
		this.orientado = orientado;
	}

	public ArrayList<Vertice> getVertices() {
		return vertices;
	}

	
	public void setVertices(ArrayList<Vertice> vertices) {
		this.vertices = vertices;
	}

	public LinkedList<Aresta> getAdjLists(int u) {
		return adjLists[u];
	}

	public void setAdjLists(LinkedList<Aresta>[] adjLists) {
		this.adjLists = adjLists;
	}

	public boolean isVazia(int u) {
		if(this.adjLists[u] == null){
			return true;
		}
		return false;
	}

}
