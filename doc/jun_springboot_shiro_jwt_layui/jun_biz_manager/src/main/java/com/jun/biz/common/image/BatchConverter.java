package com.jun.biz.common.image;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.im4java.core.IM4JavaException;
import org.im4java.core.IMOperation;
import org.im4java.core.ImageCommand;

import java.io.IOException;

/**
 * Created on 2019/4/16 9:49
 * <p>
 * Description: [批量转换]
 * <p>
 * Company: []
 *
 * 
 */
@Data
@Slf4j
public
class BatchConverter {
    private IMOperation operation;
    private ImageCommand imageCommand;

    public BatchConverter(ImageCommand imageCommand) {
        this.operation = new IMOperation();
        this.imageCommand = imageCommand;
    }

    public BatchConverter resize(Integer width, Integer height, boolean fillWhite) {
        IMOperation op = new IMOperation();
        if (!fillWhite) {
            op.resize(width, height, "!");
        } else {
            op.resize(width, height);
            op.background("white");
            op.gravity("center");
            op.extent(width, height);
        }
        operation.addOperation(op);
        return this;
    }

    public BatchConverter resizeByScale(Integer width, Integer height) {
        IMOperation op = new IMOperation();
        op.resize(width, height, "%");
        operation.addOperation(op);
        return this;
    }


    public BatchConverter crop(int x, int y, int width, int height) {
        IMOperation op = new IMOperation();
        // width：裁剪的宽度 * height：裁剪的高度 * x：裁剪的横坐标 * y：裁剪纵坐标
        op.crop(width, height, x, y);
        operation.addOperation(op);
        return this;
    }

    public BatchConverter compress(Double quality) {
        IMOperation op = new IMOperation();
        op.strip();
        op.quality(quality);
        operation.addOperation(op);
        return this;
    }

    public BatchConverter rotate(double angle) {
        IMOperation op = new IMOperation();
        op.rotate(angle % 360);
        operation.addOperation(op);
        return this;
    }


    public BatchConverter blur(double scale) {
        IMOperation op = new IMOperation();
        op.blur(scale, 3.0);
        operation.addOperation(op);
        return this;

    }

    public BatchConverter flip() {
        IMOperation op = new IMOperation();
        op.flip();
        operation.addOperation(op);
        return this;
    }

    public BatchConverter flop() {
        IMOperation op = new IMOperation();
        op.flop();
        operation.addOperation(op);
        return this;
    }

    public boolean execute(String inImg, String outImg) {
        operation.addImage(inImg, outImg);
        try {
            log.info("图片批转换处理！cmd:{} , op:{} ", imageCommand.getCommand(), operation);
            imageCommand.run(operation);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new ImageException(e);
        } catch (IOException | IM4JavaException e) {
            throw new ImageException(e);
        }
        return true;
    }
}
