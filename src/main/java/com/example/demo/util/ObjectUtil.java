package com.example.demo.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * Description: 消息类
 */
public class ObjectUtil {

    /**对象转byte[]
     * @param obj
     * @return
     * @throws
     */
    public static byte[] objectToBytes(Object obj) throws Exception{
        byte[] bytes = null;
        ByteArrayOutputStream bo = new ByteArrayOutputStream();
        ObjectOutputStream oo = new ObjectOutputStream(bo);
        oo.writeObject(obj);
        bytes = bo.toByteArray();
        bo.close();
        oo.close();
        return bytes;
    }
    /**byte[]转对象
     * @param bytes
     * @return
     * @throws Exception
     */
    public static Object bytesToObject(byte[] bytes) throws Exception{
        Object value = null;
        ByteArrayInputStream in = new ByteArrayInputStream(bytes);
        ObjectInputStream sIn = new ObjectInputStream(in);
        value = sIn.readObject();
         in.close();
         sIn.close();
        return value;
    }
}
