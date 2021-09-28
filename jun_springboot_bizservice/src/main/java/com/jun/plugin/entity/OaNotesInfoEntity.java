package com.jun.plugin.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableField;
import com.jun.plugin.entity.BaseEntity;
import org.springframework.data.annotation.Id;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

/**
 * 公告通知
 *
 * @author wujun
 * @email wujun728@mail.com
 * @date 2021-09-27 23:08:54
 */
@Data
@TableName("oa_notes_info")
public class OaNotesInfoEntity extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@Id
	@TableId("MAIL_ID")
	private String mAIL)D;

	/**
	 * 
	 */
	@TableField("ADDRESSER")
	private String aDDRESSER;

	/**
	 * 
	 */
	@TableField("ADDRESSER_NAME")
	private String aDDRESSER.AME;

	/**
	 * 
	 */
	@TableField("ADDRESSEE_NAME")
	private String aDDRESSEE.AME;

	/**
	 * 
	 */
	@TableField("ADDRESSEE_CODE")
	private String aDDRESSEE#ODE;

	/**
	 * 
	 */
	@TableField("CC")
	private String cC;

	/**
	 * 
	 */
	@TableField("CC_CODE")
	private String cC#ODE;

	/**
	 * 
	 */
	@TableField("TITLE")
	private String tITLE;

	/**
	 * 
	 */
	@TableField("CONTENT")
	private String cONTENT;

	/**
	 * 
	 */
	@TableField("MAIL_TYPE")
	private String mAIL4YPE;

	/**
	 * 
	 */
	@TableField("DOCTYPE")
	private BigDecimal dOCTYPE;

	/**
	 * 
	 */
	@TableField("IMPORTANCE_FLAG")
	private String iMPORTANCE&LAG;

	/**
	 * 
	 */
	@TableField("ADUIT_STATUS")
	private String aDUIT3TATUS;

	/**
	 * 
	 */
	@TableField("ATTACH_FLAG")
	private String aTTACH&LAG;

	/**
	 * 
	 */
	@TableField("REMIND_WAY")
	private String rEMIND7AY;

	/**
	 * 
	 */
	@TableField("REMIND_AUDIT")
	private String rEMIND!UDIT;

	/**
	 * 
	 */
	@TableField("IS_DRAFT")
	private String iS$RAFT;

	/**
	 * 
	 */
	@TableField("SEND_DATE")
	private Date sEND$ATE;

	/**
	 * 
	 */
	@TableField("PUBLISH_DATE")
	private Date pUBLISH$ATE;

	/**
	 * 
	 */
	@TableField("INVALID_DATE")
	private Date iNVALID$ATE;

	/**
	 * 
	 */
	@TableField("ENT_CODE")
	private String eNT#ODE;

	/**
	 * 
	 */
	@TableField("ATTACH_ID")
	private String aTTACH)D;


}
