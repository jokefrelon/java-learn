package APICourceCode;

import java.util.Calendar;

public class CalenderL {
	public static void main(String[] args) {
		Calendar se = Calendar.getInstance();
		
		se.add(Calendar.YEAR, 1);
		
		int year = se.get(Calendar.YEAR);
		int month = se.get(Calendar.MONTH)+1;
		int day = se.get(Calendar.DAY_OF_MONTH);
		int hours = se.get(Calendar.HOUR_OF_DAY);
		int minute = se.get(Calendar.MINUTE);
		int sec = se.get(Calendar.SECOND);
		System.out.println(year + "-" + month + "-" + day + "-" + hours + "-" + minute + "-" + sec);
	}
}