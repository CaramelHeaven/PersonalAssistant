package com.volgagas.personalassistant.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

/**
 * Created by CaramelHeaven on 17:22, 27/12/2018.
 * Copyright (c) 2018 VolgaGas. All rights reserved.
 */
public class UtilsDateTimeProvider {
    private static DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'", Locale.getDefault());
    private static DateFormat dateFormatForWorker = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
    private static DateFormat dateHistoryFormat = new SimpleDateFormat("MMM dd, yyyy", new Locale("RU"));
    private static DateFormat timeHistoryFormat = new SimpleDateFormat("HH:mm", Locale.getDefault());
    private static DateFormat dateBirthdayFormat = new SimpleDateFormat("dd MMMM yyyy", new Locale("RU"));

    private static SimpleDateFormat serverFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'",
            Locale.getDefault());

    private static UtilsDateTimeProvider INSTANCE;

    public static UtilsDateTimeProvider getInstance() {
        if (INSTANCE == null) {
            synchronized (UtilsDateTimeProvider.class) {
                if (INSTANCE == null) {
                    INSTANCE = new UtilsDateTimeProvider();
                }
            }
        }

        return INSTANCE;
    }

    /**
     * Date for  naming photos
     *
     * @return data format photos.png
     */
    public static String folderNameTimeFormat() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        return dateFormat.format(Calendar.getInstance().getTime());
    }

    /**
     * Time format for worker service - create nomenclature
     *
     * @return format fime. For example - 2019-02-22
     */
    public static String workerServiceTime() {
        return dateFormatForWorker.format(Calendar.getInstance().getTime());
    }

    /**
     * @return next day data format for server. Example: 2019-01-20'T'00:00:00Z
     */
    public static String getNextDayDataFormat() {
        dateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
        Calendar calendar = Calendar.getInstance();

        calendar.add(Calendar.DAY_OF_YEAR, 1);
        String result = dateFormat.format(calendar.getTime());

        return result + "00:00:00Z";
    }

    /**
     * @return last day data format for server. Example: 2019-01-19'T'23:59:59ZZ
     */
    public static String formatLastDayDataFormat() {
        dateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
        Calendar calendar = Calendar.getInstance();

        calendar.add(Calendar.DAY_OF_YEAR, -1);
        String result = dateFormat.format(calendar.getTime());

        return result + "23:59:59Z";
    }

    /**
     * Current data format for history request
     */
    public static String formatCurrentDayEnd() {
        dateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
        String result = dateFormat.format(Calendar.getInstance().getTime());

        return result + "23:59:59Z";
    }

    /**
     * @return current data format. Example - 2019-03-11T16:29:59Z
     */
    public static String getCurrentFormatDateTime() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
        return dateFormat.format(Calendar.getInstance().getTime());
    }

    /**
     * Start date format for history date
     */
    public static String formatHistoryDate(String serverDate) {
        try {
            if (!serverDate.equals("")) {
                Date date = serverFormat.parse(serverDate);
                return dateHistoryFormat.format(date);
            } else {
                return "";
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * Start time format for history time
     */
    public static String formatHistoryTime(String serverTime) {
        try {
            serverFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
            if (serverFormat.parse(serverTime) != null) {
                return timeHistoryFormat.format(serverFormat.parse(serverTime));
            } else {
                return "";
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return "";
    }

    public static String formatBirthday(String serverTime) {
        try {
            serverFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
            return dateBirthdayFormat.format(serverFormat.parse(serverTime));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return "";
    }
}
