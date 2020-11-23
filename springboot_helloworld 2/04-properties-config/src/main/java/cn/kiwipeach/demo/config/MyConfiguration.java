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
package cn.kiwipeach.demo.config;

import cn.kiwipeach.demo.bean.GraduateStudent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Date;

/**
 * 我的bean配置类
 *
 * @author Wujun
 * @create 2018/07/04
 */
@Configuration
public class MyConfiguration {

    @Bean
    public GraduateStudent gernateStudent(){
        GraduateStudent student = new GraduateStudent();
        student.setId(86653);
        student.setName("研究生");
        student.setBirthday(new Date());
        student.setWeight(569.8);
        return student;
    }

}
