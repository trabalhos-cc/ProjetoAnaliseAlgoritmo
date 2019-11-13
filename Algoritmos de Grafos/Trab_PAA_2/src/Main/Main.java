package Main;

import java.util.Scanner;

import Algoritmos.BellmanFord;
import Algoritmos.BuscaLargura;
import Algoritmos.BuscaProfundidade;
import Algoritmos.FordFulkerson;
import Algoritmos.Kruskal;
import Algoritmos.Prim;
import Arquivos.ReadFile;
import Grafos.Grafo;
import View.GraphDrawer;

public class Main {

	public static void main(String[] args) throws Exception {
		Grafo grafo = new Grafo();
//		GraphDrawer gw = new GraphDrawer();
		ReadFile file = null;
		int dados;

		
		file = new ReadFile(args[0]);
		grafo = file.read();


//		gw.drawer(grafo, "Grafo");
		if (grafo != null) {
			System.out.println("--------------------------------");
			System.out.println("       Escolha um algorítmo     \n");
			System.out.println("  1 Busca em Profundidade       ");
			System.out.println("  2 Busca em Largura            ");
			System.out.println("  3 Bellman-Ford                ");
			System.out.println("  4 Kruskal");
			System.out.println("  5 Prim");
			System.out.println("  6 Ford-Fulkerson");
			System.out.println("--------------------------------");

			Scanner scan = new Scanner(System.in);
			int selecao = scan.nextInt();

			switch (selecao) {
			case 1:

				System.out.println("Digite uma vertice inicial = ");
				dados = scan.nextInt();
				BuscaProfundidade buscaP = new BuscaProfundidade(grafo);
				buscaP.buscaProfundidade(dados);
				break;

			case 2:

				System.out.println("Digite uma vertice inicial = ");
				dados = scan.nextInt();
				BuscaLargura buscaL = new BuscaLargura(grafo);
				buscaL.buscaLargura(dados);
				break;

			case 3:

				if (!grafo.isOrientado()) {
					System.out.println("Digite uma vertice origem = ");
					dados = scan.nextInt();
					BellmanFord bellman = new BellmanFord(grafo);
					bellman.bellmanFord(dados);
				} else {
					System.out.println("Grafo não é orientado");
				}
				break;

			case 4:

					System.out.println("Digite uma vertice inicial = ");
					dados = scan.nextInt();
					Kruskal kruskal = new Kruskal(grafo);
					kruskal.arvGeradora(dados);

				break;

			case 5:

				if (!grafo.isOrientado()) {
					System.out.println("Digite uma vertice inicial = ");
					dados = scan.nextInt();
					Prim p = new Prim(grafo);
					p.prim(dados);
				} else {
					System.out.println("Grafo é orientado");
				}

				break;
			case 6:

				if (grafo.isOrientado()) {
					Scanner fonte = new Scanner(System.in);
					System.out.print("Fonte: ");
					int f = fonte.nextInt();
					Scanner ralo = new Scanner(System.in);
					System.out.print("Ralo: ");
					int r = ralo.nextInt();
					FordFulkerson ford = new FordFulkerson(grafo);
					System.out.println("Fluxo máximo de: " + ford.fluxoMax(f, r));
					fonte.close();
					ralo.close();
				} else {
					System.out.println("Grafo não é orientado");
				}

				break;

			}
		}

		System.out.println("Terminou");
	}
}
