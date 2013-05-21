package driver;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 * @author cauth0n
 */
public class Simulator {
	private String fileName;
	private Matrix matrix;

	public Simulator(String fileName) {
		this.fileName = fileName;
	}

	public void readValues() {
		try {
			Scanner in = new Scanner(new File(fileName));
			while (in.hasNext()) {
				
			}
		} catch (FileNotFoundException e) {
			System.out.println("Could not find dependency file");
		}
	}

	public void initMatrix(int dimensions) {
		matrix = new Matrix(dimensions);
	}

	public void matrixToFile(String outFile) {
		PrintWriter out;
		try {
			out = new PrintWriter(new File(outFile));
			out.println(matrix.toString());
		} catch (FileNotFoundException e) {
			System.out.println("Unable to create output file");
		}

	}
}
