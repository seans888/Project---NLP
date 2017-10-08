package weka.api;
//import required classes

import weka.core.Instance;
import weka.core.Instances;
import weka.core.converters.ConverterUtils.DataSource;
import weka.classifiers.bayes.NaiveBayes;
import weka.classifiers.functions.SMOreg;



public class WekaDemo{
	public static void main(String args[]) throws Exception{
		
		//load training dataset
		DataSource source = new DataSource("C:/Users/Eva Samilliano/Desktop/SS-SchoolStuffs/SCSPROJ_ExperimentONE/200Data.arff");
		Instances trainDataset = source.getDataSet();	
		//set class index to the last attribute
		trainDataset.setClassIndex(trainDataset.numAttributes()-1);
		// get number of classes
		int numClasses = trainDataset.numClasses();
		
		for(int i=0; i < numClasses; i++){
			String classValue =trainDataset.classAttribute().value(i);
			System.out.println("Class Value "+i+" is "+classValue);
		}

		//build model
		
		SMOreg smo = new SMOreg();
		smo.buildClassifier(trainDataset);


		//load new dataset
		DataSource source1 = new DataSource("C:/Users/Eva Samilliano/Desktop/SS-SchoolStuffs/SCSPROJ_ExperimentONE/200Data.arff");
		Instances testDataset = source1.getDataSet();
		
		//set class index to the last attribute
		testDataset.setClassIndex(testDataset.numAttributes()-1);
	
		
		//loop through the new dataset and make predictions
		System.out.println("===================");
		System.out.println("Actual Class, SMO Predicted");
		for (int i = 0; i < testDataset.numInstances(); i++) {
			//get class double value for current instance
			double actualClass = testDataset.instance(i).classValue();
				String actual = testDataset.classAttribute().value((int) actualClass);
			//get Instance object of current instance
			Instance newInst = testDataset.instance(i);
			//call classifyInstance, which returns a double value for the class
			double predSMO = smo.classifyInstance(newInst);
				String predString = testDataset.classAttribute().value((int) predSMO);
			System.out.println(actual+","+predString);
		}

	}
}