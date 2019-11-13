package View;

import Grafos.Aresta;
import Grafos.Grafo;
import Grafos.Vertice;

public class GraphDrawer {

//	public GraphDrawer() {}
	
	/**!
	 * Desenha o grafo
	 * 
	 * @param gv
	 * @param algoritmo
	 * 
	 * @Pré-Condição: arquivo dot escrito
	 * @Pós-Condição: arquivo jpg criado
	 */
	public void saveImage(GraphViz gv, String algoritmo) {
		gv.writeGraphToFile( gv.getGraph( gv.getDotSource(), "jpg" ), algoritmo + "." + "jpg" );
	}
	
	/**!
	 * Escreve no arquivo .dot
	 * 
	 * @param g
	 * @param algoritmo
	 * 
	 * @Pré-Condição: nenhuma
	 * @Pós-Condição: arquivo dot escrito
	 */
	public void drawer(Grafo g, String algoritmo) {
		
		GraphViz gv = new GraphViz();
		
		
		if(g.isOrientado()) {
			gv.addln("digraph grafo {");
		}else {
			gv.addln("graph grafo {");
		}
		
		setGraphAtributos(gv, algoritmo);
		setNodeAtributos(gv);
		setEdgeAtributos(gv);
		//nodeDefine
		//edgeDefine
		

		gv.addln("}");
		saveImage(gv, algoritmo);
	}
	
	
	/**!
	 * Escreve no arquivo dot os atributos do grafo
	 * 
	 * @param gv
	 * @param titulo
	 * 
	 * @Pré-Condição: variavel gv possui o inicio da sintaxe
	 * @Pós-Condição: atributos definidos
	 */
	public void setGraphAtributos(GraphViz gv, String titulo) {
		
		gv.addln(" graph ["								);
		gv.addln(" charset 		= \"UTF-8\";"			);
		gv.addln(" bgcolor 		= gray100,"				);
		gv.addln(" label 		= \" " + titulo + "\""	);
		gv.addln(" labelloc 	= \"t\","				);
		gv.addln(" labeljust 	= \"c\","				);
		gv.addln(" fontcolor 	= black,"				);
		gv.addln(" fontname		= \"helvetica\","		);
		gv.addln(" style 		= \"filled\","			);
		gv.addln(" rankdir 		= LR,"					);
		gv.addln(" splines 		= spline,"				);
		gv.addln(" ranksep 		= 3.0,"					);
		gv.addln(" nodesep 		= 4.5"					);
		gv.addln(" overlap 		= false"				);
		gv.addln(" ];"									);
	}
	
	/**!
	 * Escreve no arquivo dot os atributos dos nos
	 * 
	 * @param gv
	 * 
	 * @Pré-Condição: variavel gv possui o inicio da sintaxe
	 * @Pós-Condição: atributos definidos
	 */
	public void setNodeAtributos(GraphViz gv) {
		
		gv.addln(" node ["                      		);
		gv.addln(" style 		= \"solid,filled\"," 	);
		gv.addln(" color 		= paleturquoise2,"   	);
		gv.addln(" fontsize 	= 35,"            		);
		gv.addln(" fontcolor 	= black,"        		);
		gv.addln(" fontname 	= helvetica,"     		);
		gv.addln(" height 		= 0.6,"             	);
		gv.addln(" width 		= 1.2"               	);
		gv.addln(" ];"                          		);
	}
	
	/**!
	 * Escreve no arquivo dot os atributos do conexao
	 * 
	 * @param gv
	 * 
	 * @Pré-Condição: variavel gv possui o inicio da sintaxe
	 * @Pós-Condição: atributos definidos
	 */
	public void setEdgeAtributos(GraphViz gv) {
		
		gv.addln(" edge ["                      		);
		gv.addln(" style 		= solid," 				);
		gv.addln(" color 		= chartreuse1,"		   	);
		gv.addln(" fontsize 	= 30,"            		);
		gv.addln(" fontname 	= helvetica,"     		);
		gv.addln(" arrowhead 	= normal,"             	);
		gv.addln(" penwidth 	= 4.5"               	);
		gv.addln(" arrowsize	= 1.5"					);
		gv.addln(" ];"                          		);
	}
	
	/**!
	 * Escreve no arquivo dot as definicoes do no
	 * 
	 * @param gv
	 * @param g
	 * @param dest1
	 * @param dest2
	 * 
	 * @Pré-Condição: variavel gv possui o inicio da sintaxe
	 * @Pós-Condição: nos definidos
	 */
	public void nodeDefine(GraphViz gv, Grafo g, int dest1, int dest2) {
	
		for(int i = 0; i < g.getNumVer(); i++) {
			Vertice v = g.getVertices().get(i);
			
			if(v.getRotulo() != "") {
				gv.addln(" " + v.getRotulo() + "[ shape = circle");
			}
			
			if(i == dest1 || i == dest2) {
				gv.addln("color = purple];");
			}else {
				gv.addln("];");
			}
		}
	}
	
	/**!
	 * Escreve no arquivo dot as definicoes da conexao
	 * 
	 * @param gv
	 * @param g
	 * 
	 * @Pré-Condição: variavel gv possui o inicio da sintaxe
	 * @Pós-Condição: conexoes definidos
	 */
	public void edgeDefine(GraphViz gv, Grafo g) {
		
		String edge = (g.isOrientado())? " -> " : " -- " ;
		String labelU, labelV;
		
		for (int u = 0; u < g.getNumVer(); u++) {
			Vertice v = g.getVertices().get(u);
			labelU = "\"" + v.getRotulo() + "\"";
			
			for (Aresta a : g.getAdjLists(u)) {
				int aux1 = a.getDest();
				if(g.isOrientado() || u <= aux1) {
					labelV = "\"" + a.getRotulo() + "\"";
					
					gv.addln(" " + labelU + edge + labelV + " ");
					gv.addln("[");
					
					if(a.getRotulo() != "") {
						gv.addln(" label = <<font color  = \"blue\">" + a.getRotulo() + "</font>, ");
					}
					
					gv.addln("xlabel = <<font color=\"black\">" + a.getPeso() + "<\font>>,");
					gv.addln("arrowhead = normal];");
				}
			}
		}
	}
}
