package studentskills.util;

/**
 * @author Madhan Thangavel
 *
 */
public class MyLogger {

	public static enum DebugLevel {
		CONSTRUCTOR, FILE_PROCESSOR, NONE, DEBUG, ERROR
	};

	private static DebugLevel debugLevel;

	/**
	 * @param levelIn
	 */
	public static void setDebugValue(int levelIn) {
		switch (levelIn) {
			case 4:
				debugLevel = DebugLevel.ERROR;
				break;
			case 3:
				debugLevel = DebugLevel.DEBUG;
				break;
			case 2:
				debugLevel = DebugLevel.CONSTRUCTOR;
				break;
			case 1:
				debugLevel = DebugLevel.FILE_PROCESSOR;
				break;
			default:
				debugLevel = DebugLevel.NONE;
				break;
		}
	}

	/**
	 * @param levelIn
	 */
	public static void setDebugValue(DebugLevel levelIn) {
		debugLevel = levelIn;
	}

	/**
	 * @param message
	 * @param levelIn
	 */
	public static void writeMessage(String message, DebugLevel levelIn) {
		if (levelIn == debugLevel)
			System.out.println(message);
	}

	/**
	 * @return String
	 */
	public String toString() {
		return "The debug level has been set to the following " + debugLevel;
	}
}
