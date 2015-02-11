import java.io.File;

import jm.music.data.Part;
import jm.music.data.Score;
import jm.music.tools.Mod;
import jm.util.Read;
import jm.util.Write;

/**
 * Convert midi files to the key of C Major / a minor 
 * @author Mike
 */
public class MidiTransposer {
	private String pathOfHymnsToConvert;
	
	/**
	 * Constructor - sets the base file path for midis to convert
	 * @param pathOfHymnsToConvert
	 */
	public MidiTransposer(String pathOfHymnsToConvert) {
		this.pathOfHymnsToConvert = pathOfHymnsToConvert;
	}
		
	/**
	 * Converts all files found in the pathOfHymnsToConvert
	 * folder to the key of C. Places converted files in the cMidi directory
	 * @param pathOfHymnsToConvert
	 */
	public void convertMidiFiles() {
		File path = new File(pathOfHymnsToConvert);
	    File [] files = path.listFiles();
	    for (int i = 0; i < files.length; i++){
	        if (files[i].isFile()){ //this line weeds out other directories/folders
	        	Score score = new Score("temp Score");
	    		Read.midi(score, pathOfHymnsToConvert + files[i].getName());
	    		Score cScore = new Score("c_" + files[i].getName());
	    		int key = score.getKeySignature();
	    		for(int part =0; part < score.getPartArray().length; part++) {
	    			Part p = score.getPart(part);
	    			Mod.transpose(p, getTransposeKey(key));
	    			cScore.add(p);
	    		}
	    		cScore.setTempo(score.getTempo());
	    		Write.midi(cScore, "cMidis/c_" + files[i].getName());	        
	        }
	    }
	}

	/**
	 * Returns the appropriate number of semitone
	 * movements necessary to move to the key of C Major / A minor
	 * @param key - the input key signature 
	 * 				(Positive = # of sharps, Negative = # of Flats)
	 * @return semitone amount to pass into the Mod.transpose function
	 */
	private int getTransposeKey(int key) {
		switch(key) {
			case 1:
				return 5;				
			case 2:
				return -2;
			case 3: 
				return 3;
			case 4:
				return -4;
			case 5:
				return 1;
			case 6:
				return 6;
			case -1:
				return -5;
			case -2:
				return 2;
			case -3:
				return -3;
			case -4:
				return 4;
			case -5:
				return -1;
			default:
				return 0;
		}
	}
}
