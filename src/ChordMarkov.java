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
	public ChordMarkov() {
		statsFileName = "/songGenerator5/src/HymnChordAnalysis.txt";
		chordList = readChordList();
	}
	
	
	/**
	 * Creates an n^2 matrix. The index of the row corresponds to its 
	 * index in the chordList. The indexes of the columns likewise correspond
	 * to chordList indices. 	 
	 */
	private double[][] createMarkovMatrix() {
		double[][] matrix = new double[chordList.size()][chordList.size()];
		Scanner input = new Scanner(statsFileName);
        while (input.hasNextLine()) {
        	String[] parts = input.nextLine().split(":");        	
        	String[] chords = parts[0].split(",");
        	int index1 = chordList.indexOf(chords[0]);
        	int index2 = chordList.indexOf(chords[1]);
        	matrix[index1][index2] = Integer.parseInt(parts[1]);
        }
        input.close();
		return matrix;
	}
	
	/**
	 * Returns a list of all chords encountered in the python output file. 
	 * @return
	 */
	private ArrayList<String> readChordList() {
		ArrayList<String> chordList = new ArrayList<String>();
		Scanner input = new Scanner(statsFileName);
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
        return chordList;
	}
	
}
