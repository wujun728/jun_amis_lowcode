package com.qingshop.mall.modules.oss;

public enum OssTypeEnum {

    LOCAL(0),
    QINIU(1),
    ALIYUN(2),
    QCLOUD(3);
    private int value;

    OssTypeEnum(int value) {
        this.value = value;
    }

    public static OssTypeEnum getStatus(Integer value) {
        if (null == value) {
            return null;
        }
        OssTypeEnum[] statusArr = OssTypeEnum.values();
        for (OssTypeEnum ossTypeEnum : statusArr) {
            if (ossTypeEnum.getValue() == value) {
                return ossTypeEnum;
            }
        }
        return null;
    }

    public int getValue() {
        return value;
    }
}
