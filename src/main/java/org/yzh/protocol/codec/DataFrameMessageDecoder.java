package org.yzh.protocol.codec;

import io.netty.buffer.ByteBuf;
import org.yzh.framework.commons.transform.ByteBufUtils;
import org.yzh.framework.orm.BeanMetadata;
import org.yzh.framework.orm.MessageHelper;
import org.yzh.framework.session.Session;
import org.yzh.protocol.basics.JTMessage;

/**
 * 数据帧解码器
 * @author yezhihao
 * @home https://gitee.com/yezhihao/jt808-server
 */
public class DataFrameMessageDecoder extends JTMessageDecoder {

    private BeanMetadata<? extends JTMessage> dataFrameMetadata;
    private byte[] dataFramePrefix;

    public DataFrameMessageDecoder(String basePackage, Class<? extends JTMessage> dataFrameClass, byte[] dataFramePrefix) {
        super(basePackage);
        this.dataFrameMetadata = MessageHelper.getBeanMetadata(dataFrameClass, 0);
        this.dataFramePrefix = dataFramePrefix;
    }

    @Override
    public JTMessage decode(ByteBuf buf, Session session) {
        if (ByteBufUtils.startsWith(buf, dataFramePrefix))
            return dataFrameMetadata.decode(buf);
        return super.decode(buf, session);
    }
}