package cn.iocoder.yudao.framework.security.config;

import cn.hutool.core.util.StrUtil;
import cn.iocoder.yudao.framework.security.core.authentication.MultiUserDetailsAuthenticationProvider;
import cn.iocoder.yudao.framework.security.core.filter.JWTAuthenticationTokenFilter;
import cn.iocoder.yudao.framework.web.config.WebProperties;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import javax.annotation.Resource;
import java.util.List;

/**
 * 自定义的 Spring Security 配置适配器实现
 *
 * @author 芋道源码
 */
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class YudaoWebSecurityConfigurerAdapter extends WebSecurityConfigurerAdapter {

    @Resource
    private WebProperties webProperties;

    @Resource
    private MultiUserDetailsAuthenticationProvider authenticationProvider;
    /**
     * 认证失败处理类 Bean
     */
    @Resource
    private AuthenticationEntryPoint authenticationEntryPoint;
    /**
     * 权限不够处理器 Bean
     */
    @Resource
    private AccessDeniedHandler accessDeniedHandler;
    /**
     * 退出处理类 Bean
     */
    @Resource
    private LogoutSuccessHandler logoutSuccessHandler;
    /**
     * Token 认证过滤器 Bean
     */
    @Resource
    private JWTAuthenticationTokenFilter authenticationTokenFilter;

    /**
     * 自定义的权限映射 Bean 们
     *
     * @see #configure(HttpSecurity)
     */
    @Resource
    private List<AuthorizeRequestsCustomizer> authorizeRequestsCustomizers;

    /**
     * 由于 Spring Security 创建 AuthenticationManager 对象时，没声明 @Bean 注解，导致无法被注入
     * 通过覆写父类的该方法，添加 @Bean 注解，解决该问题
     */
    @Override
    @Bean
    @ConditionalOnMissingBean(AuthenticationManager.class)
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    /**
     * 身份认证接口
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider);
    }

    /**
     * 配置 URL 的安全配置
     *
     * anyRequest          |   匹配所有请求路径
     * access              |   SpringEl表达式结果为true时可以访问
     * anonymous           |   匿名可以访问
     * denyAll             |   用户不能访问
     * fullyAuthenticated  |   用户完全认证可以访问（非remember-me下自动登录）
     * hasAnyAuthority     |   如果有参数，参数表示权限，则其中任何一个权限可以访问
     * hasAnyRole          |   如果有参数，参数表示角色，则其中任何一个角色可以访问
     * hasAuthority        |   如果有参数，参数表示权限，则其权限可以访问
     * hasIpAddress        |   如果有参数，参数表示IP地址，如果用户IP和参数匹配，则可以访问
     * hasRole             |   如果有参数，参数表示角色，则其角色可以访问
     * permitAll           |   用户可以任意访问
     * rememberMe          |   允许通过remember-me登录的用户访问
     * authenticated       |   用户登录后可访问
     */
    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        // 登出
        httpSecurity
                // 开启跨域
                .cors().and()
                // CSRF 禁用，因为不使用 Session
                .csrf().disable()
                // 基于 token 机制，所以不需要 Session
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                .headers().frameOptions().disable().and()
                // 一堆自定义的 Spring Security 处理器
                .exceptionHandling().authenticationEntryPoint(authenticationEntryPoint)
                    .accessDeniedHandler(accessDeniedHandler).and()
                // 登出地址的配置
                .logout().logoutSuccessHandler(logoutSuccessHandler).logoutRequestMatcher(request -> // 匹配多种用户类型的登出
                        StrUtil.equalsAny(request.getRequestURI(), buildAdminApi("/system/logout"),
                                                                   buildAppApi("/member/logout")));

        // 设置每个请求的权限
        httpSecurity
                // ①：全局共享规则
                .authorizeRequests()
                    // 静态资源，可匿名访问
                    .antMatchers(HttpMethod.GET, "/*.html", "/**/*.html", "/**/*.css", "/**/*.js").permitAll()
                    .antMatchers(HttpMethod.GET, "/admin-ui/**").permitAll()
                    // 设置 App API 无需认证
                    .antMatchers(buildAppApi("/**")).permitAll()
                // ②：每个项目的自定义规则
                .and().authorizeRequests(registry -> // 下面，循环设置自定义规则
                        authorizeRequestsCustomizers.forEach(customizer -> customizer.customize(registry)))
                // ③：兜底规则，必须认证
                .authorizeRequests()
                    .anyRequest().authenticated()
        ;

        // 添加 JWT Filter
        httpSecurity.addFilterBefore(authenticationTokenFilter, UsernamePasswordAuthenticationFilter.class);
    }

    private String buildAdminApi(String url) {
        return webProperties.getAdminApi().getPrefix() + url;
    }

    private String buildAppApi(String url) {
        return webProperties.getAppApi().getPrefix() + url;
    }

}
