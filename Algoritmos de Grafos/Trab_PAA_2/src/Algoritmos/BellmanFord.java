package Algoritmos;

import java.util.LinkedList;

import Grafos.Aresta;
import Grafos.Grafo;

public class BellmanFord {
	Grafo grafo;
	int numVer;
	int peso[][];
	Integer d[];

	/**!
	 * Contrutor da classe
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

		//copia os pesos do grafo
		for (int u = 0; u < this.numVer; u++) {
			LinkedList<Aresta> list = this.grafo.getAdjLists(u);
			for (int v = 0; v < list.size(); v++) {
				int aux = this.grafo.getAdjLists(u).get(v).getSrc();
				int aux2 = this.grafo.getAdjLists(u).get(v).getDest();
				this.peso[aux][aux2] = list.get(v).getPeso();

			}
		}
	}

	/**!
	 * metodo de relaxamento
	 * 
	 * @param u vértice u
	 * @param v vértice v
	 * 
	 * @Pré-Condição: nenhuma
	 * @Pós-Condição: valores modificados
	 */
	public void relax(int u, int v) {

		System.out.println("peso= " + peso[u][v] + " d[u]"+ d[u]);

		//verifica se existe um outro menor caminho passando por determinado vértice
		if(this.d[v] > this.d[u] + this.peso[u][v]) {

			System.out.println("u e v = " + u + " " + v);
			System.out.println(this.d[u] + " " + this.d[v]);

			if(this.d[v] != this.d[u]) {
				d[v] = this.d[u] + this.peso[u][v];
				System.out.println("soma = " + (this.d[u] + this.peso[u][v]) + "\n");
			}
		}

	}

	/**!
	 * Chama do algoritmo de bellmanFord, menor caminho
	 * 
	 * @param ver vértice original
	 * 
	 * @Pré-Condição: nenhuma
	 * @Pós-Condição: menores caminhos encontrados
	 */
	public boolean bellmanFord(int ver) {
		int aux[] = new int[this.numVer];

		for(int v = 0; v < this.numVer; v++) {
			this.d[v] = Integer.MAX_VALUE;
		}

		this.d[ver] = 0; //distancia da origem dela mesma é zero


		for (int i = 0; i < this.numVer -1; i++) {
			if(aux[i] == 0) {
				for (int u = 0; u < this.numVer -1; u++) {
					LinkedList<Aresta> list = this.grafo.getAdjLists(u);
					for (int v = 0; v < list.size(); v++) {
						Aresta aresta = list.get(v);
						relax(aresta.getSrc(), aresta.getDest());
					}
					aux[u] = 1;
				}
			}
		}

		//		for (int v = 0; v < numVer; v++) {
		//			System.out.println("ate vertice " + v + "=" + d[v]);
		//		}

		for (int u = 0; u < this.numVer -1; u++) {
			LinkedList<Aresta> list = this.grafo.getAdjLists(u);
			for (int v = 0; v < list.size(); v++) {
				Aresta aresta = list.get(v); 
				if(d[aresta.getDest()] > d[aresta.getSrc()] + peso[aresta.getSrc()][aresta.getDest()]) {
					return false; //obedece a condição de relaxamento, retorna falso
					//ciclo negativo, nao pode retornar os menores caminhos
				}
			}
		}


		for (int v = 0; v < numVer; v++) {
			System.out.println(ver + " ate vertice " + v + "=" + d[v]);
		}

		return true;

	}

}
