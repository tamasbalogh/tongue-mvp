package baloght.tongue.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

import baloght.tongue.R;

/**
 * Created by baloght on 2018.04.17..
 */

public class DateUtils {

    private static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");

    public static int getDayOfWeek(){
        int dayOfWeek = 0;
        try {
            Date date = dateFormat.parse(getToday());
            GregorianCalendar calendar = new GregorianCalendar();
            calendar.setTime(date);
            dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
        } catch (ParseException e){
            LogUtil.log(e.getMessage());
        }

        switch (dayOfWeek){
            case Calendar.MONDAY:
                dayOfWeek = 1;
                break;
            case Calendar.TUESDAY:
                dayOfWeek = 2;
                break;
            case Calendar.WEDNESDAY:
                dayOfWeek = 3;
                break;
            case Calendar.THURSDAY:
                dayOfWeek = 4;
                break;
            case Calendar.FRIDAY:
                dayOfWeek = 5;
                break;
            case Calendar.SATURDAY:
                dayOfWeek = 6;
                break;
            case Calendar.SUNDAY:
                dayOfWeek = 7;
                break;
        }
        return dayOfWeek;
    }

    public static String[] getDatesOfWeek(){
        String[] datesOfWeek = new String[7];

        int dayOfWeek = getDayOfWeek();
        Date now = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(now);

        //Set calendar to the first day of the week.
        while (dayOfWeek > 1){
            cal.add(Calendar.DATE,-1);
            dayOfWeek--;
        }

        //Save dates of the current week (from monday to sunday)
        for(int i =0; i < 7 ; i++){
            datesOfWeek[i]= dateFormat.format(cal.getTime());
            cal.add(Calendar.DATE, +1);
        }

        return datesOfWeek;
    }

    public static String getToday(){
        Date date = new Date();
        return dateFormat.format(date);
    }

}
