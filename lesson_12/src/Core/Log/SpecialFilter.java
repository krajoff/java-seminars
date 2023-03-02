package Core.Log;

import java.util.logging.Filter;
import java.util.logging.Level;
import java.util.logging.LogRecord;

public class SpecialFilter implements Filter {

	@Override
	public boolean isLoggable(LogRecord log) {
		return log.getLevel() != Level.CONFIG;
	}

}
