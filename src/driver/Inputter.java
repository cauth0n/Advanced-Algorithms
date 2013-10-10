package driver;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Inputter {
	protected String file;
	protected List<String> data;

	public Inputter(String file) {
		this.file = file;
	}

	public void input() {
		data = new ArrayList<>();
		try {
			File inFile = new File(file);
			Scanner in = new Scanner(inFile);
			while (in.hasNext()) {
				data.add(in.nextLine());
			}
			in.close();
		} catch (FileNotFoundException e) {
			System.out.println("Could not find letter file");
			e.printStackTrace();
		}
	}

	public List<String> getData() {
		return data;
	}
}
