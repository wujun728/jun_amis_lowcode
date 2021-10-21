package com.jun.biz.common.image.impl;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.im4java.core.*;
import org.im4java.process.ArrayListOutputConsumer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.jun.biz.common.image.*;

import static com.jun.biz.common.utils.StringUtil.findByRegex;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created on 2018/9/6 15:48
 * <p>
 * Description: []
 * <p>
 * Company: []
 *
 * 
 */

@Component
@Slf4j
public class ImageProcessorImpl implements ImageProcessor {

    @Value("${image.processor.path:}")
    private String gmPath;


    /**
     * 获取 ImageCommand
     *
     * @param command 命令类型
     * @return
     */
    private ImageCommand getImageCommand(CommandType command) {
        ImageCommand cmd;
        switch (command) {
            case CONVERT:
                cmd = new ConvertCmd(true);
                break;
            case IDENTIFY:
                cmd = new IdentifyCmd(true);
                break;
            case COMPOSITE:
                cmd = new CompositeCmd(true);
                break;
            default:
                throw new ImageException("not surpport commandType! cmd=" + command);
        }
        if (StringUtils.isNotBlank(gmPath)) {
            cmd.setSearchPath(gmPath);
        }
        return cmd;
    }

    @Override
    public BatchConverter batch() {
        return new BatchConverter(getImageCommand(CommandType.CONVERT));
    }

    /**
     * 获取图片信息
     * IDENTIFY -format "%w,%h,%m,%b,%r"   path
     *
     * @param imagePath 图片路径
     * @return 图片信息
     */
    @Override
    public ImageInfo identify(String imagePath) {
        IMOperation op = new IMOperation();
        op.format("%m,%w,%h,%d/%f,%Q,%b\r");
        op.addImage(imagePath);
        ImageCommand identifyCmd = getImageCommand(CommandType.IDENTIFY);
        ArrayListOutputConsumer output = new ArrayListOutputConsumer();
        identifyCmd.setOutputConsumer(output);
        cmdRun(identifyCmd, op);
        ArrayList<String> cmdOutput = output.getOutput();
        String[] result = cmdOutput.get(0).split(",");
        ImageInfo imageInfo = new ImageInfo();
        if (result.length >= 6) {
            int i = 0;
            imageInfo.setExtension(result[i++]);
            imageInfo.setWidth(Integer.valueOf(result[i++]));
            imageInfo.setHeight(Integer.valueOf(result[i++]));
            imageInfo.setPath(result[i++]);
            imageInfo.setQuality(result[i++]);
            imageInfo.setSize(toMillionSize(result[i]));

        }
        return imageInfo;
    }

    /**
     * 返回图片大小，单位M
     *
     * @param s
     * @return
     */
    private static Double toMillionSize(String s) {
        //没有单位代表b
        if (StringUtils.isNumeric(s)) {
            return Double.parseDouble(s) / 1024 / 1024;
        }
        String num = findByRegex(s, "([0-9,.]*)");
        if (num == null) {
            throw new ImageException("error to get image size,size=" + s);
        }
        String unit = s.replace(num, "").toLowerCase();
        double d = Double.parseDouble(num);
        switch (unit) {
            case "mi":
            case "mig":
                return d;
            case "ki":
            case "kig":
                return d / 1024;
            case "gi":
            case "gig":
                return d * 1024;
            default:
                throw new ImageException("error to get image size,size=" + s);
        }
    }

    /**
     * 给图片加水印
     * composite -watermark  opacity  -gravity   position  waterFilePath   iFileName   iFileName
     *
     * @param srcFilename   源图片路径
     * @param destFilename  处理后图片路径
     * @param waterFilePath 水印图历经
     * @param opacity       透明度
     * @param position      水印位置 .位置center：中心;northwest：左上;southeast：右下
     */
    @Override
    public boolean waterMarker(String srcFilename, String destFilename, String waterFilePath, int opacity, String position) {
        IMOperation op = new IMOperation();
        // 水印图片位置坐标用op.geometry
        op.gravity(position);
        // 水印透明度
        op.dissolve(opacity);
        // 水印
        op.addImage(waterFilePath);
        // 原图
        op.addImage(srcFilename);
        // 目标
        op.addImage(destFilename);
        ImageCommand cmd = getImageCommand(CommandType.COMPOSITE);
        return cmdRun(cmd, op);

    }


    private boolean cmdRun(ImageCommand cmd, IMOperation op, Object... args) {
        try {
            log.info("图片处理！cmd:{} , op:{} , args:{}", cmd.getCommand(), op, Arrays.toString(args));
            cmd.run(op, args);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new ImageException(e);
        } catch (IOException | IM4JavaException e) {
            throw new ImageException(e);
        }
        return true;

    }

    /**
     * 按宽高比例缩放图片，不足则补白背景
     *
     * @param inFilePath  源图
     * @param outFilePath 结果
     * @param width       缩略宽度
     * @param height      缩略高度
     */
    @Override
    public boolean resize(String inFilePath, String outFilePath, Integer width, Integer height, boolean fillWhite) {
        IMOperation op = new IMOperation();
        op.addImage(inFilePath);
        if (!fillWhite) {
            op.resize(width, height, "!");
        } else {
            op.resize(width, height);
            op.background("white");
            op.gravity("center");
            op.extent(width, height);
        }
        op.addImage(outFilePath);
        ImageCommand cmd = getImageCommand(CommandType.CONVERT);
        return cmdRun(cmd, op);

    }

    @Override
    public boolean resizeByScale(String inFilePath, String outFilePath, Integer width, Integer height) {
        IMOperation op = new IMOperation();
        op.addImage(inFilePath);
        op.resize(width, height, "%");
        op.addImage(outFilePath);
        ImageCommand cmd = getImageCommand(CommandType.CONVERT);
        return cmdRun(cmd, op);
    }


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
    @Override
    public boolean crop(String inFilePath, String outFilePath, int x, int y, int width, int height) {
        IMOperation op = new IMOperation();
        op.addImage(inFilePath);
        /** width：裁剪的宽度 * height：裁剪的高度 * x：裁剪的横坐标 * y：裁剪纵坐标 */
        op.crop(width, height, x, y);
        op.addImage(outFilePath);
        ImageCommand cmd = getImageCommand(CommandType.CONVERT);
        return cmdRun(cmd, op);
    }

    /**
     * 压缩图片
     *
     * @param srcFilename
     * @param destFilename
     */
    @Override
    public boolean compress(String srcFilename, Double quality, String destFilename) {
        IMOperation op = new IMOperation();
        op.strip();
        op.quality(quality);
        op.addImage(srcFilename);
        op.addImage(destFilename);
        ImageCommand cmd = getImageCommand(CommandType.CONVERT);
        return cmdRun(cmd, op);
    }

    /**
     * 旋转
     *
     * @param srcFilename
     * @param angle
     * @param destFilename
     */
    @Override
    public boolean rotate(String srcFilename, double angle, String destFilename) {
        IMOperation op = new IMOperation();
        op.rotate(angle % 360);
        op.addImage(srcFilename);
        op.addImage(destFilename);
        ImageCommand cmd = getImageCommand(CommandType.CONVERT);
        return cmdRun(cmd, op);
    }

    /**
     * 高斯模糊
     *
     * @param srcFilename
     * @param scale
     * @param destFilename CONVERT -blur 80 foo.jpg foo.png
     */
    @Override
    public boolean blur(String srcFilename, double scale, String destFilename) {
        IMOperation op = new IMOperation();
        op.blur(scale, 3.0);
        op.addImage(srcFilename);
        op.addImage(destFilename);
        ImageCommand cmd = getImageCommand(CommandType.CONVERT);
        return cmdRun(cmd, op);

    }

    /**
     * 格式转换
     *
     * @param srcFilename
     * @param destFilename
     */
    @Override
    public boolean convert(String srcFilename, String destFilename) {
        IMOperation op = new IMOperation();
        op.addImage(srcFilename);
        op.addImage(destFilename);
        ImageCommand cmd = getImageCommand(CommandType.CONVERT);
        return cmdRun(cmd, op);

    }

    /**
     * 上下翻转
     *
     * @param srcFilename
     * @param destFilename
     */
    @Override
    public boolean flip(String srcFilename, String destFilename) {
        IMOperation op = new IMOperation();
        op.flip();
        op.addImage(srcFilename);
        op.addImage(destFilename);
        ImageCommand cmd = getImageCommand(CommandType.CONVERT);
        return cmdRun(cmd, op);
    }

    /**
     * 左右翻转
     *
     * @param srcFilename
     * @param destFilename
     */
    @Override
    public boolean flop(String srcFilename, String destFilename) {
        IMOperation op = new IMOperation();
        op.flop();
        op.addImage(srcFilename);
        op.addImage(destFilename);
        ImageCommand cmd = getImageCommand(CommandType.CONVERT);
        return cmdRun(cmd, op);
    }

    public static void main(String[] args) {
        System.out.println(toMillionSize("36.5KiG"));
    }
}
