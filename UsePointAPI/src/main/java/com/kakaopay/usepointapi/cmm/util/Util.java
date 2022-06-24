package com.kakaopay.usepointapi.cmm.util;

import java.util.Random;

public class Util {

    public static Long generateUUID(int n) {
        Random rd = new Random();//랜덤 객체 생성
        StringBuilder uuidStr = new StringBuilder();
        String uuid = null;

        // 9자리만 셋팅
        for (int i = 0; i < n; i++) {
            uuidStr.append(Integer.toString(rd.nextInt(9)));
        }
        uuid = uuidStr.toString();
        return Long.parseLong(uuid);
    }


}
