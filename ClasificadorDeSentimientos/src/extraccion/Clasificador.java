package extraccion;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Random;

import weka.classifiers.Evaluation;
import weka.classifiers.bayes.NaiveBayes;
import weka.classifiers.trees.J48;
import weka.core.Instances;

public class Clasificador {
	public void crossValidation(String tipo, String dataset) throws Exception{
		BufferedReader breaderTrain=null;
		breaderTrain=new BufferedReader(new FileReader(dataset));		
		Instances train = new Instances(breaderTrain);
		train.setClassIndex(train.numAttributes()-1);
		breaderTrain.close();
		Evaluation eval=new Evaluation(train);
		
		Double totalInstancias=0.0;
		Double correctas=0.0;
		Double precision=0.0;
		
		switch(tipo){
		case "NaiveBayes":
			NaiveBayes nb=new NaiveBayes();		
			nb.buildClassifier(train);			
			eval.crossValidateModel(nb, train, 10, new Random(1));
			System.out.println(eval.toSummaryString("\n>>>Resultados: Validación del clasificador: Cross Validation\n====================\n",true));
			System.out.println(eval.toMatrixString());
			totalInstancias=eval.numInstances();
			correctas=eval.correct();
			precision=correctas/totalInstancias;
			System.out.println(">>>Precisión: "+precision);
			break;
		case "J48":	
			J48 tree=new J48();
			tree.buildClassifier(train);
			eval.crossValidateModel(tree, train, 10, new Random(1));
			System.out.println(eval.toSummaryString("\n>>>Resultados: Validación del clasificador: Cross Validation\n====================\n",true));
			System.out.println(eval.toMatrixString());
			totalInstancias=eval.numInstances();
			correctas=eval.correct();
			precision=correctas/totalInstancias;
			System.out.println("\tPrecisión: "+precision);
			break;
			default:
				System.out.println("\n>>>Resultados Cross Validation: Clasificador no válido\n");			
		}
	}
	public void clasificarDataSet(String tipo ,String fileDataSetTrain, String fileDataSetUnLabeled ,String fileDataSetLabeled) throws Exception{
		BufferedReader breaderTrain=null;
		breaderTrain=new BufferedReader(new FileReader(fileDataSetTrain));		
		Instances train = new Instances(breaderTrain);
		train.setClassIndex(train.numAttributes()-1);
		breaderTrain.close();
		
		BufferedReader breaderUnlabel=null;
		breaderUnlabel=new BufferedReader(new FileReader(fileDataSetUnLabeled));
		Instances unlabel =new Instances(breaderUnlabel);
		// create copy
		Instances labeled = new Instances(unlabel);
		
		unlabel.setClassIndex(train.numAttributes()-1);
		labeled.setClassIndex(train.numAttributes()-1);
		breaderUnlabel.close();
		
		switch(tipo){
		case "NaiveBayes":
			NaiveBayes nb2=new NaiveBayes();
			nb2.buildClassifier(train);
			for(int i=0;i<unlabel.numInstances();i++){
				double clsLabel=nb2.classifyInstance(unlabel.instance(i));
				labeled.instance(i).setClassValue(clsLabel);
			}
			BufferedWriter writer1=new BufferedWriter(new FileWriter(fileDataSetLabeled));
			writer1.write(labeled.toString());
			writer1.flush();
			writer1.close();
			System.out.println("\n>>>Resultados clasificador: dataset creado en "+fileDataSetLabeled+"\n");	
			break;
		case "J48":	
			J48 tree=new J48();
			tree.buildClassifier(train);
			for(int i=0;i<unlabel.numInstances();i++){
				double clsLabel=tree.classifyInstance(unlabel.instance(i));
				labeled.instance(i).setClassValue(clsLabel);
			}
			BufferedWriter writer2=new BufferedWriter(new FileWriter(fileDataSetLabeled));
			writer2.write(labeled.toString());
			writer2.flush();
			writer2.close();
			System.out.println("\n>>>Resultados del clasificador: dataset creado en "+fileDataSetLabeled);	
			break;
			default:
				System.out.println("\n>>>Resultados del clasificador: Clasificador no válido");			
		}		
	}
}
