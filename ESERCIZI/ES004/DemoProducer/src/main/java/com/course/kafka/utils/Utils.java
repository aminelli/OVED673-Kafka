package com.course.kafka.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Utils {


    public static void printDateDiff(Date startDate, Date endDate, SimpleDateFormat formatter) {

        long timeMS = endDate.getTime() - startDate.getTime();
        double timeSEC = timeMS / 1000.0;

        System.out.print("Start    : " + formatter.format(startDate));
        System.out.print("End      : " + formatter.format(endDate));
        System.out.print("TIME MS  : " + timeMS);
        System.out.print("TIME SEC : " + timeSEC);

    }


}
