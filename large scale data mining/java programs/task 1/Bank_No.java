//Q2 BANK_NO

import java.io.IOException;

import ca.pfv.spmf.algorithms.frequentpatterns.fpgrowth.AlgoFPGrowth;
import ca.pfv.spmf.tools.dataset_converter.TransactionDatabaseConverter;
import ca.pfv.spmf.tools.resultConverter.ResultConverter;

public class Bank_No {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		// input dataset
		String input_file1 = "C:/Users/Ayush chamoli.DESKTOP-J1DCF6U/Desktop/datasets/bank_no.arff";
		// convert arff to txt file
		String input_converted1 = "C:/Users/Ayush chamoli.DESKTOP-J1DCF6U/Desktop/datasets/converted_bank_no.txt";
		// output files for algorithms
		String output_fpt1 = "C:/Users/Ayush chamoli.DESKTOP-J1DCF6U/Desktop/datasets/bank_no_fp_fpt.txt";
		// object for converter from arff to txt file.
		TransactionDatabaseConverter converter = new TransactionDatabaseConverter();
		// converting the arff to txt
		converter.convertARFFandReturnMap(input_file1, input_converted1, Integer.MAX_VALUE);
		// declareing minimum support for the program
		double minsup = 0.1;
		// create objects of pattern mining algorithms
		AlgoFPGrowth algo_FPGrowth = new AlgoFPGrowth();

		// FREQURENT SIZE -3 PATTERN
		algo_FPGrowth.setMinimumPatternLength(3);

		// FOR MAXIMUM PATTERN WE DONT NEED TO USE
		// algo_FPGrowth.setMinimumPatternLength(3);

		// run the algorithm to generate patterns
		algo_FPGrowth.runAlgorithm(input_converted1, output_fpt1, minsup);
		algo_FPGrowth.printStats();
		// storing the file in an final output file
		String final_output_fpt1 = "C:/Users/Ayush chamoli.DESKTOP-J1DCF6U/Desktop/datasets/bank_no_fpgrowth1.txt";
		// converting the result into final output file
		ResultConverter output_converter = new ResultConverter();

		output_converter.convert(input_converted1, output_fpt1, final_output_fpt1, null);
	}

}
