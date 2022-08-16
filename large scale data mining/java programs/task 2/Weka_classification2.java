//Q2.2.2

import java.util.Random;
import weka.classifiers.CostMatrix;
import weka.classifiers.bayes.NaiveBayes;
import weka.classifiers.evaluation.Evaluation;
import weka.classifiers.meta.CostSensitiveClassifier;
import weka.classifiers.rules.OneR;
import weka.classifiers.rules.PART;
import weka.classifiers.trees.J48;
import weka.core.Instances;
import weka.core.converters.ConverterUtils.DataSource;

public class Weka_classification2 {

	public void do_classification() {

		try {
			DataSource src = new DataSource("/Users/pallavchamoli/Desktop/A2/datasets/bank.arff");

			Instances data = src.getDataSet();
			data.setClass(data.attribute("subscribed"));

			CostSensitiveClassifier csc = new CostSensitiveClassifier();
			String matlab = "[0.0 1.0; 5.0 0.0]";
			CostMatrix matrix = CostMatrix.parseMatlab(matlab);

			// for PART
			PART part = new PART();
			csc.setClassifier(part);
			csc.setCostMatrix(matrix);
			csc.buildClassifier(data);

			System.out.println("\n Classification Instance and Accuracy for PART");
			for (int i = 1; i < 10; i++) {
				Evaluation eval = new Evaluation(data, matrix);
				Random r = new Random(i);
				eval.crossValidateModel(part, data, 10, r);
				System.out.println(eval.correct() + " " + (eval.correct() / eval.numInstances()) * 100);

			}

			// for J48
			J48 j48 = new J48();
			csc.setClassifier(j48);
			csc.setCostMatrix(matrix);
			csc.buildClassifier(data);

			System.out.println("\n Classification Instance and Accuracy for J48");
			for (int i = 1; i < 10; i++) {
				Evaluation eval = new Evaluation(data, matrix);
				Random r = new Random(i);
				eval.crossValidateModel(j48, data, 10, r);
				System.out.println(eval.correct() + " " + (eval.correct() / eval.numInstances()) * 100);
			}
			// for OneR
			OneR oneR = new OneR();
			csc.setClassifier(oneR);
			csc.setCostMatrix(matrix);
			csc.buildClassifier(data);

			System.out.println("\n Classification Instance and Accuracy for OneR");
			for (int i = 1; i < 10; i++) {
				Evaluation eval = new Evaluation(data, matrix);
				Random r = new Random(i);
				eval.crossValidateModel(oneR, data, 10, r);
				System.out.println(eval.correct() + " " + (eval.correct() / eval.numInstances()) * 100);

			}

		} catch (Exception e) {
			System.out.println("Error");
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Weka_classification2 weka = new Weka_classification2();
		weka.do_classification();

	}

}
