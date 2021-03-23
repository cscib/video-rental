package com.cscib.videorental.util;

import java.time.OffsetDateTime;
import java.time.ZoneId;

public class DateUtils {

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
