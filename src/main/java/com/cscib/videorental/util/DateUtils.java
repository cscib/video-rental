package com.cscib.videorental.util;

import java.time.*;

public class DateUtils {

    public static int calculateRentalDays(OffsetDateTime rentedOn, OffsetDateTime expectedReturnOn) {
        OffsetDateTime rentedOnStartOfDay = OffsetDateTime.of(rentedOn.toLocalDateTime(), ZoneOffset.from(LocalTime.MIDNIGHT));
        OffsetDateTime expectedReturnOnStartOfDay = OffsetDateTime.of(expectedReturnOn.toLocalDateTime(), ZoneOffset.from(LocalTime.MIDNIGHT));

        return Period.between(rentedOnStartOfDay.toLocalDate(), expectedReturnOnStartOfDay.toLocalDate()).getDays();
    }

    public static int calculateSurchargeDays(OffsetDateTime rentedOn, OffsetDateTime expectedReturnOn, OffsetDateTime returnedOn) {

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
