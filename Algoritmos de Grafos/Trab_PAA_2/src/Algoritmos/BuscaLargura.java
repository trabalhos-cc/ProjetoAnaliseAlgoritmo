package Algoritmos;

import java.util.LinkedList;
import java.util.Queue;

import Grafos.Aresta;
import Grafos.Grafo;

public class BuscaLargura {

	public static final byte branco = 0; //nao visitado
	public static byte cinza = 1; //nem todos os v�rtices adjacentes foram visitados
	public static byte preto = 2; //completamente visitado
	private Grafo grafo;
	private Integer predecessor[];
	private Integer d[]; //dist�ncia a ser percorrida
	private int numVer;

	/**!
	 * Construtor da classe BuscaLargura com grafo
	 * 
	 * @param grafo objeto do grafo inicial
	 *  
	 * @Pr�-Condi��o: existe objeto de grafo
	 * @P�s-Condi��o: valores inicializados
	 */
	public BuscaLargura(Grafo grafo) {

		this.numVer = grafo.getNumVer();
		this.grafo = grafo;
		this.predecessor = new Integer[this.numVer];
		this.d = new Integer[this.numVer];

	}

	/**!
	 * Construtor de classe BuscaLargura sem grafo
	 * 
	 * @param numVer
	 * 
	 * @Pr�-Condi��o: nenhum
	 * @P�s-Condi��o: valores inicializados
	 */
	public BuscaLargura(int numVer) {
		this.numVer = numVer;
	}

	/**!
	 * Faz o processo de busca em largura
	 * 
	 * @param ver vertice que sera percorrido 
	 * @param cor vetor para cores (mesma quantidade de v�rtices)
	 * 
	 * @Pr�-Condi��o: nenhum
	 * @P�s-Condi��o: valores inicializados
	 */
	public void visitaBuscaLargura(int ver, int cor[]){

		int aux = 0;
		Queue<Integer> fila = new LinkedList<Integer>();
		this.d[ver] = 0; //timestamp de chegada do v�rtice
		this.predecessor[ver] = null; //v�rtice antecessor
		fila.add(ver); //adiciona vertice na 

		//executa at� q a fila fique vazia
		while(!fila.isEmpty()){ 

			aux = fila.poll(); //remove e recebe o primeiro elemento da fila
			LinkedList<Aresta> list = this.grafo.getAdjLists(aux); //lista de adjac�ncia daquele v�rtice

			//percorre a quantidade de elementos da lista de adjac�ncia
			for(int i = 0; i < list.size(); i++){ 

				int verAdj = this.grafo.getAdjLists(aux).get(i).getDest(); //o vertice adjacente

				System.out.println("inicio = " + this.grafo.getAdjLists(aux).get(i).getSrc() + "fim = " + this.grafo.getAdjLists(aux).get(i).getDest() );
				
				if (cor[verAdj] == branco) { 
					cor[verAdj] = cinza;				
					this.d[verAdj] = this.d[aux] + 1; //timestamp
					this.predecessor[verAdj] = aux; //recebe predecesor
					fila.add(verAdj); //adiciona v�rtice a fila
				}

			}

			cor[aux] = preto; 

			System.out.println("Vertice = " +
					this.grafo.getVertices().get(aux).getRotulo() + "\n" + "Chegada = " + d[aux] + "\n");

			
		}

	}

	/**!
	 * Chamada da busca em largura, uso de listas adjacentes
	 * 
	 * @Pr�-Condi��o: nenhum
	 * @P�s-Condi��o: busca realizada
	 */
	public void buscaLargura(int inicio){

		int cor[] = new int[this.numVer];

		//inicializa valores de cor, distancia e predecessor dos v�rtices
		for(int i = 0; i < this.numVer; i ++) {
			cor[i] = branco;
			this.d[i] = -1;
			this.predecessor[i] = null;
		}

		for(int ver = inicio; ver < this.numVer; ver ++) {
			//acesso as v�rtices
			if(cor[ver] == branco) {
				this.visitaBuscaLargura(ver, cor);
			}
		}
	}

	/**!
	 * Chamada da busca em largura, uso de matriz
	 * 
	 * @param grafo grafo com os pesos das arestas
	 * @param src v�rtice inicial, 
	 * @param dest v�rtice destino, ralo
	 * @param predecessor
	 * @return retorna true quando chega a v�rtice destino
	 * 
	 * @Pr�-Condi��o: dados existem
	 * @P�s-Condi��o: busca realizada e percorrido at� o ralo
	 */
	public boolean buscaLargura(int grafo[][], int src, int dest, int [] predecessor){

		boolean visitado[] = new boolean[this.numVer];

		for(int i=0; i < this.numVer; ++i){
			visitado[i] = false;
		}

		Queue<Integer> fila = new LinkedList<Integer>();

		fila.add(src); 
		predecessor[src] = -1;
		visitado[src] = true;

		while(!fila.isEmpty()){ 

			int aux = fila.poll(); //remove e recebe o primeiro elemento da fila

			for(int i = 0; i < this.numVer; i++){ 
				//percorre todas as v�rtices que nao foram vizitados, verificando arestas que forem maiores que 0
				if (visitado[i] == false && grafo[aux][i] > 0) { 
					fila.add(i);
					predecessor[i] = aux;
					visitado [i] = true;
				}
			}	

		}
		
		return (visitado[dest] == true);

	}

}
