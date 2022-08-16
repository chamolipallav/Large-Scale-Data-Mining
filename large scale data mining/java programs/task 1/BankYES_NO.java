//Q2 BANK_YES

import java.io.IOException;

import ca.pfv.spmf.algorithms.frequentpatterns.fpgrowth.AlgoFPGrowth;
import ca.pfv.spmf.tools.dataset_converter.TransactionDatabaseConverter;
import ca.pfv.spmf.tools.resultConverter.ResultConverter;

public class BankYES_NO {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		// input dataset
		String input_file = "C:/Users/Ayush chamoli.DESKTOP-J1DCF6U/Desktop/datasets/bank_yes.arff";
		// convert arff to txt file
		String converted_input = "C:/Users/Ayush chamoli.DESKTOP-J1DCF6U/Desktop/datasets/converted_bank_yes.txt";
		// output files for algorithms
		String output_fpt = "C:/Users/Ayush chamoli.DESKTOP-J1DCF6U/Desktop/datasets/bank_yes_fp_fpt.txt";
		// object for converter from arff to txt file.
		TransactionDatabaseConverter converter = new TransactionDatabaseConverter();
		// converting the arff to txt
		converter.convertARFFandReturnMap(input_file, converted_input, Integer.MAX_VALUE);
		// declareing minimum support for the program
		double minsup = 0.1;
		// create objects of pattern mining algorithms
		AlgoFPGrowth algo_FPGrowth = new AlgoFPGrowth();
		// setting a max size for pattern (optional )
		
		//FREQURENT SIZE -3 PATTERN
		 algo_FPGrowth.setMinimumPatternLength(3);
		 
			// FOR MAXIMUM PATTERN WE DONT NEED TO USE
			// algo_FPGrowth.setMinimumPatternLength(3);

		// run the algorithm to generate patterns
		algo_FPGrowth.runAlgorithm(converted_input, output_fpt, minsup);
		algo_FPGrowth.printStats();
		// storing the file in an final output file
		String final_output_fpt = "C:/Users/Ayush chamoli.DESKTOP-J1DCF6U/Desktop/datasets/bank_yes_fpgrowth.txt";

		// converting the result into final output file
		ResultConverter output_converter = new ResultConverter();

		output_converter.convert(converted_input, output_fpt, final_output_fpt, null);
	}

}
