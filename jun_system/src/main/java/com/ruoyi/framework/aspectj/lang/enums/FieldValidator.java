package com.ruoyi.framework.aspectj.lang.enums;

public enum FieldValidator {

    NOT_NULL(0), //元素值不为null
    NOT_EMPTY(1), //元素值不为null且不为空（字符串长度不为0、集合大小不为0）
    NOT_BLANK(2), //元素值为空白字符（null；不可见字符，如空格；空字符串""）
    RANGE(3), //元素值必须在合适的范围内
    LENGTH(4), //元素值的字符串长度必须在指定的范围内
    DATE(5), //元素值必须是指定日期格式
    EMAIL(6), //元素值必须是邮箱格式
    PATTERN(7); //元素值必须符合指定的正则表达式

    private final int value;

    FieldValidator(int value) {
        this.value = value;
    }

    public int value() {
        return this.value;
    }
}