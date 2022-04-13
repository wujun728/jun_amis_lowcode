package cn.iocoder.yudao.framework.tenant.core.web;

import cn.hutool.core.util.StrUtil;
import cn.iocoder.yudao.framework.tenant.core.context.TenantContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 多租户 Context Web 过滤器
 * 将请求 Header 中的 tenant-id 解析出来，添加到 {@link TenantContextHolder} 中，这样后续的 DB 等操作，可以获得到租户编号。
 *
 * @author 芋道源码
 */
public class TenantContextWebFilter extends OncePerRequestFilter {

    private static final String HEADER_TENANT_ID = "tenant-id";

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {
        // 设置
        String tenantId = request.getHeader(HEADER_TENANT_ID);
        if (StrUtil.isNotEmpty(tenantId)) {
            TenantContextHolder.setTenantId(Long.valueOf(tenantId));
        }
        try {
            chain.doFilter(request, response);
        } finally {
            // 清理
            TenantContextHolder.clear();
        }
    }

}
