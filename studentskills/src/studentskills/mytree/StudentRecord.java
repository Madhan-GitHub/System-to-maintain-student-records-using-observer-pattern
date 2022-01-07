package studentskills.mytree;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author Madhan Thangavel
 *
 */
public class StudentRecord implements SubjectI, ObserverI {

	private Integer uniqueNumber;
	private Integer bNumber;
	private String firstName;
	private String lastName;
	private Double gpa;
	private String major;
	private Set<String> skill;
	private List<ObserverI> observerList = new ArrayList<>();

	/**
	 * Constructor for unique number.
	 */
	public StudentRecord() {
		this.uniqueNumber = TreeHelper.getUniqueNumber();
	}

	/**
	 * @return Integer
	 */
	public Integer getbNumber() {
		return bNumber;
	}

	/**
	 * @param bNumber
	 */
	public void setbNumber(Integer bNumber) {
		this.bNumber = bNumber;
	}

	/**
	 * @return String
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @param firstName
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * @return String
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * @param lastName
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * @return Double
	 */
	public Double getGpa() {
		return gpa;
	}

	/**
	 * @param gpa
	 */
	public void setGpa(Double gpa) {
		this.gpa = gpa;
	}

	/**
	 * @return String
	 */
	public String getMajor() {
		return major;
	}

	/**
	 * @param major
	 */
	public void setMajor(String major) {
		this.major = major;
	}

	/**
	 * @return Set<String>
	 */
	public Set<String> getSkill() {
		return skill;
	}

	/**
	 * @param skill
	 */
	public void setSkill(Set<String> skill) {
		this.skill = skill;
	}

	/**
	 * @return Integer
	 */
	public Integer getUniqueNumber() {
		return uniqueNumber;
	}

	/**
	 * @param uniqueNumber
	 */
	public void setUniqueNumber(Integer uniqueNumber) {
		this.uniqueNumber = uniqueNumber;
	}

	/**
	 * @return List<ObserverI>
	 */
	public List<ObserverI> getObserverList() {
		return observerList;
	}

	/**
	 * Returns values from the constructor.
	 *
	 * @return String
	 */
	@Override
	public String toString() {
		return "StudentRecord [bNumber=" + bNumber + ", firstName=" + firstName + ", lastName=" + lastName + ", gpa="
				+ gpa + ", major=" + major + ", skill=" + skill + "]";
	}

	/**
	 * @param studentRecord
	 * @return StudentRecord
	 */
	@Override
	public StudentRecord addObserver(StudentRecord studentRecord) {
		this.observerList.add(studentRecord);
		return this;
	}

	/**
	 * Constructor for updating the student record.
	 * 
	 * @param studentRecord
	 */
	@Override
	public void update(StudentRecord studentRecord) {
		this.bNumber = studentRecord.getbNumber();
		this.firstName = studentRecord.getFirstName();
		this.lastName = studentRecord.getLastName();
		this.gpa = studentRecord.getGpa();
		this.major = studentRecord.getMajor();
		this.skill = studentRecord.getSkill();
	}

	/**
	 * Notify function to notify all the observer.
	 */
	@Override
	public void notifyAllObservers() {
		this.getObserverList().stream().forEach(c -> c.update(this));
	}

	/**
	 * @return String
	 */
	public String printStudentRecord() {
		return this.uniqueNumber + " : " + this.getbNumber() + ":"
				+ this.getSkill().stream().collect(Collectors.joining(","));
	}

}
