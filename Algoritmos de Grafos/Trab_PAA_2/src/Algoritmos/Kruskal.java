package Algoritmos;

import java.util.LinkedList;

import Grafos.Aresta;
import Grafos.Grafo;

public class Kruskal{

	private Grafo grafo;

	/**!
	 * Construtor da classe Kruskal, inicializa valores
	 * 
	 * @param grafo
	 * 
	 * @Pré-Condição: grafo existente
	 * @Pós-Condição: valor inicializado
	 */
	public Kruskal(Grafo grafo){
		this.grafo = grafo;
	}

	/**!
	 * Realiza a junção de todas as arestas em uma única lista
	 * 
	 * @param conjArestas lista, onde os valores irão ser adicionados
	 *
	 * @Pré-Condição: lista inicializada
	 * @Pós-Condição: lista com todas as arestas
	 */
	public void conjArestas(LinkedList<Aresta> conjArestas) {

		for (int i = 0; i < this.grafo.getNumVer(); i++) {
			LinkedList<Aresta> list = this.grafo.getAdjLists(i);
			for (int j = 0; j < list.size(); j++) {
				conjArestas.add(list.get(j));
			}
		}

	}

	/**!
	 * Realiza o algoritmo de kruskal
	 * 
	 * @param dados vertice inicial
	 * 
	 * @Pré-Condição: nenhuma
	 * @Pós-Condição: valor da custoMin das arestas da árvore mínima
	 */
	public void arvGeradora(int dados) {

		int custoMin = 0;

		UnionFind conj = new UnionFind(this.grafo.getNumVer()); //cria conjunto disjunto

		LinkedList<Aresta> conjArestas = new LinkedList<Aresta>();

		this.conjArestas(conjArestas);
		
		System.out.println("Arestas da arvore: ");

		//faz o sort, com expressao lambda, os elementos da lista em ordem crescente
		conjArestas.sort((a1, a2) -> Integer.compare(a1.getPeso(), a2.getPeso()));

		for(int i = dados; i < conjArestas.size(); i++) {
			int u = conjArestas.get(i).getSrc(); //vértice de origem
			int v = conjArestas.get(i).getDest(); //vértice de destino
			if(conj.find(u) != conj.find(v)) {
				
				System.out.println("Aresta = " + conjArestas.get(i).getRotulo() + 
						" Src = " + u + " Dst = " + v + " peso = " + conjArestas.get(i).getPeso());
				
				custoMin += conjArestas.get(i).getPeso(); //faz a custoMin dos pesos das arestas
				conj.uniao(u, v); //acrescenta as vértices no conjunto
			}
		}

		System.out.println("custoMin/Custo minimo = " + custoMin);

	}

}
