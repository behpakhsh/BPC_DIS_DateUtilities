package bpc.dis.dateutilities.DateUtilities;

import android.annotation.SuppressLint;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class DateUtilities {

    public static Date addDays(Date date, int days) {
        return new Date(date.getTime() + days * 24 * 60 * 60 * 1000);
    }

    public static Date addDaysStable(Date date, int days) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, days);
        return calendar.getTime();
    }

    public static Date addHoursStable(Date date, int hours) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.HOUR, hours);
        return calendar.getTime();
    }

    public static Date addHours(Date date, int hours) {
        return new Date(date.getTime() + hours * 60 * 60 * 1000);
    }

    public static Date getGeorgianDate(String stringDate, String separator) {
        if (separator != null && !separator.isEmpty()) {
            PersianDate persianDate = new PersianDate();
            String[] stringValues = stringDate.split(separator);
            int[] values = persianDate.toGregorian(
                    Integer.parseInt(stringValues[0]),
                    Integer.parseInt(stringValues[1]),
                    Integer.parseInt(stringValues[2])
            );
            Calendar calendar = Calendar.getInstance();
            calendar.set(Calendar.YEAR, values[0]);
            calendar.set(Calendar.MONTH, values[1]);
            calendar.set(Calendar.DAY_OF_MONTH, values[2]);
            return calendar.getTime();
        } else {
            PersianDate persianDate = new PersianDate();
            int[] values = persianDate.toGregorian(
                    Integer.parseInt(stringDate.substring(0, 4)),
                    Integer.parseInt(stringDate.substring(4, 6)),
                    Integer.parseInt(stringDate.substring(6, 8))
            );
            Calendar calendar = Calendar.getInstance();
            calendar.set(Calendar.YEAR, values[0]);
            calendar.set(Calendar.MONTH, values[1]);
            calendar.set(Calendar.DAY_OF_MONTH, values[2]);
            return calendar.getTime();
        }
    }

    public static String getPersianDate(Date date, String separator) {
        PersianDate persianDate = new PersianDate(date);
        StringBuilder dateFormat = new StringBuilder();
        dateFormat.append(persianDate.getShYear());
        dateFormat.append(separator);
        String month = String.valueOf(persianDate.getShMonth());
        if (month.length() == 1) {
            month = "0" + month;
        }
        dateFormat.append(month);
        dateFormat.append(separator);
        String day = String.valueOf(persianDate.getShDay());
        if (day.length() == 1) {
            day = "0" + day;
        }
        dateFormat.append(day);
        return dateFormat.toString();
    }

    public static String getPersianDate(String date, String separator) {
        return date.substring(0, 4) +
                separator +
                date.substring(4, 6) +
                separator +
                date.substring(6, 8);
    }

    public static String getGeorgianDate(Date date, String separator) {
        StringBuilder dateFormat = new StringBuilder();
        dateFormat.append(date.getYear() + 1900);
        dateFormat.append(separator);
        String month = String.valueOf(date.getMonth() + 1);
        if (month.length() == 1) {
            month = "0" + month;
        }
        dateFormat.append(month);
        dateFormat.append(separator);
        String day = String.valueOf(date.getDay());
        if (day.length() == 1) {
            day = "0" + day;
        }
        dateFormat.append(day);
        return dateFormat.toString();
    }

    public static String getGeorgianDateTime(Date date, String dateSeparator, String hourSeparator, boolean separateDateAndTime) {
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        StringBuilder dateFormat = new StringBuilder();
        dateFormat.append(calendar.get(Calendar.YEAR));
        dateFormat.append(dateSeparator);
        String month = String.valueOf(calendar.get(Calendar.MONTH) + 1);
        if (month.length() == 1) {
            month = "0" + month;
        }
        dateFormat.append(month);
        dateFormat.append(dateSeparator);
        String day = String.valueOf(calendar.get(Calendar.DAY_OF_MONTH));
        if (day.length() == 1) {
            day = "0" + day;
        }
        dateFormat.append(day);
        if (separateDateAndTime) {
            dateFormat.append(" ");
        }
        String hour = String.valueOf(date.getHours());
        if (hour.length() == 1) {
            hour = "0" + hour;
        }
        dateFormat.append(hour);
        dateFormat.append(hourSeparator);
        String min = String.valueOf(date.getMinutes());
        if (min.length() == 1) {
            min = "0" + min;
        }
        dateFormat.append(min);
        dateFormat.append(hourSeparator);
        String sec = String.valueOf(date.getSeconds());
        if (sec.length() == 1) {
            sec = "0" + sec;
        }
        dateFormat.append(sec);
        return dateFormat.toString();
    }

    public static int getDayBetweenDate(Date oldDate, Date currentDate) {
        long diff = currentDate.getTime() - oldDate.getTime();
        return (int) (diff / (1000 * 60 * 60 * 24));
    }

    @SuppressLint("SimpleDateFormat")
    public Date stringToDate(String dateString, String dateFormatter) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(dateFormatter);
        try {
            return simpleDateFormat.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    @SuppressLint("SimpleDateFormat")
    public String dateToString(Date date, String dateFormatter) {
        return new SimpleDateFormat(dateFormatter).format(date);
    }

}