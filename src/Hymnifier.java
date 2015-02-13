import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

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
	private boolean getChordStats;
	public Hymnifier() {
		this.convertMidiFiles = false;
		this.getChordStats = false; 
			//the python script works when called from the terminal (i.e. python ChordStats.py),
			//but seems to have problems with generating large files
	}	
	
	public static void main(String[] args) {			
		Hymnifier hymnifier = new Hymnifier();
				
		if(hymnifier.convertMidiFiles) {
			String pathOfHymnsToConvert = ""; //INSERT the base path of your midi-folder-to-convert here			
			MidiTransposer mt = new MidiTransposer(pathOfHymnsToConvert);
			mt.convertMidiFiles();
		}
		if(hymnifier.getChordStats) {
			try {
				final String dir = System.getProperty("user.dir");
				Process p = Runtime.getRuntime().exec("python " + dir + "/music21/ChordStats.py");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
