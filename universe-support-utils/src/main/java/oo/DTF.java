package oo;

import java.time.format.DateTimeFormatter;

public final class DTF {

    public static final DateTimeFormatter DATE_TIME_DEFAULT = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    public static final DateTimeFormatter DATE_DEFAULT = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    public static final DateTimeFormatter TIME_DEFAULT = DateTimeFormatter.ofPattern("HH:mm:ss");
    //
    public static final DateTimeFormatter DATE_YYMMDD = DateTimeFormatter.ofPattern("yyMMdd");
    public static final DateTimeFormatter DATE_YYYYMMDD = DateTimeFormatter.ofPattern("yyyyMMdd");
    public static final DateTimeFormatter TIME_HHMM = DateTimeFormatter.ofPattern("HHmm");
    public static final DateTimeFormatter DATE_TIME_YYMMDDHHMM = DateTimeFormatter.ofPattern("yyMMddHHmm");
    //
    public static final DateTimeFormatter DATE_TIME_COMPACT = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
}
