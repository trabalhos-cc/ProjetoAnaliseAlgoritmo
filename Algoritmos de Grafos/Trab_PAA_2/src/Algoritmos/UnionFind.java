package Algoritmos;

public class UnionFind {

	private int[] tamComp; //tamanho do componente
	private int[] id; //id[i] aponta para o pai de i, se id[i] = i entao i � o inicial
	private int numComponentes; //numero de componentes


	/*!
	 * Contrutor da classe ConjDisj
	 * 
	 * @Pr�-Condi��o: nenhuma
	 * @P�s-Condi��o: valores setados
	 */
	public UnionFind(int tam) {

		if (tam <= 0) throw new IllegalArgumentException("tamanho negativo");

		this.numComponentes = tam;
		tamComp = new int[tam];
		id = new int[tam];

		for (int i = 0; i < tam; i++) {
			id[i] = i; // Link a si mesmo
			tamComp[i] = 1; //tamanho original de cada componente � 1
		}

	}

	/**!
	 * Encontra qual componente o v�rtice pertence
	 * 
	 * @param ver v�rtice passada
	 * @return 
	 * 
	 * @Pr�-Condi��o: nenhuma
	 * @P�s-Condi��o: retorna o componente
	 */
	 public int find(int p) {
	   if (p == id[p]) return p;
	   return id[p] = find(id[p]);
	 }

	/**!
	 * Faz a jun��o de dois v�rtices em um grupo
	 * 
	 * @param u v�rtice u
	 * @param v v�rtice v
	 * 
	 * @Pr�-Condi��o: nenhuma
	 * @P�s-Condi��o: jun��o 
	 */
	public void uniao(int u, int v) {

		int root1 = find(u);
		int root2 = find(v);

		// se v�rtice est�o no mesmo grupo, encerra
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
