package org.george.pocket.db.common;

import com.activeandroid.serializer.TypeSerializer;

import java.util.Date;

final public class DateSerializer extends TypeSerializer {
     @Override
     public Class<?> getDeserializedType() {
         return Date.class;
     }

     @Override
     public Class<?> getSerializedType() {
         return Long.class;
     }

     @Override
     public Long serialize(Object data) {
         if (data == null) {
             return null;
         }

         return ((Date) data).getTime();
     }

     @Override
     public Date deserialize(Object data) {
         if (data == null) {
             return null;
         }

         return new Date((Long) data);
     }
 }