/*
 * Copyright 2018 kiwipeach.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package cn.kiwipeach.demo.service.impl;

import cn.kiwipeach.demo.service.LogbackService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * 日志服务实现类
 *
 * @author kiwipeach [1099501218@qq.com]
 * @create 2018/07/05
 */
@Service
public class LogbackServiceImpl implements LogbackService {

    private Logger logger  = LoggerFactory.getLogger(getClass());

    @Override
    public void showLogLevel() {
        logger.trace("Service trace 日志");
        logger.debug("Service debug 日志");
        logger.info("Service info 日志");
        logger.warn("Service warn 日志");
        logger.error("Service error 日志");
    }
}
