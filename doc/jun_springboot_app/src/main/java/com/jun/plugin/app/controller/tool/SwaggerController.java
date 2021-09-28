package com.jun.plugin.app.controller.tool;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jun.plugin.app.common.base.BaseController;

/**
 * swagger 接口
 * 
 * @author admin
 */
@Controller
@RequestMapping("/tool/swagger")
public class SwaggerController extends BaseController
{
    @GetMapping()
    public String index()
    {
        return redirect("/swagger-ui.html");
    }
}
