package read;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ReadFile {

	private boolean orientado;
	private String vertice;
	public static void read (String path) throws IOException{
		
		BufferedReader buffRead = new BufferedReader (new FileReader(path));
		String linha = "";
		
		while(true) {
			if(linha != null) {
				System.out.println(linha);
				ReadFile.saveData(linha);
			}else {
				break;
			}
			linha = buffRead.readLine();
			
		}
		buffRead.close();
	}
	
	
	public static void saveData (String line) throws IOException{
		ReadFile aux = new ReadFile();
		if(line.equals("orientado=sim")) {
			aux.orientado = true;
			System.out.println(aux.orientado);
			return;
		}
		
	}
}
