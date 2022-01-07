package studentskills.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
//import java.nio.file.Files;
//import java.nio.file.Paths;

import studentskills.util.MyLogger.DebugLevel;

/**
 * @author Madhan Thangavel
 *
 */
public class Results implements FileDisplayInterface, StdoutDisplayInterface {

	private File file;

	/**
	 * To create output file if not present.
	 * 
	 * @param filePath
	 */
	public Results(String filePath) {
		MyLogger.writeMessage("writing into the output file", DebugLevel.DEBUG);
		/*
		 * if (!Files.exists(Paths.get(filePath))) {
		 * MyLogger.writeMessage("The given file path '" + file.getAbsolutePath() +
		 * "' is not found...", DebugLevel.ERROR); System.exit(0); }
		 */

		file = new File(filePath);
		MyLogger.writeMessage("output file is successfully created", DebugLevel.DEBUG);
	}

	/**
	 * To write the o/p to output.txt.
	 * 
	 * @param line
	 */
	@Override
	public void writeToFile(String line) {
		PrintWriter pw = null;
		try {
			pw = new PrintWriter(file);
			pw.write(line);
		} catch (FileNotFoundException e) {
			MyLogger.writeMessage("The given file path '" + file.getAbsolutePath() + "' is not found...",
					DebugLevel.ERROR);
		} finally {
			if (pw != null)
				pw.close();
		}
	}

}
