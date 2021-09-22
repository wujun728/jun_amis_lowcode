package cn.kunter.generator.config;

import lombok.Getter;

import java.util.Properties;

/**
 * Property Holder
 * @author nature
 * @version 1.0 2021/7/20
 */
public abstract class PropertyHolder {

    @Getter
    private final Properties properties;

    protected PropertyHolder() {
        super();
        properties = new Properties();
    }

    public void addProperty(String name, String value) {
        properties.setProperty(name, value);
    }

    public String getProperty(String name) {
        return properties.getProperty(name);
    }

}
