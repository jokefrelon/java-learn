package APICourceCode;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class dateAPI {
	
	public void getDate() {
		Date date = new Date();

		DateFormat a = DateFormat.getDateInstance(DateFormat.LONG);
		DateFormat ses = DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.SHORT);

		ses = new SimpleDateFormat("yyyy/MM/dd HH-mm-ss");

		String sesa = ses.format(date);
		String b = a.format(date);

		System.out.println(sesa + "~~~" + b);
	}
}
