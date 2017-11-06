import java.io.File;
import java.util.Scanner;

public class DNABenchMark2 {
	
	private static String mySource;
	private static final int TRIALS = 10;
	private static final int splices = 1000;
	private static final int length = 10000;
	private static final String splicee = "ttttt";
	
	
	public static String dnaData() {

		String dna = new String();
		

		StringBuilder buf = new StringBuilder("");
		for (int i = 0; i < length; i++) {
			buf.append("c");
		}
		for (int j = 0; j < splices; j+= splicee.length()) {
			buf.replace(j,j+splicee.length(),splicee);
		}
		//buf.reverse();
		dna = buf.toString();
		
		//System.out.println(buf);

		
		return dna;
	}

	public static void main(String[] args)
			throws Exception {

		mySource = dnaData();
		String enzyme = "g";


			
		StringStrand strand = new StringStrand(mySource);
//		StringBuilderStrand strand = new StringBuilderStrand(mySource);
//		LinkStrand strand = new LinkStrand(mySource);
		System.out.println("length of string = " + mySource.length());
		System.out.println("length of splice = " + splicee.length());
		
		
		double time = 0;
		try {
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


//public class DNABenchMark2 {
//	
//	private static final int TRIALS = 10;	
//	
//	public static void main(String [] args) {
//		int breaks = 4000;
//		String enzyme = "a";
//		String splice = "ttttt";
//		StringBuilder strandBuild = new StringBuilder();
//		for (int a = 0; a < breaks; a++) {
//			strandBuild.append(enzyme);
//		}
//		for (int b = 0; b < 10000-breaks; b++) {
//			strandBuild.append("g");
//		}
//		String strandString = strandBuild.toString();
//		System.out.println(strandString.length());
//		
////		StringStrand strand = new StringStrand(strandString);
//		StringBuilderStrand strand = new StringBuilderStrand(strandString);
////		LinkStrand strand = new LinkStrand(strandString);
//		
//		try {
//			double sum = 0;
//			for (int i = 0; i < TRIALS; i++) {
//				Thread thread = new Thread(() -> {
//					strand.cutAndSplice(enzyme, splice);
//				});
//				double start = System.nanoTime();
//				thread.run();
//				thread.join();
//				double end = System.nanoTime();
//				double time = (end-start) / 1e9;
//				sum += time;
//			}
//			double avgTime = sum/TRIALS;
//			System.out.println(avgTime);
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
//		
//	}
//	
//}
