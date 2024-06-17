springboot + groovy 进行动态编程 （二）

maple 枫

于 2020-07-06 14:21:31 发布

2853
 收藏 14
文章标签： groovy
版权
springboot + groovy 进行动态编程 （一）
 本篇文章是编码，上篇文章我们说到 A服务的同学需要做的是：

（1）在项目启动的时候，完成对calculate_rule表的读取，并构造 groovy 对应的xml格式

（2）把构造好的xml，交给spring容器去管理，这样A服务里就可以拿到calculateParse接口的具体实现

（3）当calculate_rule表中的计算代码有改动时，不需要重启A服务，只需要通过一个开关就可以refresh内存里的计算规则即可

那下面我们把他一一实现：

一、A服务里建表

CREATE TABLE `calculate_rule` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `interface_id` varchar(128) NOT NULL COMMENT 'interface_id',
  `bean_name` varchar(64) NOT NULL COMMENT 'bean_name',
  `calculate_rule` text NOT NULL COMMENT 'calculate_rule',
  `calculate_type` varchar(64) NOT NULL COMMENT 'reward',
  `status` varchar(16) NOT NULL DEFAULT 'ENABLE' COMMENT 'ENABLE/DISENABLE',
  `extend_info` varchar(4096) DEFAULT NULL,
  `created_time` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6),
  `modified_time` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6),
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='calculate rule';
二、A服务里项目启动时，读取该表，并组装成groovy对应xml的格式，交给spring容器管理

1、实现InitializingBean接口，重写afterPropertiesSet方法



2、查询 calculate_rule 表，拿到 interface_id、bean_name、calculate_rule 数据，并做对象转换



3、 把转换之后的数据放进本地缓存里，方便我们后续使用；并构造groovy格式的xml，加载bean到spring容器



 4、加载bean到spring容器



 5、新增接口calculateParse，接入方如果想接入的话，必须要实现这个接口，实现自己的业务逻辑



6、 对外暴露 calculate接口，接入方需要调该接口，我这里就以 controller 为例了



7、groovy解析引擎





8、DML

INSERT INTO `calculate_rule`(`id`, `interface_id`, `bean_name`, `calculate_rule`, `calculate_type`, `status`, `extend_info`, `created_time`, `modified_time`) VALUES (1, 'B.integration.A.calculate.reward', 'rewardCalculateParser', 'package com.example.demo.groovy.calculate.impl;\n\nimport com.example.demo.entity.request.CalculateRequest;\nimport com.example.demo.entity.response.CalculateResponse;\nimport com.example.demo.groovy.calculate.CalculateParser;\nimport org.apache.commons.lang3.StringUtils;\n\nimport java.math.BigDecimal;\nimport java.math.RoundingMode;\nimport java.util.HashMap;\nimport java.util.Map;\n\n/**\n * 计算推广奖金\n */\npublic class RewardCalculateParser implements CalculateParser {\n\n    @Override\n    public CalculateResponse parse(CalculateRequest request) {\n\n        Map<String, Object> extendInfo = request.getExtendInfo();\n\n        String interfaceId = request.getInterfaceId();\n\n        BigDecimal totalAmount = BigDecimal.ZERO;\n        if (StringUtils.isNotBlank((String) extendInfo.get(\"totalAmount\"))) {\n            totalAmount = new BigDecimal((String) extendInfo.get(\"totalAmount\"));\n        }\n\n        int refererNumber = 0;\n        if (StringUtils.isNotBlank((String) extendInfo.get(\"refererNumber\"))) {\n            refererNumber = Integer.parseInt((String) extendInfo.get(\"refererNumber\"));\n        }\n\n\n        System.out.println(\"进入奖金计算逻辑,总金额为：\" + totalAmount + \"，邀请人数为：\" + refererNumber);\n        \n        BigDecimal reward = totalAmount.multiply(new BigDecimal(String.valueOf(refererNumber)))\n                .divide(new BigDecimal(\"100\")).divide(new BigDecimal(\"365\"),4, RoundingMode.HALF_DOWN);\n        CalculateResponse response = new CalculateResponse();\n\n        response.setInterfaceId(interfaceId);\n        Map<String, Object> map = new HashMap<>();\n        map.put(\"reward\", reward);\n\n        response.setExtendInfo(map);\n\n        System.out.println(\"退出奖金计算逻辑,总奖金为：\" + reward);\n        return response;\n    }\n}', 'reward', 'ENABLE', NULL, '2020-07-06 09:27:58.279144', '2020-07-06 13:23:51.311882');
 9、到此为止，主要思路就是这样，启动项目，访问 http://localhost:8888/calculate 测试结果为：





10、为了防止我们以后修改了计算的业务逻辑之后需要重启服务，我们新增一个接口去refresh内存里的计算规则





11、测试refresh，启动工程，访问 http://localhost:8888/calculate 接口，看到计算结果为：



这个时候改变DB里的脚本，把奖金改为 9999，然后访问 http://localhost:8888/refresh 接口刷新；

再次访问 http://localhost:8888/calculate 接口，看到计算结果为：



可以发现我们想要修改计算规则的话，直接把修改之后的脚本放进DB里，然后执行refresh接口即可生效；

实际项目中会可能会有自动化或者平台化的产品来实现refresh功能，但是思路都是一样的。 

12、至此我们的代码完成了，完整代码在gitlab里  https://gitee.com/gane_maple/groovy
————————————————
版权声明：本文为CSDN博主「maple 枫」的原创文章，遵循CC 4.0 BY-SA版权协议，转载请附上原文出处链接及本声明。
原文链接：https://blog.csdn.net/qq_33101675/article/details/107156878