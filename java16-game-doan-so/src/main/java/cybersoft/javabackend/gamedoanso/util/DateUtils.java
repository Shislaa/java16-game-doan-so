package cybersoft.javabackend.gamedoanso.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateUtils {
	private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
	
	public static String toString(LocalDateTime date) {
		return date.format(formatter);
	}
}
