package texto;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import verificadores.Verificador;

public class Texto {
	
	public Vector<Character> PasarTextoAVector(String texto){
    	Vector<Character> vector=new Vector<Character>(texto.length());
    	for(int i=0;i<texto.length();i++){
    		vector.add(texto.charAt(i));
    	}
    	return vector;
    }
	
	public String[] separadorRegex(String texto,String regex){
		String[] parts=texto.split(regex);
		
		return parts;
	}
	public List<String> extraerDatosReferencia(String archivo){
	      String cadena;
	      List<String> lista=new ArrayList<>(); 
	      FileReader f;
		try {
			f = new FileReader(archivo);
			BufferedReader b = new BufferedReader(f);
		      while((cadena = b.readLine())!=null) {
		          lista.add(cadena);
		      }
		      b.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (IOException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	      
	      return lista;
	}
	public String limpiarPalabra(String palabra){
		String resultado="";
		Verificador v=new Verificador();
		List<Character> carcteres=new ArrayList<Character>();
		for(int i=0;i<palabra.length();i++){
			if(v.esSigno(palabra.charAt(i))==false)carcteres.add(palabra.charAt(i));
		}
		for(int j=0;j<carcteres.size();j++){
			resultado+=carcteres.get(j);
		}
		return resultado;
	}
	public int obtenerIndicePorPorcentajeDeSeparacion(String[] texto, double porcentaje){
		int finTotal=texto.length;
		int finProcentaje=(int)(finTotal*(0.8));
		System.out.println("FIN"+porcentaje+": "+finProcentaje+" de "+finTotal);
		return finProcentaje;
	}
	public List<String> extraerDatosReferenciaSplit(String archivo){
	      String cadena,resultado="";
	      List<String> lista=new ArrayList<>(); 
	      FileReader f;
		try {
			f = new FileReader(archivo);
			BufferedReader b = new BufferedReader(f);
		      while((cadena = b.readLine())!=null) {
		          
		          resultado+=cadena;
		      }
		      b.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (IOException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
	      String  []palabras =resultado.split("\\s|,|-|–|\\.");
	      
	      for(int i=0;i<palabras.length;i++)
	      {
	    	  if(palabras[i]!=null&&!palabras[i].isEmpty())lista.add(palabras[i]);
	      }
	      
	    	  
	      return lista;
	      
	}
}
