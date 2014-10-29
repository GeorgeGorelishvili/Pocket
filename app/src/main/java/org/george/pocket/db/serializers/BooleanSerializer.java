package org.george.pocket.db.serializers;

import com.activeandroid.serializer.TypeSerializer;

final public class BooleanSerializer extends TypeSerializer {

    @Override
    public Class<?> getDeserializedType() {
        return Boolean.class;
    }

    @Override
    public Class<?> getSerializedType() {
        return Long.class;
    }

    @Override
    public Long serialize(Object data) {
        return data != null && (Boolean)data ? 1l : 0l;
    }

    @Override
    public Boolean deserialize(Object data) {
        return data != null && (Long)data != 0;
    }
}
