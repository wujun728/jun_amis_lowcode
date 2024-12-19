package com.ruoyi.framework.aspectj.lang.enums;

/**
 * 业务操作类型
 * @author ruoyi
 */
public enum BusinessType {

	/**
	 * 其它
	 */
	OTHER(0),

	/**
	 * 查询
	 */
	QUERY(1),

	/**
	 * 保存
	 */
	SAVE(2),

	/**
	 * 新增
	 */
	INSERT(3),

	/**
	 * 修改
	 */
	UPDATE(4),

	/**
	 * 删除
	 */
	DELETE(5),

	/**
	 * 清空数据
	 */
	CLEAN(6),

	/**
	 * 导出
	 */
	EXPORT(7),

	/**
	 * 导入
	 */
	IMPORT(8),

	/**
	 * 授权
	 */
	GRANT(9),

	/**
	 * 强退
	 */
	FORCE(10),

	/**
	 * 生成代码
	 */
	GENCODE(11),

	/**
	 * 执行任务
	 */
	RUNJOB(12),

	/**
	 * 解锁账户
	 */
	UNLOCK(13);

    private final int value;

	BusinessType(int value) {
        this.value = value;
    }

    public int value() {
        return this.value;
    }
}