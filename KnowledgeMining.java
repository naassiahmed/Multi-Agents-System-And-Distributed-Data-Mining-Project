import java.util.Random;
import java.util.Scanner;

import jade.tools.logging.ontology.GetAllLoggers;
import weka.classifiers.Classifier;
import weka.classifiers.Evaluation;
import weka.classifiers.bayes.BayesNet;
import weka.classifiers.lazy.LWL;
import weka.classifiers.lazy.kstar.KStarNominalAttribute;
import weka.classifiers.meta.AdaBoostM1;
import weka.classifiers.meta.Bagging;
import weka.classifiers.meta.FilteredClassifier;
import weka.classifiers.meta.MultiClassClassifier;
import weka.classifiers.meta.MultiClassClassifierUpdateable;
import weka.classifiers.meta.RandomCommittee;
import weka.classifiers.meta.RandomSubSpace;
import weka.classifiers.meta.Stacking;
import weka.classifiers.meta.Vote;
import weka.classifiers.misc.InputMappedClassifier;
import weka.classifiers.rules.JRip;
import weka.classifiers.rules.OneR;
import weka.classifiers.rules.PART;
import weka.classifiers.trees.J48;
import weka.classifiers.trees.LMT;
import weka.classifiers.trees.M5P;
import weka.classifiers.trees.REPTree;
import weka.classifiers.trees.RandomForest;
import weka.classifiers.trees.RandomTree;
import weka.core.Instances;
import weka.core.converters.ConverterUtils.DataSource;
import weka.filters.supervised.attribute.Discretize;
import weka.filters.unsupervised.attribute.Remove;

public class KnowledgeMining {
public static Object[] Start(DataSource ds,int Classifier_Agent,String LocalName) throws Exception{
	
	
	
		
	Discretize  bb=new Discretize();
	
	Remove rm = new Remove();
	DataSource source = ds;
	Instances data = source.getDataSet();
	if (data.classIndex() == -1)
		   data.setClassIndex(data.numAttributes() - 1);
	
	System.out.println(LocalName+ "    >>  "+ data.numInstances());
		Classifier classifier = null;
			
		if(Classifier_Agent==1)
		{
			 classifier=new BayesNet ();
		}	
		if(Classifier_Agent==2){
			 classifier=new BayesNet();
		}
		if(Classifier_Agent==3){
			 classifier=new BayesNet();
		}
			
			
			
	FilteredClassifier fc = new FilteredClassifier();
	fc.setFilter(rm);
	fc.setClassifier((Classifier) classifier);
	
	 
	 fc.buildClassifier(data);
	 Evaluation eval = new Evaluation(data);
	 
	 eval.crossValidateModel(classifier, data, 10, new Random(1));
	 String perf_results=new String();
	 perf_results=String.valueOf(Math.round(eval.correct()*100)/eval.numInstances())+" %";
	 //System.out.println(Math.round(eval.correct()*100)/eval.numInstances() +"    %%");
	
	 Object[] results=new Object[2];
	 results[0]=fc;
	 results[1]=perf_results;
	 
	 return results;
	 
	 }


public static String MergeClassifiers(FilteredClassifier[] set_classifiers) throws Exception{
	Scanner Cl=new Scanner(System.in);
	System.out.println("Path for the whole DataSet : ");
	String path=Cl.nextLine();
	
	DataSource source=new DataSource(path);
	
	//DataSource source = new   DataSource("E:\\Mémoire Master SII\\Docs & datsets\\Downloaded Datasets\\tic-tac-toe_ALL.arff");
	Instances data = source.getDataSet();
	System.out.println("All is :  "+data.numInstances());
	if (data.classIndex() == -1)
		   data.setClassIndex(data.numAttributes() - 1);
	
	
	int size_classifiers=set_classifiers.length;
	Classifier[] classifiers=new Classifier[size_classifiers];
	
	
	for(int i=0;i<size_classifiers;i++)
		{
		classifiers[i]=set_classifiers[i];
		}
	
		Stacking s=new Stacking();
		//s.setMetaClassifier(new PART());
		s.setMetaClassifier(new AdaBoostM1());
		s.setClassifiers(classifiers);
		s.buildClassifier(data);
		
		Evaluation eval = new Evaluation(data);
		
		 eval.crossValidateModel(s, data, 10, new Random(1));
		 String perf_results=new String();
		 perf_results=String.valueOf(Math.round((eval.correct()*100)/eval.numInstances()))+" %";
		
		 
		 return perf_results;
		
}
}