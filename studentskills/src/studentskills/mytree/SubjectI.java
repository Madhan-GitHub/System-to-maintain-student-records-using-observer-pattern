package studentskills.mytree;

/**
 * @author Madhan Thangavel
 *
 */

/**
 * Interface for subject interface to include notify and add observer functionalities.
 */
public interface SubjectI {

	public StudentRecord addObserver(StudentRecord studentRecord);

	public void notifyAllObservers();

}