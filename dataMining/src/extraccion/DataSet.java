package extraccion;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import texto.Texto;

public class DataSet {
	private String nombre;
	private List<Atributo> atributos=new ArrayList<Atributo>();
	private List<String> data=new ArrayList<String>();
	
	public DataSet() {
		super();
	}
	public DataSet(String nombre, List<Atributo> atributos) {
		super();
		this.nombre = nombre;
		this.atributos = atributos;
	}

	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public List<Atributo> getAtributos() {
		return atributos;
	}
	public void setAtributos(List<Atributo> atributos) {
		this.atributos = atributos;
	}
	public List<String> getData() {
		return data;
	}
	public void setData(List<String> data) {
		this.data = data;
	}
	public void escribir(String dataset, int inicio, int fin) throws IOException{
		
		BufferedWriter bw= new BufferedWriter(new FileWriter(dataset));
		
		bw.write("@Relation "+this.nombre);
		bw.newLine();bw.newLine();
		for (int x=0;x<atributos.size();x++){
			 bw.write("@attribute "+atributos.get(x).getNombre()+" "+atributos.get(x).getValores());
			 bw.newLine();
		}
		bw.newLine();
		bw.write("@data");
		bw.newLine();
		System.out.println("Numero de isntancias: "+this.data.size()+" inicio: "+inicio+" fin: "+fin);
		for(int j=inicio;j<fin;j++){
			bw.write(this.data.get(j));
			if(j!=fin-1)bw.newLine();
		}
		bw.close();
		System.out.println("Dataset escrito en "+dataset+"\n");
	}
	public void extraerAtributosSinClase(String[] lineas){
		Texto t=new Texto();
		List<String> adjetivosPositivos=t.extraerDatosReferencia("dataFiles/adjetivosPos.txt");
		List<String> adjetivosNegativos=t.extraerDatosReferencia("dataFiles/adjetivosNeg.txt");
		List<String> adjetivosPositivosPersonalidad=t.extraerDatosReferencia("dataFiles/adjetivosPersonalidadPos.txt");
		List<String> adjetivosNegativosPersonalidad=t.extraerDatosReferencia("dataFiles/adjetivosPersonalidadNeg.txt");
		List<String> palabrasPositivas=t.extraerDatosReferencia("dataFiles/positivewords.txt");
		List<String> palabrasNegativas=t.extraerDatosReferencia("dataFiles/negativewords.txt");
		List<String> palabrasClaveNegativas=t.extraerDatosReferencia("dataFiles/palabrasClaveNeg.txt");
		List<String> sentimientoPositivos=t.extraerDatosReferencia("dataFiles/sentimientosPos.txt");
		List<String> sentimientosNegativos=t.extraerDatosReferencia("dataFiles/sentimientosNeg.txt");
		for(int i=0;i<lineas.length;i++){			
			String linea=lineas[i].toString();
			String estado="?";
			String palabrasPos=verificarPalabrasConReferencias(linea, palabrasPositivas);
			int NpalabrasPos=contarPalabrasConReferencias(linea, palabrasPositivas);
			String palabrasNeg=verificarPalabrasConReferencias(linea, palabrasNegativas);
			int NpalabrasNeg=contarPalabrasConReferencias(linea, palabrasNegativas);
			String adjetivosPersonalidadPos=verificarPalabrasConReferencias(linea, adjetivosPositivosPersonalidad);
			int NadjetivosPersonalidadPos=contarPalabrasConReferencias(linea, adjetivosPositivosPersonalidad);
			String adjetivosPersonalidadNeg=verificarPalabrasConReferencias(linea, adjetivosNegativosPersonalidad);
			int NadjetivosPersonalidadNeg=contarPalabrasConReferencias(linea, adjetivosNegativosPersonalidad);
			String adjetivosPos=verificarPalabrasConReferencias(linea, adjetivosPositivos);
			int NadjetivosPos=contarPalabrasConReferencias(linea, adjetivosPositivos);
			String adjetivosNeg=verificarPalabrasConReferencias(linea, adjetivosNegativos);
			int NadjetivosNeg=contarPalabrasConReferencias(linea, adjetivosNegativos);
			String sentimientosPos=verificarPalabrasConReferencias(linea, sentimientoPositivos);
			int NsentimientosPos=contarPalabrasConReferencias(linea, sentimientoPositivos);
			String sentimientosNeg=verificarPalabrasConReferencias(linea, sentimientosNegativos);
			int NsentimientosNeg=contarPalabrasConReferencias(linea, sentimientosNegativos);
			int NpalabrasNegClave=contarPalabrasConReferencias(linea, palabrasClaveNegativas);
			
			this.data.add(palabrasPos+","+NpalabrasPos+","+palabrasNeg+","+NpalabrasNeg+","+adjetivosPersonalidadPos+","+NadjetivosPersonalidadPos+","+adjetivosPersonalidadNeg+","+NadjetivosPersonalidadNeg+","+adjetivosPos+","+NadjetivosPos+","+adjetivosNeg+","+NadjetivosNeg+","+sentimientosPos+","+NsentimientosPos+","+sentimientosNeg+","+NsentimientosNeg+","+NpalabrasNegClave+","+estado);
		}
		System.out.println("Realizada extraccion de atributos");
	}
	/**Metodos para extraer atributos*/
	//Metodo para ver si existen las palabaras de la lista de referencias en la oracion
	public String verificarPalabrasConReferencias(String texto,List<String> referencias){
		String resultado="no";
		Texto t=new Texto();
		String[] palabras=texto.split("\\s");
		for(int i=0;i<palabras.length;i++){
			for(int j=0;j<referencias.size();j++){
				String palabra=t.limpiarPalabra(palabras[i]);
				if(palabra.equals(referencias.get(j))){
					resultado="si";
				}
			}
		}
		return resultado;
	}
	//Metodo para contar las palabaras de la lista de referencias en la oracion
	public int contarPalabrasConReferencias(String texto,List<String> referencias){
		int resultado=0;
		Texto t=new Texto();
		String[] palabras=texto.split("\\s");
		for(int i=0;i<palabras.length;i++){
			for(int j=0;j<referencias.size();j++){
				String palabra=t.limpiarPalabra(palabras[i]);
				if(palabra.equals(referencias.get(j))){
					resultado++;
				}
			}
		}
		return resultado;
	}
} 
