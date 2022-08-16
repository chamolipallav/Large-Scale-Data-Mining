//Q1

import java.io.IOException;

import ca.pfv.spmf.algorithms.frequentpatterns.apriori.AlgoApriori;
import ca.pfv.spmf.algorithms.frequentpatterns.fpgrowth.AlgoFPGrowth;
import ca.pfv.spmf.tools.dataset_converter.TransactionDatabaseConverter;
import ca.pfv.spmf.tools.resultConverter.ResultConverter;

public class AssociationMining {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

		// input dataset
		String input_file = "C:/Users/Ayush chamoli.DESKTOP-J1DCF6U/Desktop/datasets/bank.arff";
		// convert arff to txt file
		String converted_input = "C:/Users/Ayush chamoli.DESKTOP-J1DCF6U/Desktop/datasets/converted_bank.txt";
		// output files for algorithms
		String output_Apriori = "C:/Users/Ayush chamoli.DESKTOP-J1DCF6U/Desktop/datasets/bank_fp_Apriorio.txt";

		String output_fpt = "C:/Users/Ayush chamoli.DESKTOP-J1DCF6U/Desktop/datasets/bank_fp_fpt.txt";

		// object for converter from arff to txt file.
		TransactionDatabaseConverter converter = new TransactionDatabaseConverter();
		// converting the arff to txt
		converter.convertARFFandReturnMap(input_file, converted_input, Integer.MAX_VALUE);

		// declareing minimum support for the program

		double minsup = 0.3;
		// create objects of pattern mining algorithms
		AlgoApriori algo_Apri = new AlgoApriori();
		AlgoFPGrowth algo_FPGrowth = new AlgoFPGrowth();

		// setting a max size for pattern (optional )
		algo_Apri.setMaximumPatternLength(5);
		algo_FPGrowth.setMaximumPatternLength(5);

		// printing out the minimum support
		System.out.print("with minsup of \n");
		System.out.print(minsup);
		System.out.print("\n");

		// run the algorithm to generate patterns
		algo_Apri.runAlgorithm(minsup, converted_input, output_Apriori);
		algo_Apri.printStats();
		algo_FPGrowth.runAlgorithm(converted_input, output_fpt, minsup);
		algo_FPGrowth.printStats();

		// storing the file in an final output file
		String final_output_apri = "C:/Users/Ayush chamoli.DESKTOP-J1DCF6U/Desktop/datasets/final_bank_apriori.txt";
		String final_output_fpt = "C:/Users/Ayush chamoli.DESKTOP-J1DCF6U/Desktop/datasets/bank_fpgrowth.txt";

		// converting the result into final output file
		ResultConverter output_converter = new ResultConverter();
		output_converter.convert(converted_input, output_Apriori, final_output_apri, null);
		output_converter.convert(converted_input, output_fpt, final_output_fpt, null);

	}

}
