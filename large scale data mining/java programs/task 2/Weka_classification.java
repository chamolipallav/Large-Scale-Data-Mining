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

public class Weka_classification {

	public void do_classification() {

		try {
			DataSource src = new DataSource("/Users/pallavchamoli/Desktop/A2/datasets/bank.arff");

			Instances data = src.getDataSet();
			data.setClass(data.attribute("subscribed"));

			CostSensitiveClassifier csc = new CostSensitiveClassifier();
			String matlab = "[0.0 1.0; 5.0 0.0]";
			CostMatrix matrix = CostMatrix.parseMatlab(matlab);

			// for NaiveBayes
			NaiveBayes naive = new NaiveBayes();
			csc.setClassifier(naive);
			csc.setCostMatrix(matrix);
			csc.buildClassifier(data);

			System.out.println("\n Classification Accuracy and Cost for NaiveBayes");
			for (int i = 1; i < 10; i++) {
				Evaluation eval = new Evaluation(data, matrix);
				Random r = new Random(i);
				eval.crossValidateModel(naive, data, 10, r);
				System.out.println(eval.correct() / eval.numInstances() + " " + eval.totalCost());

			}
			// for PART
			PART part = new PART();
			csc.setClassifier(part);
			csc.setCostMatrix(matrix);
			csc.buildClassifier(data);

			System.out.println("\n Classification Accuracy and Cost for PART");
			for (int i = 1; i < 10; i++) {
				Evaluation eval = new Evaluation(data, matrix);
				Random r = new Random(i);
				eval.crossValidateModel(part, data, 10, r);
				System.out.println(eval.correct() / eval.numInstances() + " " + eval.totalCost());

			}

			// for J48
			J48 j48 = new J48();
			csc.setClassifier(j48);
			csc.setCostMatrix(matrix);
			csc.buildClassifier(data);

			System.out.println("\n Classification Accuracy and Cost for J48");
			for (int i = 1; i < 10; i++) {
				Evaluation eval = new Evaluation(data, matrix);
				Random r = new Random(i);
				eval.crossValidateModel(j48, data, 10, r);
				System.out.println(eval.correct() / eval.numInstances() + " " + eval.totalCost());

			}
			// for OneR
			OneR oneR = new OneR();
			csc.setClassifier(oneR);
			csc.setCostMatrix(matrix);
			csc.buildClassifier(data);

			System.out.println("\n Classification Accuracy and Cost for OneR");
			for (int i = 1; i < 10; i++) {
				Evaluation eval = new Evaluation(data, matrix);
				Random r = new Random(i);
				eval.crossValidateModel(oneR, data, 10, r);
				System.out.println(eval.correct() / eval.numInstances() + " " + eval.totalCost());

			}

		} catch (Exception e) {
			System.out.println("Error");
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Weka_classification weka = new Weka_classification();
		weka.do_classification();

	}

}
