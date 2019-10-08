package Arquivos;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

import Grafos.Grafo;
import Grafos.Vertice;

public class ReadFile {

	private Scanner path;
	private Grafo grafo;
	
	public ReadFile(String fileName) {
        Path filePath = Paths.get(fileName);

        /// tenta abrir o arquivo
        try {
            this.path = new Scanner(filePath);
        } catch (Exception e) {
            /// printa erro
            System.out.println(String.format("Erro na abertura do arquivo (%s);", fileName));
        }

        
    }

	public Grafo read () throws IOException{
		
		ArrayList <Vertice> v = new ArrayList<Vertice>();

		boolean g = this.readGuidance(this.path.nextLine());
		int numVer = this.readNumVer(this.path.nextLine(), v);
		
		this.grafo = new Grafo(numVer, g);
		System.out.println(this.grafo);
		
		for(int i = 0; i < numVer; i++) {
			this.grafo.addVertice(v.get(i).getRotulo());
		}
		
		while(this.path.hasNextLine()) {
			this.readUV(this.path.nextLine());
		}
		
		return this.grafo;
	}
	
	private boolean readGuidance(String line) {

		line = line.substring(line.indexOf('=')+1);
		
		if(line.equals("nao")) {
			return false;
		}else {
			return true;
		}
	}
	
	private int readNumVer(String line, ArrayList<Vertice> v) {
		String[] names;
		String aux;
		int len = 0;
		Vertice ve = new Vertice();
		
		line = line.substring(line.indexOf('=')+1);
	
		aux = line.substring(0,1);

		if(!aux.equals("{")) {

			return Integer.parseInt(line);
		}
		
		
		line = line.substring(line.indexOf('{')+1, line.indexOf('}'));
		names = line.split(",");
			
		for(int i = 0; i < names.length; i++) {
			names[i] = names[i].trim();
			ve.setRotulo(names[i]);
			v.add(ve);
				
			len = i;
			
		}
		
		return len;
	
	}

	private void readUV(String line) {
		String lineAux;
		String lineAux2;
		String[] statenames;
		String[] sn;
		
		
		lineAux = line.substring(line.indexOf('(')+1, line.indexOf(')'));
		statenames = lineAux.split(",");
		
		
		lineAux2 = line.substring(line.indexOf(':')+1);
		sn = lineAux2.split(",");
		
		
		for (int i = 0; i < statenames.length; i += 2) {
			statenames[i] = statenames[i].trim();
			sn[i] = sn[i].trim();
			
			if(sn.length < 2) {
				this.grafo.addAresta(Integer.parseInt(sn[i]), "null",
						Integer.parseInt(statenames[i]), Integer.parseInt(statenames[i+1]));
			}else {
				this.grafo.addAresta(Integer.parseInt(sn[i]), sn[i+1],
						Integer.parseInt(statenames[i]), Integer.parseInt(statenames[i+1]));
			}
			
			
			
			
		}
		

	}
}
