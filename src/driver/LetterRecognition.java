package driver;

import java.io.File;
import java.io.FileNotFoundException;

public class LetterRecognition extends Inputter {

	public LetterRecognition(String file) {
		super(file);
		data = new String[20000];
	}

	@Override
	public String[] input() {
		try {
			File inFile = new File(file);

		} catch (FileNotFoundException e) {
			System.out.println();
		}
	}
}
