/*
 * Copyright 2019 kiwipeach(1099501218@qq.com).
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
package cn.kiwipeach.demo.domain;

import lombok.*;

import java.io.Serializable;

/**
 * 系统用户
 *
 * @author Wujun
 * @create 2019-01-24
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class SysUser implements Serializable {
    /**
     * 用户编号（序列生成，可以观看会员注册先后）
     */
    private String id;

    /**
     * 登录账号(三方登陆账号)
     */
    private String userName;

    /**
     * 密码(本站注册密码，只有注册或者三方登陆完善过信息的用户才有)
     */
    private String password;


}
