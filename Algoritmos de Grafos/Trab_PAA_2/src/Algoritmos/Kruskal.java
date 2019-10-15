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
	 * @Pr�-Condi��o: grafo existente
	 * @P�s-Condi��o: valor inicializado
	 */
	public Kruskal(Grafo grafo){
		this.grafo = grafo;
	}

	/**!
	 * Realiza a jun��o de todas as arestas em uma �nica lista
	 * 
	 * @param conjArestas lista, onde os valores ir�o ser adicionados
	 *
	 * @Pr�-Condi��o: lista inicializada
	 * @P�s-Condi��o: lista com todas as arestas
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
	 * @Pr�-Condi��o: nenhuma
	 * @P�s-Condi��o: valor da soma das arestas da �rvore m�nima
	 */
	public void arvGeradora(int dados) {

		int soma = 0;

		UnionFind conj = new UnionFind(this.grafo.getNumVer()); //cria conjunto disjunto

		LinkedList<Aresta> conjArestas = new LinkedList<Aresta>();

		this.conjArestas(conjArestas);

		//faz o sort, com expressao lambda, os elementos da lista em ordem crescente
		conjArestas.sort((a1, a2) -> Integer.compare(a1.getPeso(), a2.getPeso()));

		for(int i = dados; i < conjArestas.size(); i++) {
			int u = conjArestas.get(i).getSrc(); //v�rtice de origem
			int v = conjArestas.get(i).getDest(); //v�rtice de destino
			if(conj.find(u) != conj.find(v)) {
				soma += conjArestas.get(i).getPeso(); //faz a soma dos pesos das arestas
				conj.uniao(u, v); //acrescenta as v�rtices no conjunto
			}
		}

		System.out.println("Soma = " + soma);

	}

}
