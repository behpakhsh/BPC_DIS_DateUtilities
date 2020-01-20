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
        startSolarCalendar = new SolarCalendar(startDate);
        endSolarCalendar = new SolarCalendar(endDate);

        initYears(startDate, endDate);
        initMonths(context, startDate, endDate);
        initDays(startDate, endDate);
    }

    private void initYears(Date startDate, Date endDate) {
        int startYear = startSolarCalendar.year;
        int endYear = endSolarCalendar.year;
        years = new ArrayList<>();
        for (int i = startYear; i <= endYear; i++) {
            years.add(String.valueOf(i));
        }
    }

    private void initMonths(Context context, Date startDate, Date endDate) {
        int end = 12;
        int startYear = startSolarCalendar.year;
        int startMonth = startSolarCalendar.month;
        int endYear = endSolarCalendar.year;
        if (startYear == endYear) {
            end = endSolarCalendar.day;
        }
        months = new ArrayList<>();
        months.addAll(Arrays.asList(context.getResources().getStringArray(R.array.month)).subList(startMonth - 1, end));
    }

    private void initDays(Date startDate, Date endDate) {
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

    public List<String> getYears() {
        return years;
    }

    public void setYears(List<String> years) {
        this.years = years;
    }

    public List<String> getMonths() {
        return months;
    }

    public void setMonths(List<String> months) {
        this.months = months;
    }

    public List<String> getDays() {
        return days;
    }

    public void setDays(List<String> days) {
        this.days = days;
    }

}