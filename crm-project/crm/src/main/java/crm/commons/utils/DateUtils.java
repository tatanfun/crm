package crm.commons.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 处理Date类型数据的工具类
 */
public class DateUtils {
    /**
     * 对指定的date对象进行格式化：yyy-MM-dd HH:mm:ss
     * @param date
     * @return
     */
    public static String formateDateTime(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyy-MM-dd HH:mm:ss");
        String dateStr = sdf.format(date);
        return dateStr;
    }
    /**
     * 对指定的date对象进行格式化：yyy-MM-dd
     * @param date
     * @return
     */
    public static String formateDate(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyy-MM-dd");
        String dateStr = sdf.format(date);
        return dateStr;
    }
    /**
     * 对指定的date对象进行格式化：HH:mm:ss
     * @param date
     * @return
     */
    public static String formateTime(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        String dateStr = sdf.format(date);
        return dateStr;
    }
}
