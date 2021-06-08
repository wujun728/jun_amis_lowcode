package com.qingshop.mall;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

public class MyTest {

	/**
	 * 图片添加水印
	 *
	 * @param srcImgPath       需要添加水印的图片的路径
	 * @param outImgPath       添加水印后图片输出路径
	 * @param fontType         水印文字的字体
	 * @param fontStyle        水印文字的字体份风格
	 * @param markContentColor 水印文字的颜色
	 * @param fontSize         水印的文字的大小
	 * @param waterMarkContent 水印的文字内容
	 */
	public void waterPress(String srcImgPath, String outImgPath, List<ImageTextContent> testContentList) {
		try {
			// 读取原图片信息
			File srcImgFile = new File(srcImgPath);
			Image srcImg = null;
			if (srcImgFile.exists() && srcImgFile.isFile() && srcImgFile.canRead()) {
				srcImg = ImageIO.read(srcImgFile);
			}
			// 宽、高
			int srcImgWidth = srcImg.getWidth(null);
			int srcImgHeight = srcImg.getHeight(null);
			// 加水印
			BufferedImage bufImg = new BufferedImage(srcImgWidth, srcImgHeight, BufferedImage.TYPE_INT_RGB);
			Graphics2D g = bufImg.createGraphics();
			g.drawImage(srcImg, 0, 0, srcImgWidth, srcImgHeight, null);
			for (ImageTextContent imageTextContent : testContentList) {
				// 水平垂直居中 originX、originY均无效
				if ("center".equals(imageTextContent.getPosition())) {
					String fontType = imageTextContent.getFontType();
					int fontStyle = imageTextContent.getFontStyle();
					int fontSize = imageTextContent.getFontSize();
					Color contentColor = imageTextContent.getContentColor();
					String textContent = imageTextContent.getTextContent();
					int lineWidth = imageTextContent.getLineWidth();
					// 设置设置字体颜色样式
					Font font = new Font(fontType, fontStyle, fontSize);
					g.setColor(contentColor);
					g.setFont(font);
					g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
					int fontLength = getWatermarkLength(textContent, g);
					// 实际生成的水印文字，实际文字行数
					Double textLineCount = Math
							.ceil(Integer.valueOf(fontLength).doubleValue() / Integer.valueOf(lineWidth).doubleValue());
					// 实际所有的水印文字的高度
					int textHeight = textLineCount.intValue() * fontSize;
					// 相对与X的起始的位置
					int originX = 0;
					// 相对与Y的起始的位置
					int originY = 0;
					// 实际文字大于1行，则x则为默认起始0，
					if (1 == textLineCount.intValue()) {
						// 实际文字行数是1，1/2个图片高度，减去1/2个字符高度
						originY = srcImgHeight / 2 - fontSize / 2;
						// 实际文字行数是1，计算x的居中的起始位置
						originX = (srcImgWidth - fontLength) / 2;
					} else {
						// 实际文字行数大于1，1/2个图片高度减去文字行数所需的高度
						originY = (srcImgHeight - textHeight) / 2;
					}
					// 文字叠加,自动换行叠加
					int tempX = originX;
					int tempY = originY;
					int tempCharLen = 0;// 单字符长度
					int tempLineLen = 0;// 单行字符总长度临时计算
					StringBuffer stringBuffer = new StringBuffer();
					for (int i = 0; i < textContent.length(); i++) {
						char tempChar = textContent.charAt(i);
						tempCharLen = getCharLen(tempChar, g);
						if (tempLineLen >= lineWidth) {
							// 绘制前一行
							g.drawString(stringBuffer.toString(), tempX, tempY);
							// 清空内容,重新追加
							stringBuffer.delete(0, stringBuffer.length());
							// 文字长度已经满一行,Y的位置加1字符高度
							tempY = tempY + fontSize;
							tempLineLen = 0;
						}
						// 追加字符
						stringBuffer.append(tempChar);
						tempLineLen += tempCharLen;
					}
					// 最后叠加余下的文字
					g.drawString(stringBuffer.toString(), tempX, tempY);
				}

				// 水平居中Y轴固定值 originX无效、originY有效
				if ("centerX".equals(imageTextContent.getPosition())) {
					String fontType = imageTextContent.getFontType();
					int fontStyle = imageTextContent.getFontStyle();
					int fontSize = imageTextContent.getFontSize();
					Color contentColor = imageTextContent.getContentColor();
					String textContent = imageTextContent.getTextContent();
					int originY = imageTextContent.getOriginY();
					int lineWidth = imageTextContent.getLineWidth();
					// 设置设置字体颜色样式
					Font font = new Font(fontType, fontStyle, fontSize);
					g.setColor(contentColor);
					g.setFont(font);
					g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
					int fontLength = getWatermarkLength(textContent, g);
					// 实际生成的水印文字，实际文字行数
					Double textLineCount = Math
							.ceil(Integer.valueOf(fontLength).doubleValue() / Integer.valueOf(lineWidth).doubleValue());
					// 相对与X的起始的位置
					int originX = 0;
					// 实际文字大于1行，则x则为默认起始0，
					if (1 == textLineCount.intValue()) {
						// 实际文字行数是1，计算x的居中的起始位置
						originX = (srcImgWidth - fontLength) / 2;
					}
					// 文字叠加,自动换行叠加
					int tempX = originX;
					int tempY = originY;
					int tempCharLen = 0;// 单字符长度
					int tempLineLen = 0;// 单行字符总长度临时计算
					StringBuffer stringBuffer = new StringBuffer();
					for (int i = 0; i < textContent.length(); i++) {
						char tempChar = textContent.charAt(i);
						tempCharLen = getCharLen(tempChar, g);
						if (tempLineLen >= lineWidth) {
							// 绘制前一行
							g.drawString(stringBuffer.toString(), tempX, tempY);
							// 清空内容,重新追加
							stringBuffer.delete(0, stringBuffer.length());
							// 文字长度已经满一行,Y的位置加1字符高度
							tempY = tempY + fontSize;
							tempLineLen = 0;
						}
						// 追加字符
						stringBuffer.append(tempChar);
						tempLineLen += tempCharLen;
					}
					// 最后叠加余下的文字
					g.drawString(stringBuffer.toString(), tempX, tempY);
				}

				// 水平居中Y轴固定值 originX有效、originY无效
				if ("centerY".equals(imageTextContent.getPosition())) {
					String fontType = imageTextContent.getFontType();
					int fontStyle = imageTextContent.getFontStyle();
					int fontSize = imageTextContent.getFontSize();
					Color contentColor = imageTextContent.getContentColor();
					String textContent = imageTextContent.getTextContent();
					int originX = imageTextContent.getOriginX();
					int lineWidth = imageTextContent.getLineWidth();
					// 设置设置字体颜色样式
					Font font = new Font(fontType, fontStyle, fontSize);
					g.setColor(contentColor);
					g.setFont(font);
					g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
					int fontLength = getWatermarkLength(textContent, g);
					// 实际生成的水印文字，实际文字行数
					Double textLineCount = Math
							.ceil(Integer.valueOf(fontLength).doubleValue() / Integer.valueOf(lineWidth).doubleValue());
					// 实际所有的水印文字的高度
					int textHeight = textLineCount.intValue() * fontSize;
					// 相对与Y的起始的位置
					int originY = 0;
					// 实际文字大于1行，则x则为默认起始0，
					if (1 == textLineCount.intValue()) {
						// 实际文字行数是1，1/2个图片高度，减去1/2个字符高度
						originY = srcImgHeight / 2 - fontSize / 2;
					} else {
						// 实际文字行数大于1，1/2个图片高度减去文字行数所需的高度
						originY = (srcImgHeight - textHeight) / 2;
					}
					// 文字叠加,自动换行叠加
					int tempX = originX;
					int tempY = originY;
					int tempCharLen = 0;// 单字符长度
					int tempLineLen = 0;// 单行字符总长度临时计算
					StringBuffer stringBuffer = new StringBuffer();
					for (int i = 0; i < textContent.length(); i++) {
						char tempChar = textContent.charAt(i);
						tempCharLen = getCharLen(tempChar, g);
						if (tempLineLen >= lineWidth) {
							// 绘制前一行
							g.drawString(stringBuffer.toString(), tempX, tempY);
							// 清空内容,重新追加
							stringBuffer.delete(0, stringBuffer.length());
							// 文字长度已经满一行,Y的位置加1字符高度
							tempY = tempY + fontSize;
							tempLineLen = 0;
						}
						// 追加字符
						stringBuffer.append(tempChar);
						tempLineLen += tempCharLen;
					}
					// 最后叠加余下的文字
					g.drawString(stringBuffer.toString(), tempX, tempY);
				}

				// 水平垂直居中 originX有效、originY有效
				if ("centerN".equals(imageTextContent.getPosition())) {
					String fontType = imageTextContent.getFontType();
					int fontStyle = imageTextContent.getFontStyle();
					int fontSize = imageTextContent.getFontSize();
					Color contentColor = imageTextContent.getContentColor();
					String textContent = imageTextContent.getTextContent();
					int originX = imageTextContent.getOriginX();
					int originY = imageTextContent.getOriginY();
					int lineWidth = imageTextContent.getLineWidth();
					// 设置设置字体颜色样式
					Font font = new Font(fontType, fontStyle, fontSize);
					g.setColor(contentColor);
					g.setFont(font);
					g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
					// 文字叠加,自动换行叠加
					int tempX = originX;
					int tempY = originY;
					int tempCharLen = 0;// 单字符长度
					int tempLineLen = 0;// 单行字符总长度临时计算
					boolean isFirstLine = true; // 首航缩进两个字符
					int tempLineWidth = lineWidth;
					StringBuffer stringBuffer = new StringBuffer();
					for (int i = 0; i < textContent.length(); i++) {
						char tempChar = textContent.charAt(i);
						tempCharLen = getCharLen(tempChar, g);
						if (isFirstLine) {
							if (tempLineLen >= tempLineWidth - tempCharLen) {
								// 绘制前一行
								g.drawString(stringBuffer.toString(), tempX + tempCharLen, tempY);
								// 清空内容,重新追加
								stringBuffer.delete(0, stringBuffer.length());
								// 文字长度已经满一行,Y的位置加1字符高度
								tempY = tempY + fontSize;
								tempLineLen = 0;
								isFirstLine = false;
							}
						} else {
							if (tempLineLen >= lineWidth) {
								// 绘制前一行
								g.drawString(stringBuffer.toString(), tempX, tempY);
								// 清空内容,重新追加
								stringBuffer.delete(0, stringBuffer.length());
								// 文字长度已经满一行,Y的位置加1字符高度
								tempY = tempY + fontSize;
								tempLineLen = 0;
							}
						}
						// 追加字符
						stringBuffer.append(tempChar);
						tempLineLen += tempCharLen;
					}
					// 最后叠加余下的文字
					g.drawString(stringBuffer.toString(), tempX, tempY);
				}
			}
			g.dispose();
			// 输出图片
			FileOutputStream outImgStream = new FileOutputStream(outImgPath);
			ImageIO.write(bufImg, "jpg", outImgStream);
			outImgStream.flush();
			outImgStream.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public int getCharLen(char c, Graphics2D g) {
		return g.getFontMetrics(g.getFont()).charWidth(c);
	}

	/**
	 * 获取水印文字总长度
	 *
	 * @paramwaterMarkContent水印的文字
	 * @paramg
	 * @return水印文字总长度
	 */
	public int getWatermarkLength(String waterMarkContent, Graphics2D g) {
		return g.getFontMetrics(g.getFont()).charsWidth(waterMarkContent.toCharArray(), 0, waterMarkContent.length());
	}

	public static void main(String[] args) {
		List<ImageTextContent> list = new ArrayList<ImageTextContent>();
		ImageTextContent text1 = new ImageTextContent("宋体", Font.BOLD, Color.black, 35, "张三丰", 0, 500, "centerX", 400);
		String desc = "于2021年01月01日参加“企赢培训学校”举办的(股票管理大全XXXXXXXXXX)培训, 成绩合格, 恭喜你通过认证.";
		ImageTextContent text2 = new ImageTextContent("宋体", Font.PLAIN, Color.black, 25, desc, 110, 580, "centerN", 400);
		list.add(text1);
		list.add(text2);
		new MyTest().waterPress("D:1\\1.jpg", "D:1\\2.jpg", list);
	}

}
