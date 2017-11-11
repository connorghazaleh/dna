import java.io.File;
import java.util.Scanner;

public class DNABenchMark2 {
	
	private static String mySource;
	private static final int TRIALS = 10;
	private static final int splices = 2000;
	private static final int length = 10000;
	private static final String splicee = "ttt";
	
	
	public static String dnaData() {

		String dna = new String();
		
		//create the string strand by appending a variable number of splice locations
		//to a variable length string
		StringBuilder buf = new StringBuilder("");
		//create initial string
		for (int i = 0; i < length; i++) {
			buf.append("c");
		}
		//replace parts of initial string with however many splice locations I need
		for (int j = 0; j < splices; j+= splicee.length()) {
			buf.replace(j,j+splicee.length(),splicee);
		}

		dna = buf.toString();
		
		return dna;
	}

	public static void main(String[] args)
			throws Exception {

		//Create string for analyzation
		mySource = dnaData();
		String enzyme = "g";


		//initialize a particular type of Strand object	
		StringStrand strand = new StringStrand(mySource);
//		StringBuilderStrand strand = new StringBuilderStrand(mySource);
//		LinkStrand strand = new LinkStrand(mySource);
		System.out.println("length of string = " + mySource.length());
		System.out.println("length of splice = " + splicee.length());
		
		//record start time
		double time = 0;
		try {
			//run cutAndSplice for a variable number of trials, 
			//then record the average time of all the trials
			for (int i = 0; i < TRIALS; i++) {
				Thread thread = new Thread(() -> {
					strand.cutAndSplice(enzyme, splicee);
				});
				double start = System.nanoTime();
				thread.run();
				thread.join();
				time += (System.nanoTime() - start)/TRIALS/1e9;
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		
		System.out.println(time);
		System.out.println("");
		System.out.println("");

		
		System.exit(0);
	}
	
}


