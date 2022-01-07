package studentskills.driver;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Paths;

import studentskills.mytree.TreeHelper;
import studentskills.util.FileProcessor;
import studentskills.util.MyLogger;
import studentskills.util.MyLogger.DebugLevel;
import studentskills.util.Results;

/**
 * @author Madhan Thangavel
 *
 */
public class Driver {

	/**
	 * Driver code to perform insert/search/modify student skills..
	 * 
	 * @param args
	 * @return String The processed line read from the input file.
	 * @exception Exception On error encountered when handling the input.
	 */
	public static void main(String[] args) throws Exception {

		/*
		 * As the build.xml specifies the arguments as input,output or metrics, in case
		 * the argument value is not given java takes the default value specified in
		 * build.xml. To avoid that, below condition is used
		 */
		if ((args.length != 7) || (args[0].equals("${input}")) || (args[1].equals("${modify}"))
				|| (args[2].equals("${out1}")) || (args[3].equals("${out2}")) || args[4].equals("${out3}")) {
			System.err.printf("Error: Incorrect number of arguments. Program accepts %d arguments.");
			System.exit(0);
		}

		if (!Files.exists(Paths.get(args[0])) || !Files.exists(Paths.get(args[1]))
				|| !Files.exists(Paths.get(args[5]))) {
			System.err.printf("Error: Given input or modify or error file doesn't exist..");
			System.exit(0);
		}
		System.out.println("Hello World! Lets get started with the assignment - studentskills");
		try {
			PrintStream ps = new PrintStream(new File(args[5]));
			System.setOut(ps);
			MyLogger.setDebugValue(Integer.parseInt(args[6]));

			MyLogger.writeMessage("Student skill assignment gets started...!", DebugLevel.DEBUG);
			MyLogger.writeMessage("Initilizing  the Result object", DebugLevel.DEBUG);
			Results resultRoot = new Results(args[2]);
			Results resultBk1 = new Results(args[3]);
			Results resultBk2 = new Results(args[4]);
			TreeHelper tree = new TreeHelper();

			MyLogger.writeMessage("Initiating the input file process", DebugLevel.DEBUG);
			FileProcessor inputFile = new FileProcessor(args[0]);
			String line = inputFile.poll();
			while (line != null) {
				tree.createStudentRecords(line);
				line = inputFile.poll();
			}
			inputFile.close();
			MyLogger.writeMessage("The input file process is completed...!", DebugLevel.DEBUG);

			MyLogger.writeMessage("Initiating the modify file process", DebugLevel.DEBUG);
			FileProcessor modifyFile = new FileProcessor(args[1]);
			String modifyLine = modifyFile.poll();
			while (modifyLine != null) {
				tree.modifyStudentRecords(modifyLine);
				modifyLine = modifyFile.poll();
			}
			modifyFile.close();
			MyLogger.writeMessage("The modify file process is completed...!", DebugLevel.DEBUG);

			MyLogger.writeMessage("print the student record and replica", DebugLevel.DEBUG);
			tree.printStudentRecords(resultRoot, resultBk1, resultBk2);
			MyLogger.writeMessage("Output is stored in output1.txt, output2.txt, output3.txt", DebugLevel.DEBUG);
		} catch (FileNotFoundException e) {
			MyLogger.writeMessage("FileNotFoundException: Thrown from driver ", DebugLevel.ERROR);
			MyLogger.writeMessage(e.getMessage(), DebugLevel.ERROR);
		} catch (IOException e) {
			MyLogger.writeMessage("IOException: Thrown from driver", DebugLevel.ERROR);
			MyLogger.writeMessage(e.getMessage(), DebugLevel.ERROR);
		} catch (Exception e) {
			MyLogger.writeMessage("Exception: Thrown from driver ", DebugLevel.ERROR);
			MyLogger.writeMessage(e.getMessage(), DebugLevel.ERROR);
		}
	}

}
