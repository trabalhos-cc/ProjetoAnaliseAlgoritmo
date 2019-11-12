package Algoritmos;

import java.util.LinkedList;

import Grafos.Aresta;
import Grafos.Grafo;

public class Prim {

	private Grafo grafo;
	private int numVer;// quantidade de vertices

	/**
	 * ! Construtor da classe Prim
	 * 
	 * @param grafo
	 * 
	 * @Pré-Condição: existe objeto de grafo
	 * @Pós-Condição: valores inicializados
	 */
	public Prim(Grafo grafo) {
		this.grafo = grafo;
		this.numVer = grafo.getNumVer();
	}

	/**
	 * ! Valida a aresta
	 * 
	 * @param u     inicio da aresta
	 * @param v     fim da aresta
	 * @param inMST //arestas que fazem parte da arvore
	 * @return true para valida e false para nao valida
	 * 
	 * @Pré-Condição: src e dst da aresta
	 * @Pós-Condição: nenhuma
	 */

	static boolean isArestaValida(int u, int v, boolean[] inMST) {
		if (u == v) // quando forem as mesmas vertices
			return false;
		if (inMST[u] == false && inMST[v] == false) // quando nenhuma das vertices estiverem
			return false;
		else if (inMST[u] == true && inMST[v] == true) // quando as vertices ja estiverem
			return false;
		return true;
	}

	/**
	 * ! Algoritmo de prim
	 * 
	 * @Pré-Condição: existe objeto de grafo
	 * @Pós-Condição: imprime as arestas que fazem parte da arvore e seus pesos
	 */
	public void prim(int ver) {

		boolean inMST[] = new boolean[this.numVer]; // vertice se encontra na arvore geradora minima

		inMST[ver] = true; // vertice inicial

		int contaAresta = 0, custoMin = 0;
		
		System.out.println("Arestas da arvore: ");

		while (contaAresta < this.numVer - 1) {

			// Encontrao menor valor de aresta
			int min = Integer.MAX_VALUE, novoSrc = -1, novoDst = -1, vertice = -1, verAdj = -1;
			for (int i = 0; i < this.numVer; i++) {

				LinkedList<Aresta> list = this.grafo.getAdjLists(i);

				for (int j = 0; j < list.size(); j++) {

					int u = list.get(j).getSrc(); // vértice de origem
					int v = list.get(j).getDest(); // vértice de destino

					if (list.get(j).getPeso() < min) {
						if (isArestaValida(u, v, inMST)) {
							min = list.get(j).getPeso();
							novoSrc = u;
							novoDst = v;

							vertice = i;
							verAdj = j;

						}
					}
				}
			}

			System.out.println("Aresta = " + this.grafo.getAdjLists(vertice).get(verAdj).getRotulo() + " Src = "
					+ novoSrc + " Dst = " + novoDst + " peso = " + min);
			custoMin = custoMin + min;
			inMST[novoDst] = inMST[novoSrc] = true; // acrescenta na arvore
			
			contaAresta++;

		}

		System.out.println("Soma/Custo minimo= " + custoMin);

	}

}
