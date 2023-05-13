package crm.commons.utils;

import java.util.UUID;

public class UUIDUtils {
    /**
     * 获取uuid的值
     * @return
     */
    public static String getUUID(){
        return UUID.randomUUID().toString().replaceAll("-","");
    }
}
