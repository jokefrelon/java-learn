package APICourceCode;

import java.text.DateFormat;
import java.util.Date;

public class dateAPI {
	public static void main(String[] args) {
		Date date = new Date();
		DateFormat ses = DateFormat.getDateTimeInstance();
		String sesa = ses.format(date);
		System.out.println(sesa);
		
	}
}
