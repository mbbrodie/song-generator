import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


/**
 * Creates the Markov Matrix for hymn generation
 * @author Mike
 */
public class ChordMarkov {
	String statsFileName;
	ArrayList<String> chordList;
	double[][] chordMatrix;
	public ChordMarkov() {
		statsFileName = "/Users/Mike/git/songGenerator4/src/HymnChordAnalysis.txt";
		chordList = readChordList();
		chordMatrix = createMarkovMatrix();
	}
	
	
	/**
	 * Creates an n^2 matrix. The index of the row corresponds to its 
	 * index in the chordList. The indexes of the columns likewise correspond
	 * to chordList indices. 	 
	 */
	private double[][] createMarkovMatrix() {
		double[][] matrix = new double[chordList.size()][chordList.size()];
		Scanner input;
		try {
			input = new Scanner(new File("/Users/Mike/git/songGenerator4/src/HymnChordAnalysis.txt"));
		
	        while (input.hasNextLine()) {
	        	String[] parts = input.nextLine().split(":");        	
	        	String[] chords = parts[0].split(",");
	        	int index1 = chordList.indexOf(chords[0]);
	        	int index2 = chordList.indexOf(chords[1]);
	        	matrix[index1][index2] = Integer.parseInt(parts[1]);
	        }
        input.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return matrix;
	}
	
	/**
	 * Returns a list of all chords encountered in the python output file. 
	 * @return
	 */
	private ArrayList<String> readChordList() {
		ArrayList<String> chordList = new ArrayList<String>();
		Scanner input;
		try {
			input = new Scanner(new File(statsFileName));
		
	        while (input.hasNextLine()) {
	        	String[] parts = input.nextLine().split(":");
	        	String[] chords = parts[0].split(",");
	        	for (String c : chords) {
	        		if(!chordList.contains(c)) {
	        			chordList.add(c);
	        		}
	        	}                        
	        }
	        input.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        return chordList;
	}
	
}
