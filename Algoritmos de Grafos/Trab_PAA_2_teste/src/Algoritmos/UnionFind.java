package Algoritmos;

public class UnionFind {

	private int[] tamComp; //tamanho do componente
	private int[] id; //id[i] aponta para o pai de i, se id[i] = i entao i é o inicial
	private int numComponentes; //numero de componentes


	/*!
	 * Contrutor da classe ConjDisj
	 * 
	 * @Pré-Condição: nenhuma
	 * @Pós-Condição: valores setados
	 */
	public UnionFind(int tam) {

		if (tam <= 0) throw new IllegalArgumentException("tamanho negativo");

		this.numComponentes = tam;
		tamComp = new int[tam];
		id = new int[tam];

		for (int i = 0; i < tam; i++) {
			id[i] = i; // Link a si mesmo
			tamComp[i] = 1; //tamanho original de cada componente é 1
		}

	}

	/**!
	 * Encontra qual componente o vértice pertence
	 * 
	 * @param ver vértice passada
	 * @return 
	 * 
	 * @Pré-Condição: nenhuma
	 * @Pós-Condição: retorna o componente
	 */
	 public int find(int p) {
	   if (p == id[p]) return p;
	   return id[p] = find(id[p]);
	 }

	/**!
	 * Faz a junção de dois vértices em um grupo
	 * 
	 * @param u vértice u
	 * @param v vértice v
	 * 
	 * @Pré-Condição: nenhuma
	 * @Pós-Condição: junção 
	 */
	public void uniao(int u, int v) {

		int root1 = find(u);
		int root2 = find(v);

		// se vértice estão no mesmo grupo, encerra
		if (root1 == root2) return;

		// Junta o menor componente com o maior
		if (tamComp[root1] < tamComp[root2]) {
			tamComp[root2] += tamComp[root1];
			id[root1] = root2;
		} else {
			tamComp[root1] += tamComp[root2];
			id[root2] = root1;
		}

		// decrementa o numero de componentes existentes
		numComponentes--;
	}

}
