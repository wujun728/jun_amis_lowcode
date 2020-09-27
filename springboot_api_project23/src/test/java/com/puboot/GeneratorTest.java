package com.puboot;

import com.puboot.service.SysGeneratorService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class GeneratorTest extends BaseTester {

    @Autowired
    private SysGeneratorService sysGeneratorService;

    @Test
    public void testGetFilePath() {
        sysGeneratorService.generatorCode(new String[]{"sys_menu"}, "sys");
    }
}
