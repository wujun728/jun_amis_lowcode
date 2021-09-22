package cn.kunter.generator.config;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Jdbc Config
 * @author nature
 * @version 1.0 2021/7/20
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class JdbcConfig extends PropertyHolder {

    private String driverClass;
    private String connectionURL;
    private String userId;
    private String password;

}
