import java.util.Random;
import weka.classifiers.Evaluation;

import weka.classifiers.functions.SMO;
import weka.classifiers.lazy.IBk;
import weka.classifiers.meta.FilteredClassifier;
import weka.classifiers.trees.HoeffdingTree;
import weka.classifiers.trees.J48;
import weka.core.Instances;
import weka.core.SelectedTag;
import weka.core.converters.ConverterUtils.DataSource;
import weka.core.stemmers.LovinsStemmer;
import weka.core.stopwords.Rainbow;
import weka.core.tokenizers.AlphabeticTokenizer;
import weka.filters.unsupervised.attribute.StringToWordVector;


public class WekaTextClassifier {

	public void doFilteredClassification(Instances data) throws Exception {

		// Sets the class index of the dataset
		data.setClassIndex(1);
		// Create a StringToWordVector filter
		StringToWordVector swFilter = new StringToWordVector();
		// Specify range of attributes to act on. We want to work on the entire list of
		// words
		swFilter.setAttributeIndices("first-last");
		// Configure the filter
		swFilter.setIDFTransform(true);
		swFilter.setTFTransform(true);
		swFilter.setDoNotOperateOnPerClassBasis(false);
		swFilter.setNormalizeDocLength(
				new SelectedTag(StringToWordVector.FILTER_NORMALIZE_ALL, StringToWordVector.TAGS_FILTER));
		swFilter.setOutputWordCounts(true);
		swFilter.setStemmer(new LovinsStemmer());
		swFilter.setStopwordsHandler(new Rainbow());
		swFilter.setTokenizer(new AlphabeticTokenizer());
		swFilter.setWordsToKeep(200);
		// Create a FilteredClassifier object
		FilteredClassifier filter_classifier = new FilteredClassifier();
		// Set the filter to the filtered classifier
		filter_classifier.setFilter(swFilter);
		// Create a classifier IBk
		IBk ibk = new IBk();
		// Add the classifier into the filtered classifier
		filter_classifier.setClassifier(ibk);
		filter_classifier.buildClassifier(data);
		
		Evaluation eval = new Evaluation(data);
		eval.crossValidateModel(filter_classifier, data, 10, new Random(1));
		System.out.println("IBk " + eval.correct()/eval.numInstances());
		System.out.println("Done");
		System.out.println(eval.toSummaryString());
		System.out.println(eval.toClassDetailsString());
		System.out.println("===== Evaluating on filtered (training) dataset done =====");
		
		
	//Create a classifier SMO
		SMO smo = new SMO();
		filter_classifier.setClassifier(smo);
		filter_classifier.buildClassifier(data);
		eval.crossValidateModel(filter_classifier, data, 10, new Random(1));
		System.out.println("SMO " + eval.correct()/eval.numInstances());
		System.out.println("Done");
		System.out.println(eval.toSummaryString());
		System.out.println(eval.toClassDetailsString());
		System.out.println("===== Evaluating on filtered (training) dataset done =====");
		
		//Create a classifier J48
		J48 j48 = new J48();
		filter_classifier.setClassifier(j48);
		filter_classifier.buildClassifier(data);
		eval.crossValidateModel(filter_classifier, data, 10, new Random(1));
		System.out.println("J48 " + eval.correct()/eval.numInstances());
		System.out.println("Done");
		System.out.println(eval.toSummaryString());
		System.out.println(eval.toClassDetailsString());
		System.out.println("===== Evaluating on filtered (training) dataset done =====");
		
		// Crete a classifier Hoeffding tree
		HoeffdingTree htree = new HoeffdingTree();
		filter_classifier.setClassifier(htree);
		filter_classifier.buildClassifier(data);
		eval.crossValidateModel(filter_classifier, data, 10, new Random(1));
		System.out.println("HoeffdingTree " + eval.correct()/eval.numInstances());
		System.out.println("Done");
		System.out.println(eval.toSummaryString());
		System.out.println(eval.toClassDetailsString());
		System.out.println("===== Evaluating on filtered (training) dataset done =====");
		// Evaluation
		
		//System.out.println("Done");
		//System.out.println(eval.toSummaryString());
		//System.out.println(eval.toClassDetailsString());
		//System.out.println("===== Evaluating on filtered (training) dataset done =====");

	}

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub

		DataSource source = new DataSource("C:/Users/Ayush chamoli.DESKTOP-J1DCF6U/Desktop/datasets/Q3 JAVA/NEWS.arff");
		Instances data = source.getDataSet();
		WekaTextClassifier tc = new WekaTextClassifier();
		tc.doFilteredClassification(data);
	}

}
