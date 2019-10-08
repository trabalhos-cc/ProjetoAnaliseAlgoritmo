package Main;

import java.util.Scanner;

import Algoritmos.BuscaLargura;
import Algoritmos.BuscaProfundidade;
import Algoritmos.FordFulkerson;
import Algoritmos.Kruskal;
import Arquivos.ReadFile;
import Grafos.Grafo;

public class Main {

	public static void main(String[] args) throws Exception {
		Grafo grafo = new Grafo();
		ReadFile file;
		int dados;
		
		/// se existe mais de um argumento
        if (args.length == 1) {
            /// inicializa autômato com arquivo de nome do segundo argumento
            System.out.println(String.format("FileName = %s", args[0]));
            file = new ReadFile(args[0]);
            grafo = file.read();
        } else {
            /// printa erro
            System.out.println("Nenhum arquivo de entrada especificado!");
           
            return;
        }
		
		
        System.out.println(grafo);
       
        if(grafo != null) {
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

			switch(selecao) {
			case 1:
				BuscaProfundidade buscaP = new BuscaProfundidade(grafo); 
				buscaP.buscaProfundidade();
				break;
			case 2:

				BuscaLargura buscaL = new BuscaLargura(grafo); 
				buscaL.buscaLargura();
				break;
			case 3:
				break;
			case 4:
				
				dados = scan.nextInt();
				Kruskal kruskal = new Kruskal(grafo);
				kruskal.arvGeradora(dados);

				break;
			case 5:
				break;
			case 6:

				if(grafo.isOrientado()) { 
					FordFulkerson ford = new FordFulkerson (grafo);
					System.out.println("Fluxo máximo de: " + ford.fluxoMax(0, 4)); 
				}else{
					System.out.println("Grafo não é orientado"); 
				}

				break;

			}
		}
	    
	    System.out.println("terminou");
	}

}
