		package com.jun.biz.manager.vo.role;



import lombok.Data;

import java.util.Date;






        /**
 * 
 */

@Data
public class RoleVO  {


    /**
     * id       db_column: id
     */
	private Long id;
    /**
     * 名称       db_column: name
     */
private String name;
            /**
             * 描述       db_column: description
             */
            private String description;

            /**
             * 创建时间       db_column: create_time
             */
            private Date createTime;

            private String createAdmin;


        }






