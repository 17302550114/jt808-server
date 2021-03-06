package org.yzh.framework.orm.fields;

import io.netty.buffer.ByteBuf;
import org.yzh.framework.orm.annotation.Field;

import java.beans.PropertyDescriptor;

public class FieldLong32 extends BasicField<Long> {

    public FieldLong32(Field field, PropertyDescriptor property) {
        super(field, property);
    }

    @Override
    public Long readValue(ByteBuf input, int length) {
        return input.readUnsignedInt();
    }

    @Override
    public void writeValue(ByteBuf output, Long value) {
        output.writeInt(value.intValue());
    }
}