package APICourceCode;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;

public class ReverseDateFormat {
	public static void main(String[] args) throws ParseException {
		String str = "2019-07-02";
		DateFormat se = DateFormat.getDateInstance();
		Date date =se.parse(str);
		System.out.println(date);
	}
}
