package Main;

import java.util.Scanner;

import Algoritmos.*;
import Arquivos.ReadFile;
import Grafos.Grafo;

public class Main {

	public static void main(String[] args) throws Exception {
		Grafo grafo = new Grafo();
//		GraphViz gv = new GraphViz();
//		 
//	   
//		gv.addln(gv.start_graph());
//	    gv.add("0 -- 1[label = a];");
//	    gv.add("2 -- 4[label = b];");
//	    gv.add("1 -- 3[label = c];");
//	    gv.add("4 -- 2[label = d];");
//	    gv.add("3 -- 2[label = e]");
//	    gv.addln(gv.end_graph());
//	    
//	    
//	   // String type = "gif";
//	    String type = "jpg";
//	    gv.increaseDpi();
//	    gv.decreaseDpi();
//	   // gv.decreaseDpi();
//	    File out = new File("DotGraph_"+"."+ type); 
//	    gv.writeGraphToFile( gv.getGraph( gv.getDotSource(), type ), out );
//		
		ReadFile file = null;
		int dados;

		/// se existe mais de um argumento
//        if (args.length == 1) {
		/// inicializa aut�mato com arquivo de nome do segundo argumento
//			
//			  System.out.println(String.format("FileName = %s", args[0]));

		file = new ReadFile(
				"F:/Arquivos/eclipse-workspace/Trab_PAA_Gabriel/ProjetoAnaliseAlgoritmo/Algoritmos de Grafos/Trab_PAA_2/grafo3.txt");

//		file = new ReadFile("C:/Users/DK Square/Desktop/Trab_PAA_2/grafo3.txt");

		grafo = file.read();
//        } else {
//            /// printa erro
//            System.out.println("Nenhum arquivo de entrada especificado!");
//           
//            return;
//        }

		if (grafo != null) {
			System.out.println("--------------------------------");
			System.out.println("       Escolha um algor�tmo     \n");
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

				if (grafo.isOrientado()) {
					System.out.println("Digite uma vertice origem = ");
					dados = scan.nextInt();
					BellmanFord bellman = new BellmanFord(grafo);
					bellman.bellmanFord(dados);
				} else {
					System.out.println("Grafo n�o � orientado");
				}
				break;

			case 4:

				if (!grafo.isOrientado()) {
					System.out.println("Digite uma vertice inicial = ");
					dados = scan.nextInt();
					Kruskal kruskal = new Kruskal(grafo);
					kruskal.arvGeradora(dados);
				} else {
					System.out.println("Grafo � orientado");
				}
				break;

			case 5:

				if (!grafo.isOrientado()) {
					System.out.println("Digite uma vertice inicial = ");
					dados = scan.nextInt();
					Prim p = new Prim(grafo);
					p.prim(dados);
				} else {
					System.out.println("Grafo � orientado");
				}

				break;
			case 6:

				if (grafo.isOrientado()) {
					FordFulkerson ford = new FordFulkerson(grafo);
					System.out.println("Fluxo m�ximo de: " + ford.fluxoMax(0, 5));
				} else {
					System.out.println("Grafo n�o � orientado");
				}

				break;

			}
		}

		System.out.println("Terminou");
	}
}
