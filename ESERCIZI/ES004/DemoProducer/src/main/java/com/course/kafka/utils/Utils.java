package com.course.kafka.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Utils {


    public static void printDateDiff(Date startDate, Date endDate, SimpleDateFormat formatter) {

        long timeMS = endDate.getTime() - startDate.getTime();
        double timeSEC = timeMS / 1000.0;
        double timeMIN = timeSEC / 60.0;

        System.out.println("-".repeat(30));
        System.out.println("STATISTICHE DEI POVERI");
        System.out.println("-".repeat(30));
        System.out.println("Start    : " + formatter.format(startDate));
        System.out.println("End      : " + formatter.format(endDate));
        System.out.println("TIME MS  : " + timeMS);
        System.out.println("TIME SEC : " + timeSEC);
        System.out.println("TIME MIN : " + timeMIN);
        System.out.println("-".repeat(30));


    }


}
