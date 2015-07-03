package process;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Format {
	public static String formatDate(String date) throws ParseException{	
		return new SimpleDateFormat("dd-MM-yyyy").format(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S").parse(date))+"";
	}
}
