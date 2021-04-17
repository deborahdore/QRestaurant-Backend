package com.certimetergroup.qrestaurant.utility;

import com.certimetergroup.qrestaurant.enumeration.ResponseType;
import com.certimetergroup.qrestaurant.exception.FailureException;
import org.springframework.http.HttpStatus;

import java.nio.file.attribute.FileTime;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class DateUtility {

    public static Timestamp selectPassedDate(Integer pastHour) {
        return new Timestamp(System.currentTimeMillis() - TimeUnit.HOURS.toMillis(pastHour));
    }

    public static boolean isBefore(Timestamp t1, Timestamp t2) {
        return t1.before(t2);
    }

    public static boolean isBefore1DaysAgo(Timestamp t1) {
        return t1.before(selectPassedDate(24));
    }

    public static boolean isBefore1DaysAgo(FileTime t1) {
        return new Timestamp(t1.toMillis()).before(selectPassedDate(24));
    }

    public static Timestamp formatToTimestamp(String date) {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date parsedDate = dateFormat.parse(date);
            return new Timestamp(parsedDate.getTime());
        } catch (ParseException e) {
            throw new FailureException(ResponseType.UNEXPECTED_ERROR, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
