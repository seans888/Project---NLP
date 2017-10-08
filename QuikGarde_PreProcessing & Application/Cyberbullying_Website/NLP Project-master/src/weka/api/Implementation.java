package weka.api;

import java.awt.BorderLayout;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Random;

import libsvm.svm;
import weka.classifiers.Evaluation;
import weka.classifiers.evaluation.EvaluationUtils;
import weka.classifiers.evaluation.ThresholdCurve;
import weka.classifiers.functions.LibSVM;
import weka.classifiers.functions.SMO;
import weka.core.Instances;
import weka.core.Utils;
import weka.filters.Filter;
import weka.filters.unsupervised.attribute.Remove;
import weka.gui.visualize.PlotData2D;
import weka.gui.visualize.ThresholdVisualizePanel;


public class Implementation {
	
	static svm test;	
	static SMO run;
	static LibSVM new_test;
	
	public static void main(String[] args) throws Exception
	{
		 	 
		 
		//Reading data
		 BufferedReader reader = new BufferedReader(new FileReader("C:/Users/Paulo/IdeaProjects/NLP Project-master/200Data.arff"));
		 
		 Instances data = new Instances(reader);
		 reader.close();
		 
		 Remove rm = new Remove();
		 
		 
		 String[] remove = new String[2];
		 remove[0] = "-R";
		 remove[1] = "1,2";
		 
		 rm.setOptions(remove);
		 
		 rm.setInputFormat(data);
		 
		 Instances newData = Filter.useFilter(data, rm);
		 
		 newData.setClassIndex(newData.numAttributes() - 1);
		 
		 System.out.println("The number of attributes after filtering is: " + newData.numAttributes());
		 
		 SMO sm = new SMO();
		 		 
		 sm.setOptions(weka.core.Utils.splitOptions("-C 1.0 -M -K \"weka.classifiers.functions.supportVector.RBFKernel -C 250007 -G 0.001\""));
		 	
		 Evaluation eval = new Evaluation(newData);
		 
		 eval.crossValidateModel(sm, newData, 10, new Random(1), new Object[] {});
		 
		 
		 System.out.println(eval.toClassDetailsString());
		 
		 System.out.println(eval.toSummaryString(true));
		 
		 System.out.println(eval.toMatrixString());
		 
		 int n = (eval.predictions()).size();
		 
		 EvaluationUtils e = new EvaluationUtils();
		 
		 System.out.println(e.getCVPredictions(sm, newData, 10));
		 
		 //System.out.println(eval.predictions());
		
		 
		//Generate Curve
		 
		 ThresholdCurve tc = new ThresholdCurve();
	     
		 int classIndex = 1;
	     
	     Instances result = tc.getCurve(eval.predictions(), classIndex);
	 
	     // plot curve
	     ThresholdVisualizePanel vmc = new ThresholdVisualizePanel();
	     
	     vmc.setROCString("(Area under ROC = " + Utils.doubleToString(tc.getROCArea(result), 4) + ")");
	     
	     vmc.setName(result.relationName());
	     
	     PlotData2D tempd = new PlotData2D(result);
	     
	     tempd.setPlotName(result.relationName());
	     
	     tempd.addInstanceNumberAttribute();
	     // specify which points are connected
	     boolean[] cp = new boolean[result.numInstances()];
	     
	     for (int i = 1; i < cp.length; i++)
	     cp[i] = true;
	     tempd.setConnectPoints(cp);
	     // add plot
	     vmc.addPlot(tempd);
	 
	     // display curve
	     String plotName = vmc.getName(); 
	    
	     final javax.swing.JFrame jf = new javax.swing.JFrame("Weka Classifier Visualize: "+plotName);
	     
	     jf.setSize(500,400);
	     
	     jf.getContentPane().setLayout(new BorderLayout());
	     
	     jf.getContentPane().add(vmc, BorderLayout.CENTER);
	     
	     jf.addWindowListener(new java.awt.event.WindowAdapter() {
	       public void windowClosing(java.awt.event.WindowEvent e) {
	       jf.dispose();
	       }
	     });
	     
	     jf.setVisible(true);
	}
		 
		
}	