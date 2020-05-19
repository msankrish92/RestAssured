package week2.Assignment;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;

public class TimeToMilliSecondConvertor {

	
	public static void main(String[] args) {
		String input = "2020-05-19T12:24:08.623+0530";
		DateTimeZone zone = DateTimeZone.UTC; // Or DateTimeZone.UTC
		DateTime dateTime = new DateTime( input, zone );
		long millisecondsSinceUnixEpoch = dateTime.getMillis();
		System.out.println(millisecondsSinceUnixEpoch);
		
		 DateTime dt = new DateTime();
		 long millis = dt.getMillis();
		 System.out.println(millis);
	}
}
