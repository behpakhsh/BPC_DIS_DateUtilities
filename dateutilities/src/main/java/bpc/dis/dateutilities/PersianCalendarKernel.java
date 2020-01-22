package bpc.dis.dateutilities;

import android.content.Context;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import bpc.dis.dateutilities.LeapYearHelper.LeapYearHelper;
import bpc.dis.dateutilities.SolarCalendar.SolarCalendar;

public class PersianCalendarKernel {

    private SolarCalendar startSolarCalendar;
    private SolarCalendar endSolarCalendar;
    private List<String> defaultMonths;
    private List<String> years;
    private List<String> months;
    private List<String> days;

    public PersianCalendarKernel(Context context, Date startDate, Date endDate) {
        if (startDate.after(endDate)) {
            years = new ArrayList<>();
            months = new ArrayList<>();
            days = new ArrayList<>();
            return;
        }

        defaultMonths = Arrays.asList(context.getResources().getStringArray(R.array.month)).subList(0, 12);

        startSolarCalendar = new SolarCalendar(startDate);
        endSolarCalendar = new SolarCalendar(endDate);

        initYears();
        initMonths();
        initDays();
    }

    private void initYears() {
        int startYear = startSolarCalendar.year;
        int endYear = endSolarCalendar.year;
        years = new ArrayList<>();
        for (int i = startYear; i <= endYear; i++) {
            years.add(String.valueOf(i));
        }
    }

    private void initMonths() {
        int end = 12;
        int startYear = startSolarCalendar.year;
        int startMonth = startSolarCalendar.month;
        int endYear = endSolarCalendar.year;
        if (startYear == endYear) {
            end = endSolarCalendar.month;
        }
        months = new ArrayList<>();
        months.addAll(defaultMonths.subList(startMonth - 1, end));
    }

    private void initDays() {
        int endDay = 31;
        int startYear = startSolarCalendar.year;
        int startMonth = startSolarCalendar.month;
        int startDay = startSolarCalendar.day;
        int endYear = endSolarCalendar.year;
        if (startYear == endYear) {
            endDay = endSolarCalendar.day;
        }
        if (startMonth > 6) {
            if (!LeapYearHelper.checkLeapYear(startYear)) {
                if (startMonth == 12)
                    endDay = 29;
                else
                    endDay = 30;
            } else {
                endDay = 30;
            }
        }
        days = new ArrayList<>();
        for (int i = startDay; i <= endDay; i++) {
            days.add(String.valueOf(i));
        }
    }


    public List<String> getMonthsByYear(int year) {
        int startYear = startSolarCalendar.year;
        int endYear = endSolarCalendar.year;
        if (year > startYear && year < endYear) {
            return defaultMonths.subList(0, 12);
        }
        if (year == startYear) {
            return defaultMonths.subList(startSolarCalendar.month - 1, 12);
        }
        if (year == endYear) {
            return defaultMonths.subList(0, endSolarCalendar.month - 1);
        }
        return new ArrayList<>();
    }

    public List<String> getDaysByMonth(int year, String stringMonth) {
        int month = 1;
        for (int i = 0; i < defaultMonths.size(); i++) {
            if (defaultMonths.get(i).equals(stringMonth)) {
                month = i + 1;
                break;
            }
        }
        List<String> days = new ArrayList<>();
        int startYear = startSolarCalendar.year;
        int endYear = endSolarCalendar.year;
        int start;
        int end;
        if (startYear == endYear) {
            start = startSolarCalendar.day;
            end = 31;
        } else if (year == startYear) {
            start = 1;
            end = 31;
            int startMonth = startSolarCalendar.month;
            if (month == startMonth) {
                start = startSolarCalendar.day;
            }
        } else if (year == endYear) {
            start = 1;
            end = 31;
            int endMonth = endSolarCalendar.month;
            if (month == endMonth) {
                end = endSolarCalendar.day;
            }
        } else {
            start = 1;
            end = 31;
        }
        if (month > 6) {
            if (!LeapYearHelper.checkLeapYear(year)) {
                if (month == 12)
                    end = 29;
                else
                    end = 30;
            } else {
                end = 30;
            }
        }
        for (int i = start; i <= end; i++) {
            days.add(String.valueOf(i));
        }
        return days;
    }


    public List<String> getYears() {
        return years;
    }

    public List<String> getMonths() {
        return months;
    }

    public List<String> getDays() {
        return days;
    }

}