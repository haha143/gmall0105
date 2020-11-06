package com.auguigu.gmall.bean;

public enum RestResultStatus {
    SUCCESS("Y", "成功"),
    FAIL("N", "失败"),
    NOT_FUND("404","信息未找到");

//    private static final Map<String, RestResultStatus> map;
//    static {
//        map = new HashMap<>();
//        for (RestResultStatus item : RestResultStatus.values()) {
//            map.put(item.code, item);
//        }
//    }
    private final String code;
    private final String text;

    RestResultStatus(String code, String text) {
        this.code = code;
        this.text = text;
    }

    public String getCode() {
        return code;
    }

    public String getText() {
        return text;
    }

//    public static RestResultStatus of(String code) {
//        return map.get(code);
//    }
//
//    public static boolean contain(String code) {
//        return map.containsKey(code);
//    }

    public static RestResultStatus forValue(String value) {
        if ("Y".equals(value)) return SUCCESS;
        else return FAIL;
    }
}
