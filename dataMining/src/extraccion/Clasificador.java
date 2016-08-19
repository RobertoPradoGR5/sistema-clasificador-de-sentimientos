package extraccion;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Random;

import weka.classifiers.Classifier;
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
		switch(tipo){
		case "NaiveBayes":
			NaiveBayes nb=new NaiveBayes();		
			nb.buildClassifier(train);			
			eval.crossValidateModel(nb, train, 10, new Random(1));
			System.out.println(eval.toSummaryString("\n>>>Resultados: Validaci�n del clasificador: Cross Validation\n====================\n",true));
			System.out.println(eval.toMatrixString());
			System.out.println("fMeasure: "+eval.fMeasure(1)+"\tPrecision: "+eval.precision(1)+"\tRecall: "+eval.recall(1));
			break;
		case "J48":	
			J48 tree=new J48();
			tree.buildClassifier(train);
			eval.crossValidateModel(tree, train, 10, new Random(1));
			System.out.println(eval.toSummaryString("\n>>>Resultados: Validaci�n del clasificador: Cross Validation\n====================\n",true));
			System.out.println(eval.toMatrixString());
			System.out.println("fMeasure:\t"+eval.fMeasure(1)+"\tPrecision: "+eval.precision(1)+"\tRecall: "+eval.recall(1));
			break;
			default:
				System.out.println("\n>>>Resultados Cross Validation: Clasificador no v�lido\n");			
		}
	}
	public void train_test_Validation(String tipo, String fileDatasetTrain, String fileDatasetTest) throws Exception{
		BufferedReader breaderTrain=null;
		breaderTrain=new BufferedReader(new FileReader(fileDatasetTrain));		
		Instances train = new Instances(breaderTrain);
		train.setClassIndex(train.numAttributes()-1);
		breaderTrain.close();
		
		BufferedReader breaderTest=null;
		breaderTest=new BufferedReader(new FileReader(fileDatasetTest));
		Instances test =new Instances(breaderTest);
		test.setClassIndex(train.numAttributes()-1);
		breaderTest.close();
		
		Classifier cls=null;
		Evaluation eval=new Evaluation(train);
		
		switch(tipo){
		case "NaiveBayes":
			NaiveBayes nb=new NaiveBayes();		
			nb.buildClassifier(train);	
			cls = new NaiveBayes();
			break;
		case "J48":	
			J48 tree=new J48();
			tree.buildClassifier(train);
			cls = new J48();
			break;
			default:
				System.out.println("\n>>>Resultados Train-Test: Clasificador no v�lido\n");			
		}		
		if(cls!=null){
			cls.buildClassifier(train);	
			eval.evaluateModel(cls, test);
			System.out.println(eval.toSummaryString("\n>>>Resultados: Train/test Validation\n=========================\n", false));
			System.out.println(eval.toMatrixString());
			System.out.println(">>>fMeasure: "+eval.fMeasure(1)+"\tPrecision: "+eval.precision(1)+"\tRecall: "+eval.recall(1)+"\n");
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
				System.out.println("\n>>>Resultados del clasificador: Clasificador no v�lido");			
		}		
	}
}
