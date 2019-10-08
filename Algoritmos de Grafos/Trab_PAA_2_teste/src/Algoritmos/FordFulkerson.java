package Algoritmos;

import java.util.LinkedList;

import Grafos.Aresta;
import Grafos.Grafo;

public class FordFulkerson {

	private Grafo grafo;
	private int numVerMax;

	/**!
	 * Construtor da classe BuscaLargura com grafo
	 * 
	 * @param grafo
	 *  
	 * @Pr�-Condi��o: existe objeto de grafo
	 * @P�s-Condi��o: valores inicializados
	 */
	public FordFulkerson(Grafo grafo) {
		this.grafo = grafo;
		this.numVerMax = grafo.getNumVer();
	}

	/**!
	 * 
	 * @param src v�rtice inicial, fonte
	 * @param dest v�rtice destino, ralo
	 * @return retorna o valor do fluxo m�ximo
	 * 
	 * @Pr�-Condi��o: v�rtice fonte e ralo passados como par�metros
	 * @P�s-Condi��o: fluxo m�ximo calculado
	 */
	public int fluxoMax(int src, int dest) {

		int u, v;
		int fluxo = 0;
		int predecessor[] = new int[this.numVerMax];

		int grafoResidual[][] = new int[this.numVerMax][this.numVerMax];

		//copia as arestas das listas de adjacencia para uma matriz adjacente que representa o grafo residual
		for (u = 0; u < this.numVerMax; u++) {
			LinkedList<Aresta> list = this.grafo.getAdjLists(u);
			for (v = 0; v < list.size(); v++) {
				int aux = this.grafo.getAdjLists(u).get(v).getSrc();
				int aux2 = this.grafo.getAdjLists(u).get(v).getDest();
				grafoResidual[aux][aux2] = list.get(v).getPeso();
			}
		}
		
		for (u = 0; u < this.numVerMax; u++) {
			for (v = 0; v < this.numVerMax; v++) {
				System.out.print(grafoResidual[u][v] + " ");
				if(v%5 == 0) {
					System.out.println();
				}
			}
		}

		//visita da v�rtice fonte at� o ralo, guarda o predecessor
		while(new BuscaLargura(this.numVerMax).buscaLargura(grafoResidual, src, dest, predecessor)) {

			int capacidade = Integer.MAX_VALUE;
			
			//verifica a aresta de menor capacidade residual, gargalo do caminho
			for(v = dest; v != src; v=predecessor[v]){
				u = predecessor[v];
				capacidade = Math.min(capacidade, grafoResidual[u][v]);
			}

			//o menor valor � subtra�do dos outras arestas do caminho na dire��o u-v
			//e somada na dire��o contr�ria
			for(v = dest; v != src; v=predecessor[v]) {
				u = predecessor[v];
				grafoResidual[u][v] -= capacidade;
				grafoResidual[v][u]	+= capacidade;
			    //quando a aresta zerar o caminho n�o ser� mais utilizado
			}
			
			//caminho percorrido, acrescenta oo fluxo m�ximo
			fluxo += capacidade;
		}

		return fluxo;

	}
}
