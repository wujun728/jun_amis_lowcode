package com.qingshop.mall;

import java.awt.Color;

public class ImageTextContent {

	public ImageTextContent() {

	}

	public ImageTextContent(String fontType, int fontStyle, Color contentColor, int fontSize, String textContent,
			int originX, int originY, String position, int lineWidth) {
		this.fontType = fontType;
		this.fontStyle = fontStyle;
		this.contentColor = contentColor;
		this.fontSize = fontSize;
		this.textContent = textContent;
		this.originX = originX;
		this.originY = originY;
		this.position = position;
		this.lineWidth = lineWidth;
	}

	/**
	 * 水印文字的字体
	 */
	private String fontType;

	/**
	 * 水印文字的字体份风格
	 */
	private int fontStyle;

	/**
	 * 水印文字的颜色
	 */
	private Color contentColor;

	/**
	 * 文字的大小
	 */
	private int fontSize;

	/**
	 * 文字内容
	 */
	private String textContent;

	/**
	 * 相对与X的起始的位置
	 */
	private int originX = 0;

	/**
	 * 相对与Y的起始的位置
	 */
	private int originY = 0;

	/**
	 * 文字内容的排列方式 值 ： “center”水平垂直居中 ，“centerX”水平居中垂直自定义Y ，“centerY”垂直居中垂直自定义X
	 * ，“centerN”自定义XY
	 */
	private String position;

	/**
	 * 文字行宽
	 */
	private int lineWidth;

	public String getFontType() {
		return fontType;
	}

	public void setFontType(String fontType) {
		this.fontType = fontType;
	}

	public int getFontStyle() {
		return fontStyle;
	}

	public void setFontStyle(int fontStyle) {
		this.fontStyle = fontStyle;
	}

	public Color getContentColor() {
		return contentColor;
	}

	public void setContentColor(Color contentColor) {
		this.contentColor = contentColor;
	}

	public int getFontSize() {
		return fontSize;
	}

	public void setFontSize(int fontSize) {
		this.fontSize = fontSize;
	}

	public String getTextContent() {
		return textContent;
	}

	public void setTextContent(String textContent) {
		this.textContent = textContent;
	}

	public int getOriginX() {
		return originX;
	}

	public void setOriginX(int originX) {
		this.originX = originX;
	}

	public int getOriginY() {
		return originY;
	}

	public void setOriginY(int originY) {
		this.originY = originY;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public int getLineWidth() {
		return lineWidth;
	}

	public void setLineWidth(int lineWidth) {
		this.lineWidth = lineWidth;
	}

}
