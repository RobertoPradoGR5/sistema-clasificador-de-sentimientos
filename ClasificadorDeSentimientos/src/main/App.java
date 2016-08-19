package main;

import java.util.ArrayList;
import java.util.List;

import extraccion.Atributo;
import extraccion.Clasificador;
import extraccion.DataSet;
import texto.Texto;
import archivo.Archivo;

public class App {

	public static void main(String[] args) throws Exception {
		Long begin, end;
		begin = System.currentTimeMillis();
		
		Archivo archivo=new Archivo();
		
		/**DATASETS*/
		//Crear relacion y atributos
		String relacion="estados";
		
		Atributo atributo1=	new Atributo("palabras+","{si,no}");
		Atributo atributo2=	new Atributo("Npalabras+","real");
		Atributo atributo3=	new Atributo("palabras-","{si,no}");
		Atributo atributo4=	new Atributo("Npalabras-","real");
		Atributo atributo5=	new Atributo("adjetivosPersonalidad+","{si,no}");
		Atributo atributo6=	new Atributo("NadjetivosPersonalidad+","real");
		Atributo atributo7=	new Atributo("adjetivosPersonalidad-","{si,no}");
		Atributo atributo8=	new Atributo("NadjetivosPersonalidad-","real");
		Atributo atributo9=	new Atributo("adjetivos+","{si,no}");	
		Atributo atributo10=new Atributo("Nadjetivos+","real");
		Atributo atributo11=new Atributo("adjetivos-","{si,no}");
		Atributo atributo12=new Atributo("Nadjetivos-","real");
		Atributo atributo13=new Atributo("sentimientos+","{si,no}");
		Atributo atributo14=new Atributo("Nsentimientos+","real");
		Atributo atributo15=new Atributo("sentimientos-","{si,no}");
		Atributo atributo16=new Atributo("Nsentimientos-","real");
		Atributo atributo17=new Atributo("palabrasClave-","real");
		Atributo clase=		new Atributo("estado","{malo,neutro,bueno}");
		
		List<Atributo> atributos=new ArrayList<>();
		atributos.add(atributo1);
		atributos.add(atributo2);
		atributos.add(atributo3);
		atributos.add(atributo4);
		atributos.add(atributo5);
		atributos.add(atributo6);
		atributos.add(atributo7);
		atributos.add(atributo8);
		atributos.add(atributo9);
		atributos.add(atributo10);
		atributos.add(atributo11);
		atributos.add(atributo12);
		atributos.add(atributo13);
		atributos.add(atributo14);
		atributos.add(atributo15);
		atributos.add(atributo16);
		atributos.add(atributo17);
		atributos.add(clase);
		System.out.println(">>>Relacion y atributos creados.\n");
		
		//Leer dataset de entrenamiento	
		String fileDataSetTrain="C:/Users/RobertoPrado/Desktop/DataMining/DataSetEntrenamiento/dataset-train.arff";
		System.out.println(">>>Archivo de entrada: "+fileDataSetTrain);
		
		/**CLASIFICADOR*/
		Clasificador clasificador=new Clasificador();		
		//Evaluacion: Cross validation
		clasificador.crossValidation("NaiveBayes", fileDataSetTrain);
			
		//Crear el dataset sin etiqueta de clase del libro a clasificar
		String libro="archivos/emma.txt";	
		String fileSalida1="archivos/salida1.txt";	
		String fileDataSetUnLabeled="datasets/dataset-unlabel.arff";
		String fileDataSetLabeled="datasets/dataset-labeled.arff";
		
		System.out.println("\n>>>Creación de dataset sin clasificar del libro "+libro+"");
		String contenido=archivo.leerContenido(libro);
		
		Texto util=new Texto();
		String[] oraciones=util.separadorRegex(contenido, "\\.\\s\\s|\\.\"|\\.]|\\.\'");
		System.out.println("Oraciones obtenidas de: "+libro+" :"+oraciones.length);
		archivo.escribirContenido(oraciones, fileSalida1);
		
		DataSet datasetUnlabel=new DataSet(relacion,atributos);
		datasetUnlabel.extraerAtributosSinClase(oraciones);
		datasetUnlabel.escribir(fileDataSetUnLabeled,0,oraciones.length);	
		
		//Clasificar dataset creado sin clases
		clasificador.clasificarDataSet("NaiveBayes", fileDataSetTrain, fileDataSetUnLabeled, fileDataSetLabeled);
		
		//Calificaciion del dataset clasificado
		System.out.println("\n>>>RESULTADOS");
		clasificador.crossValidation("NaiveBayes", fileDataSetLabeled);
		
		end= System.currentTimeMillis();
		Double segundos=(end - begin) / 1000.0;
		Double minutos=(end - begin) /  60000.0;
		System.out.println("TIEMPO TOTAL: en "+ segundos+"s\t"+": en "+minutos+"min");
	}
}
