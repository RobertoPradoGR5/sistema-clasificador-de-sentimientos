package archivo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Archivo {
	public String leerContenido(String fichero) throws FileNotFoundException, IOException {
	      String resultado="";
	      String cadena="";
	      try{
	    	  FileReader file = new FileReader(fichero);
	    	  BufferedReader b = new BufferedReader(file);
		      while((cadena = b.readLine())!=null) {
		          resultado+=cadena;
		      }
		      b.close();
	      }
	      catch(FileNotFoundException e){
	    	  return "INF: "+e;
	      }
	      System.out.println("Contenido obtenido: "+fichero);
	      return resultado;
	}
	public void escribirContenido(String[] lineas,String fichero) throws IOException{
		BufferedWriter bw = new BufferedWriter(new FileWriter(fichero));
		for (int x=0;x<lineas.length;x++){
			 bw.write(" ["+lineas[x]+"]");
			 bw.newLine();
		}	
		bw.close();
		System.out.println("Contenido escrito en "+fichero);
	}
}

