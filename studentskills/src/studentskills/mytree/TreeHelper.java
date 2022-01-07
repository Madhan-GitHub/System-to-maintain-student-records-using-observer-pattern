package studentskills.mytree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import studentskills.util.MyLogger;
import studentskills.util.MyLogger.DebugLevel;
import studentskills.util.Results;

/**
 * @author Madhan Thangavel
 *
 */

public class TreeHelper {

	private static List<StudentRecord> studentRecordList = new ArrayList<>();
	private static List<StudentRecord> studentRecordBK1List = new ArrayList<>();
	private static List<StudentRecord> studentRecordBKL2ist = new ArrayList<>();

	private static int uniqueNumber = 0;

	/**
	 * To create student record.
	 * 
	 * @param line
	 * @exception NumberFormatException
	 */
	public void createStudentRecords(String line) throws NumberFormatException {
		MyLogger.writeMessage("TreeHelper - createStudentRecords - started...", DebugLevel.DEBUG);
		if (line.matches("\\d+:\\w+,\\w+,[0-9.]+,\\w+,[a-zA-z0-9,]+")) {
			MyLogger.writeMessage("TreeHelper - createStudentRecords - The given Input line is valid to process",
					DebugLevel.DEBUG);
			StudentRecord studentRecordRoot = new StudentRecord();
			studentRecordRoot.setbNumber(Integer.parseInt(line.substring(0, line.indexOf(":"))));
			List<String> values = Arrays.asList(line.substring(line.indexOf(":") + 1).split(","));
			studentRecordRoot.setFirstName(values.get(0));
			studentRecordRoot.setLastName(values.get(1));
			studentRecordRoot.setGpa(Double.parseDouble(values.get(2)));
			studentRecordRoot.setMajor(values.get(3));
			Set<String> skills = new HashSet<String>();
			for (int i = 4; i < values.size(); i++) {
				skills.add(values.get(i));
			}
			if (skills.size() < 11) {
				studentRecordRoot.setSkill(skills);
				StudentRecord studentRecordBackup1 = new StudentRecord();
				StudentRecord studentRecordBackup2 = new StudentRecord();
				studentRecordRoot.addObserver(studentRecordBackup1).addObserver(studentRecordBackup2);
				studentRecordBackup1.addObserver(studentRecordRoot).addObserver(studentRecordBackup2);
				studentRecordBackup2.addObserver(studentRecordRoot).addObserver(studentRecordBackup1);
				studentRecordRoot.notifyAllObservers();
				studentRecordList.add(studentRecordRoot);
				studentRecordBK1List.add(studentRecordBackup1);
				studentRecordBKL2ist.add(studentRecordBackup2);
				MyLogger.writeMessage(
						"TreeHelper - createStudentRecords - The StudentRecord with two backups are succesfully created...",
						DebugLevel.DEBUG);
			} else {
				// catch if skill size above 10
				MyLogger.writeMessage("TreeHelper - createStudentRecords - The given skills exists the max length-10",
						DebugLevel.ERROR);
			}
		} else {
			// invalid line
			MyLogger.writeMessage("TreeHelper - createStudentRecords - The given Input line is invalid to process",
					DebugLevel.ERROR);
		}
		MyLogger.writeMessage("TreeHelper - createStudentRecords - completed...!", DebugLevel.DEBUG);
	}

	/**
	 * To get unique id for each tree.
	 * 
	 * @return Integer
	 */
	public static Integer getUniqueNumber() {
		return uniqueNumber++;
	}

	/**
	 * To Modify student record.
	 * 
	 * @param line
	 */
	public void modifyStudentRecords(String line) {
		MyLogger.writeMessage("TreeHelper - modifyStudentRecords - started...", DebugLevel.DEBUG);
		if (line.matches("\\d+,\\d+,\\w+:\\w*")) {
			MyLogger.writeMessage("TreeHelper - modifyStudentRecords - The given Input line is valid to process",
					DebugLevel.DEBUG);
			String[] value = line.split(",");
			Integer bnumber = Integer.parseInt(value[1]);
			StudentRecord student = studentRecordList.stream().filter(c -> c.getbNumber().equals(bnumber)).findFirst()
					.orElse(null);
			if (student != null) {
				if (value[2].split(":").length > 1) {
					String oldValue = value[2].split(":")[0];
					String newValue = value[2].split(":")[1];
					if (student.getFirstName().equals(oldValue)) {
						student.setFirstName(newValue);
					} else if (student.getLastName().equals(oldValue)) {
						student.setLastName(newValue);
					} else if (student.getMajor().equals(oldValue)) {
						student.setMajor(newValue);
					} else if (student.getSkill().contains(oldValue)) {
						student.getSkill().remove(oldValue);
						student.getSkill().add(newValue);
					} else {
						if (oldValue.matches("[0-9.]+")) {
							MyLogger.writeMessage(
									"TreeHelper - modifyStudentRecords - GPA is not allowed to modify for the BNumber["
											+ bnumber + "]",
									DebugLevel.ERROR);
						} else
							MyLogger.writeMessage(
									"TreeHelper - modifyStudentRecords - The given ORIG_VALUE [" + oldValue
											+ "] value is not found in given bNumber [" + bnumber + "]",
									DebugLevel.ERROR);
					}
					student.notifyAllObservers();
					MyLogger.writeMessage(
							"TreeHelper - modifyStudentRecords - The given Input line is successfully processed",
							DebugLevel.DEBUG);
				} else {
					// there is no new value to update it.
					MyLogger.writeMessage("TreeHelper - modifyStudentRecords - There is no NEW_VALUE to update",
							DebugLevel.ERROR);
				}

			} else {
				// there is no existing bnumber
				MyLogger.writeMessage(
						"TreeHelper - modifyStudentRecords - The given bnumber [" + bnumber + "] is not found...",
						DebugLevel.ERROR);
			}
		} else {
			// invalid line
			MyLogger.writeMessage("TreeHelper - modifyStudentRecords - The given Input line is invalid to process",
					DebugLevel.ERROR);
		}
		MyLogger.writeMessage("TreeHelper - modifyStudentRecords - completed...!", DebugLevel.DEBUG);
	}

	/**
	 * To print student records.
	 * 
	 * @param resultRoot
	 * @param resultBk1
	 * @param resultBk2
	 */
	public void printStudentRecords(Results resultRoot, Results resultBk1, Results resultBk2) {
		MyLogger.writeMessage("TreeHelper - printStudentRecords - started...", DebugLevel.DEBUG);
		resultRoot.writeToFile(studentRecordList.stream().sorted((a, b) -> a.getbNumber().compareTo(b.getbNumber()))
				.map(StudentRecord::printStudentRecord).collect(Collectors.joining("\n")));
		resultBk1.writeToFile(studentRecordBK1List.stream().sorted((a, b) -> a.getbNumber().compareTo(b.getbNumber()))
				.map(StudentRecord::printStudentRecord).collect(Collectors.joining("\n")));
		resultBk2.writeToFile(studentRecordBKL2ist.stream().sorted((a, b) -> a.getbNumber().compareTo(b.getbNumber()))
				.map(StudentRecord::printStudentRecord).collect(Collectors.joining("\n")));
		MyLogger.writeMessage("TreeHelper - printStudentRecords - completed...!", DebugLevel.DEBUG);
	}

}