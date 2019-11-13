package Grafos;
import java.util.ArrayList;
import java.util.LinkedList;

public class Grafo implements Cloneable{

	private boolean orientado; // true para orientado e false para n�o orientado
	private int numVer; // quantidade de vertices
	private ArrayList<Vertice> vertices; // lista de todos os v�rtices
	private LinkedList<Aresta> adjLists[]; // lista de adjac�ncia

	public Grafo() {}
	/**!
	 * Construtor da classe Grafo, inicializa valores
	 * 
	 * @param numVer
	 * @param orientado
	 * 
	 * @Pr�-Condi��o: nenhuma
	 * @P�s-Condi��o: valores inicializados
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
	 * @param peso   valor da dist�ncia (aresta) entre v�rtices
	 * @param rotulo nome da aresta
	 * @param src    v�rtice de inicio
	 * @param dest   v�rtice de fim
	 * 
	 * @Pr�-Condi��o: nenhuma
	 * @P�s-Condi��o: aresta criada e adicionada na lista de adjac�ncia
	 */
	public void addAresta(int peso, String rotulo, int src, int dest) {
		// quando n�o orientado as listas precisam dos valores inversos tamb�m
		if (orientado == false) {
			Aresta aresta = new Aresta(peso, rotulo, dest, src);
			adjLists[dest].addFirst(aresta);			
		}
		Aresta aresta = new Aresta(peso, rotulo, src, dest);
		adjLists[src].addFirst(aresta);
	
	}

	/**!
	 * Adiciona v�rtice no grafo
	 * 
	 * @param rotulo
	 * 
	 * @Pr�-Condi��o: nenhuma
	 * @P�s-Condi��o: v�rtice criada e adicionada no array list
	 */
	public void addVertice(String rotulo) {
		Vertice vertice = new Vertice(rotulo);
		vertices.add(vertice);
	}

	/**
	 * Impress�o das rela��es do grafo para teste
	 * 
	 * @Pr�-Condi��o: grafo com valores
	 * @P�s-Condi��o: rela��es impressos
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
