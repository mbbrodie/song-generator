import java.io.File;

import jm.JMC;
import jm.music.data.*;
import jm.music.tools.*;
import jm.util.*;

/**
 * The control class for hymn generation.
 * Oversees midi conversion, song structure extraction,
 * melody and harmony generation etc.
 * @author Mike
 *
 */
public class Hymnifier {
	private boolean convertMidiFiles;
	public Hymnifier() {
		this.convertMidiFiles = false;
	}
	
	public static void main(String[] args) {			
		Hymnifier hymnifier = new Hymnifier();
		
		if(hymnifier.convertMidiFiles) {
			String pathOfHymnsToConvert = ""; //insert the base path of your midi-folder-to-convert here			
			MidiTransposer mt = new MidiTransposer(pathOfHymnsToConvert);
			mt.convertMidiFiles();
		}
		

	}

}
