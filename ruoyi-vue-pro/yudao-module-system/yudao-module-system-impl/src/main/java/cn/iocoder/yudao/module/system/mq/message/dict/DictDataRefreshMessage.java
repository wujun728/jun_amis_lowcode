package cn.iocoder.yudao.module.system.mq.message.dict;

import cn.iocoder.yudao.framework.mq.core.pubsub.AbstractChannelMessage;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 字典数据数据刷新 Message
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class DictDataRefreshMessage extends AbstractChannelMessage {

    @Override
    public String getChannel() {
        return "system.dict-data.refresh";
    }

}
