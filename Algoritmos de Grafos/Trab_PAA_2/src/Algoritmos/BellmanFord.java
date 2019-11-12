package Algoritmos;

import java.util.LinkedList;

import Grafos.Aresta;
import Grafos.Grafo;

public class BellmanFord {
	Grafo grafo;
	int numVer;
	int peso[][];
	Integer d[];

	/**
	 * ! Contrutor da classe
	 * 
	 * @param grafo
	 * 
	 * @Pré-Condição: existe objeto de grafo
	 * @Pós-Condição: valores inicializados
	 */
	public BellmanFord(Grafo grafo) {
		this.grafo = grafo;
		this.numVer = grafo.getNumVer();
		this.peso = new int[this.numVer][this.numVer];
		this.d = new Integer[this.numVer];

		// copia os pesos do grafo
		for (int u = 0; u < this.numVer; u++) {
			LinkedList<Aresta> list = this.grafo.getAdjLists(u);
			for (int v = 0; v < list.size(); v++) {
				int aux = this.grafo.getAdjLists(u).get(v).getSrc();
				int aux2 = this.grafo.getAdjLists(u).get(v).getDest();
				this.peso[aux][aux2] = list.get(v).getPeso();

			}
		}
	}

	/**
	 * ! metodo de relaxamento
	 * 
	 * @param u vértice u
	 * @param v vértice v
	 * 
	 * @Pré-Condição: nenhuma
	 * @Pós-Condição: valores modificados
	 */
	public void relax(int u, int v) {

		// verifica se existe um outro menor caminho passando por determinado vértice
		if (this.d[u] != Integer.MAX_VALUE && this.d[v] > this.d[u] + this.peso[u][v]) {

			d[v] = this.d[u] + this.peso[u][v];
		}

	}

	/**
	 * ! Chama do algoritmo de bellmanFord, menor caminho
	 * 
	 * @param ver vértice original
	 * 
	 * @Pré-Condição: nenhuma
	 * @Pós-Condição: menores caminhos encontrados
	 */
	public boolean bellmanFord(int ver) {
		int aux[] = new int[this.numVer];

		for (int v = 0; v < this.numVer; v++) {
			this.d[v] = Integer.MAX_VALUE;
		}

		this.d[ver] = 0; // distancia da origem dela mesma é zero

		for (int i = 1; i < this.numVer; i++) {
			if (aux[i] == 0) {
				for (int u = 0; u < this.numVer; u++) {
					LinkedList<Aresta> list = this.grafo.getAdjLists(u);
					for (int v = 0; v < list.size(); v++) {
						Aresta aresta = list.get(v);
						relax(aresta.getSrc(), aresta.getDest());
					}
					aux[u] = 1;
				}
			}
		}

		for (int u = 0; u < this.numVer; u++) {
			LinkedList<Aresta> list = this.grafo.getAdjLists(u);
			for (int v = 0; v < list.size(); v++) {
				Aresta aresta = list.get(v);
				int src = aresta.getSrc();
				int dst = aresta.getDest();
				if (d[ver] != Integer.MAX_VALUE && d[dst] > d[src] + peso[src][dst]) {

					System.out.println("Contem ciclo de peso negativo");

					return false; // obedece a condição de relaxamento, retorna falso
					// ciclo negativo, nao pode retornar os menores caminhos
				}
			}
		}

		for (int v = 0; v < numVer; v++) {

			System.out.println("Da vertice = " + this.grafo.getVertices().get(ver).getRotulo() + " para a vertice = "
					+ this.grafo.getVertices().get(v).getRotulo() + " possui d = " + d[v] + "\n");

		}

		return true;

	}

}
