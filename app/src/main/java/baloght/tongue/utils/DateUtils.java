package baloght.tongue.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by baloght on 2018.04.17..
 */

public class DateUtils {

    private String format = "yyyyMMdd";
    private SimpleDateFormat df = new SimpleDateFormat(format);

    public static int  getDayOfWeek(){
        Date now = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(now);
        return calendar.get(Calendar.DAY_OF_WEEK);
    }

    public static String getToday(){
        Date date = new Date();
        DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        return dateFormat.format(date);
    }

    public static int getWeekOfYear(String today){

    }

}
