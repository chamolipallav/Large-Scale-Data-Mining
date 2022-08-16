//Q5 TOP 10 FREQUENT RULES  FOR SUBSCRIBED=YES AND SUBSCRIBED = NO

import java.io.IOException;
import ca.pfv.spmf.tools.resultConverter.ResultConverter;
import ca.pfv.spmf.algorithms.associationrules.TopKRules_and_TNR.AlgoTopKClassRules;
import ca.pfv.spmf.algorithms.associationrules.TopKRules_and_TNR.Database;

public class topk_ClassRules {

	public void topK_classRules(String input_dataset, String out_path, double minconf, int top_k,
			int[] itemToBeUsedAsConsequent) {
// creating output file  for NO
		String output = out_path + "topk_classRules_NO.txt";
		String final_output = out_path + "final_topk_classRules_NO.txt";
// creating output file for YES	
		// String output = out_path + "topk_classRules_YES.txt";
		// String final_output = out_path + "final_topk_classRules_YES.txt";

// create database to load input file
		Database db = new Database();
		try {

			db.loadFile(input_dataset);

		} catch (IOException e) {
			e.printStackTrace();
		}
// create object of rule mining 
		AlgoTopKClassRules topK_classRules = new AlgoTopKClassRules();
// generate association rules
		topK_classRules.runAlgorithm(top_k, minconf, db, itemToBeUsedAsConsequent);
		topK_classRules.printStats();
		try {
			topK_classRules.writeResultTofile(output);
		} catch (IOException e) {
			e.printStackTrace();
		}
// convert result with their original namesd of itemset.
		ResultConverter output_converter = new ResultConverter();
		try {
			output_converter.convert(input_dataset, output, final_output, null);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
// input dataset
		String input_dataset = "C:/Users/Ayush chamoli.DESKTOP-J1DCF6U/Desktop/datasets/topk_consequent/bank.txt";
// output file
		String out_path = "C:/Users/Ayush chamoli.DESKTOP-J1DCF6U/Desktop/datasets/topk_consequent/";
// minimum confidfence
		double minconf = 0.3;
// number of rules to be generated
		int top_k = 10;
//the cosequents used to generate specific results with conditions.
		// here 11 = subscribed = no
		// and 42 = subsribed = yes

		// to generate 10 top rules with subscribe = yes and subscribe = no we have
		// change the array data 11 with 42
		int[] itemToBeUsedAsConsequent = new int[] { 11 };

		// for yes
		// int[] itemToBeUsedAsConsequent = new int[] { 42 };

		topk_ClassRules generateRules = new topk_ClassRules();
		generateRules.topK_classRules(input_dataset, out_path, minconf, top_k, itemToBeUsedAsConsequent);

	}

}
