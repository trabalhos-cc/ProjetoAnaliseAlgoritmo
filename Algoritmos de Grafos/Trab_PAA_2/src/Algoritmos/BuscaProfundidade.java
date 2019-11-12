package Algoritmos;

import java.util.LinkedList;

import Grafos.Aresta;
import Grafos.Grafo;

public class BuscaProfundidade {

	public static final byte branco = 0; // nao visitado
	public static byte cinza = 1; // nem todos os vértices adjacentes foram visitados
	public static byte preto = 2; // completamente visitado
	private Grafo grafo;
	private Integer d[], f[], predecessor[]; // valor de chegada e termino
	private int numVer;
	private int tempo;

	/*
	 * ! Contrutor da classe BuscaProfundidade
	 * 
	 * @Pré-Condição: nenhuma
	 * 
	 * @Pós-Condição: valores setados
	 */
	public BuscaProfundidade(Grafo grafo) {
		this.numVer = grafo.getNumVer();
		this.grafo = grafo;
		this.predecessor = new Integer[this.numVer];
		this.d = new Integer[this.numVer];
		this.f = new Integer[this.numVer];
		this.tempo = 0;
	}

	/*
	 * ! Chamada da busca em profundidade
	 * 
	 * @Pré-Condição: nenhuma
	 * 
	 * @Pós-Condição: busca realizada
	 */
	public void buscaProfundidade(int inicio) {

		int cor[] = new int[this.numVer];

		// inicializa valores de cor e predecessor dos vértices
		for (int i = 0; i < this.numVer; i++) {
			cor[i] = branco;
			this.predecessor[i] = null;
		}

		for (int ver = inicio; ver < this.numVer; ver++) {
			// acesso as vértices
			if (cor[ver] == branco) {
				this.visitaBuscaProfundidade(ver, cor);
			}
		}
	}

	/**
	 * ! Faz o processo de busca em profundidade
	 * 
	 * @param ver   vertice
	 * @param tempo timestamp
	 * @param cor
	 * @return valor de timestamp
	 * 
	 * @Pré-Condição: dados existem
	 * @Pós-Condição: busca realizada
	 */
	public void visitaBuscaProfundidade(int ver, int[] cor) {

		cor[ver] = cinza;

		this.d[ver] = tempo;
		tempo++;

		if (!this.grafo.getAdjLists(ver).isEmpty()) {

			LinkedList<Aresta> list = this.grafo.getAdjLists(ver); // pega a lista adjacente do vértice ver

			for (int v = 0; v < list.size(); v++) {

				int verAdj = this.grafo.getAdjLists(ver).get(v).getDest(); // vertice adjacente

				if (cor[verAdj] == branco) {
					this.predecessor[verAdj] = ver;

					System.out.println("Da vertice = " + this.grafo.getVertices().get(ver).getRotulo()
							+ " para a vertice = " + this.grafo.getVertices().get(verAdj).getRotulo() + "\n");

					this.visitaBuscaProfundidade(verAdj, cor);

				}

			}

			this.f[ver] = tempo;

		}

		cor[ver] = preto;
		tempo++;

		System.out.println("Vertice = " + this.grafo.getVertices().get(ver).getRotulo());
		System.out.println("Chegada = " + d[ver]);
		System.out.println("Termino = " + f[ver] + "\n");

	}
}
