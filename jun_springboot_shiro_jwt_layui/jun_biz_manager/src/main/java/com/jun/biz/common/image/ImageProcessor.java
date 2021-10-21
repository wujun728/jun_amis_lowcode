package com.jun.biz.common.image;

import org.im4java.core.IM4JavaException;

import java.io.IOException;

/**
 * Created on 2018/9/4 10:21
 * <p>
 * Description: []
 * <p>
 * Company: []
 *
 * 
 */
public interface ImageProcessor {

    /**
     * 批量图片处理
     * @return
     */
    BatchConverter batch();


    /**
     * 获取图片信息
     * IDENTIFY -format "%w,%h,%m,%b,%r"   path
     *
     * @param imagePath 图片路径
     * @return
     */
    ImageInfo identify(String imagePath);


    /**
     * 给图片加水印
     * composite -watermark  opacity  -gravity   position  waterFilePath   iFileName   iFileName
     *
     * @param srcFilename   源图片路径
     * @param destFilename  处理后图片路径
     * @param waterFilePath 水印图历经
     * @param opacity       透明度
     * @param position      水印位置
     */
    boolean waterMarker(String srcFilename, String destFilename, String waterFilePath, int opacity, String position);


    /**
     * 按宽高缩放图片
     *
     * @param inFilePath
     * @param outFilePath
     * @param width
     * @param height
     * @param fillWhite   是否补白背景，不补白则拉伸
     */
    boolean resize(String inFilePath, String outFilePath, Integer width, Integer height, boolean fillWhite);

    /**
     * 按比例缩放图片
     *
     * @param inFilePath
     * @param outFilePath
     * @param width
     * @param height
     * @return
     */
    boolean resizeByScale(String inFilePath, String outFilePath, Integer width, Integer height);


    /**
     * 图片剪裁
     *
     * @param inFilePath
     * @param outFilePath
     * @param x
     * @param y
     * @param width
     * @param height
     */
    boolean crop(String inFilePath, String outFilePath, int x, int y, int width, int height);


    /**
     * 压缩图片
     *
     * @param srcFilename
     * @param quality
     * @param destFilename
     * @throws InterruptedException
     * @throws IOException
     * @throws IM4JavaException
     */
    boolean compress(String srcFilename, Double quality, String destFilename);

    /**
     * 旋转
     *
     * @param srcFilename
     * @param angle
     * @param destFilename CONVERT -rotate 90 input.jpg output.jpg
     */
    boolean rotate(String srcFilename, double angle, String destFilename);


    /**
     * 高斯模糊
     *
     * @param srcFilename
     * @param scale
     * @param destFilename CONVERT -blur 80 foo.jpg foo.png
     *                     -blur参数还可以这样-blur 80x5。后面的那个5表示的是Sigma的值
     */
    boolean blur(String srcFilename, double scale, String destFilename);

    /**
     * 格式转换
     *
     * @param srcFilename
     * @param destFilename
     */
    boolean convert(String srcFilename, String destFilename);

    /**
     * 上下翻转
     *
     * @param srcFilename
     * @param destFilename CONVERT -flip foo.png bar.png
     */
    boolean flip(String srcFilename, String destFilename);

    /**
     * 左右翻转
     *
     * @param srcFilename
     * @param destFilename CONVERT -flop  foo.png bar.png
     */
    boolean flop(String srcFilename, String destFilename);


}
