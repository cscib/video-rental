package com.cscib.videorental.util;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;

public class DateUtils {


    public static int calculateRentalDays(OffsetDateTime rentedOn, OffsetDateTime expectedReturnOn) {

        OffsetDateTime rentedOnStartOfDay =  rentedOn.toLocalDate()
                .atTime(LocalTime.MIDNIGHT)
                .atOffset(ZoneOffset.UTC);

        OffsetDateTime expectedReturnOnStartOfDay = expectedReturnOn.toLocalDate()
                .atTime(LocalTime.MIDNIGHT)
                .atOffset(ZoneOffset.UTC);

        return Period.between(rentedOnStartOfDay.toLocalDate(), expectedReturnOnStartOfDay.toLocalDate()).getDays();
    }

    public static int calculateSurchargeDays(OffsetDateTime expectedReturnOn, OffsetDateTime returnedOn) {

        if (expectedReturnOn.isBefore(returnedOn)) {
            return calculateRentalDays(expectedReturnOn, returnedOn);
        }
        return 0;
    }



    public enum ZoneIdEnum{
        GMT("GMT"),
        UTC("UTC");

        private String zoneId;
        ZoneIdEnum(String zoneId) {
            this.zoneId = zoneId;
        }

        public static String getZoneId(ZoneIdEnum zoneIdEnum){
            return zoneIdEnum.zoneId;
        }
    }

    public static OffsetDateTime getCurrentTime() {
        return OffsetDateTime.now();
    }

    //e.g. ZoneId.of("GMT+01:00")
    public static OffsetDateTime getCurrentTime(ZoneIdEnum zoneIdEnum) {
        return OffsetDateTime.now(ZoneId.of(zoneIdEnum.zoneId));
    }




}
