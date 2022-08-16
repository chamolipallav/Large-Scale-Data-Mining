//Q4 CLOSED PATTERN 

import java.io.FileNotFoundException;
import java.io.IOException;

import ca.pfv.spmf.algorithms.frequentpatterns.apriori_close.AlgoAprioriClose;
import ca.pfv.spmf.algorithms.frequentpatterns.charm.AlgoCharm_Bitset;
import ca.pfv.spmf.algorithms.frequentpatterns.fpgrowth.AlgoFPClose;
import ca.pfv.spmf.algorithms.frequentpatterns.fpgrowth.AlgoFPGrowth;
import ca.pfv.spmf.input.transaction_database_list_integers.TransactionDatabase;
import ca.pfv.spmf.tools.resultConverter.ResultConverter;

public class closed_pattern_bank {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		// Specify input dataset
		String input_dataset = "C:/Users/Ayush chamoli.DESKTOP-J1DCF6U/Desktop/datasets/converted_bank.txt";
		// Specify output files
		String out_path = "C:/Users/Ayush chamoli.DESKTOP-J1DCF6U/Desktop/datasets/output/";
		String output_Fpt = out_path + "fp_Fpt.txt";
		String output_Apriori = out_path + "fcp_Apriori.txt";
		String output_fcp_Fpt = out_path + "fcp_Fpt.txt";
		String output_Charm = out_path + "fcp_Charm.txt";

		// minimum support
		double minsup = 0.3;

		AlgoFPGrowth algo_FPGrowth = new AlgoFPGrowth();
		AlgoAprioriClose algo_AprioriClose = new AlgoAprioriClose();
		AlgoFPClose algo_FCP_Growth = new AlgoFPClose();
		AlgoCharm_Bitset algo_FCP_Charm = new AlgoCharm_Bitset();

		// Run algorithms to generate patterns
		try {
			algo_FPGrowth.runAlgorithm(input_dataset, output_Fpt, minsup);
			algo_FPGrowth.printStats();
			algo_AprioriClose.runAlgorithm(minsup, input_dataset, output_Apriori);
			algo_AprioriClose.printStats();
			algo_FCP_Growth.runAlgorithm(input_dataset, output_fcp_Fpt, minsup);
			algo_FCP_Growth.printStats();
			// AlgoCharm_Bitset requires transaction database
			TransactionDatabase tdb = new TransactionDatabase();
			tdb.loadFile(input_dataset);
			algo_FCP_Charm.runAlgorithm(output_Charm, tdb, minsup, false, 10000);
			algo_FCP_Charm.printStats();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		// storing the file in an final output file
		String final_output_fp_Fpt = out_path + "final_fp_Fpt.txt";
		String final_output_Apriori = out_path + "final_fcp_Apriori.txt";
		String final_output_fcp_Fpt = out_path + "final_fcp_Fpt.txt";
		String final_output_Charm = out_path + "final_fcp_Charm.txt";

		// converting the result into final output file

		ResultConverter output_converter = new ResultConverter();

		output_converter.convert(input_dataset, output_Fpt, final_output_fp_Fpt, null);
		output_converter.convert(input_dataset, output_Apriori, final_output_Apriori, null);
		output_converter.convert(input_dataset, output_fcp_Fpt, final_output_fcp_Fpt, null);
		output_converter.convert(input_dataset, output_Charm, final_output_Charm, null);

	}

}
