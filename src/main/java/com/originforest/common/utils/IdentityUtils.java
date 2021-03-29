/**
* Copyright (c) 2021 Copyright Originforest All Rights Reserved.
* Author: brian.li
* Date: 2021-03-26 15:46
* Desc: 
*/

package com.originforest.common.utils;

import java.nio.ByteBuffer;
import java.security.SecureRandom;
import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 唯一标识符工具.
 */
public class IdentityUtils {

    private static final long RANDOM;
    private static final AtomicInteger atom_se;
    private static final AtomicInteger atom_st;

    private static final int LOW_ORDER_THREE_BYTES = 0x00ffffff;

    private static final char[] HEX_CHARS = new char[] { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b',
            'c', 'd', 'e', 'f' };

    public static final int ID_LENGTH = 32;

    static {
        Date now = new Date();

        RANDOM = new SecureRandom().nextLong();
        atom_st = new AtomicInteger(((int) (now.getTime() / 1000)) << 8 >> 8);
        atom_se = new AtomicInteger(0);
    }

    /**
     * 生成下一个ID
     *
     * @return Id
     */
    public static String nextId() {
        Date now = new Date();
        int timestamp = (int) (now.getTime() / 1000);

        ByteBuffer buffer = ByteBuffer.allocate(ID_LENGTH / 2);

        buffer.put(int3(timestamp));
        buffer.put(int2(timestamp));
        buffer.put(int1(timestamp));
        buffer.put(int0(timestamp));

        buffer.put(int5(RANDOM));
        buffer.put(int4(RANDOM));
        buffer.put(int3(RANDOM));
        buffer.put(int2(RANDOM));
        buffer.put(int1(RANDOM));
        buffer.put(int0(RANDOM));

        int se = _get_se_inc();
        buffer.put(int2(se));
        buffer.put(int1(se));
        buffer.put(int0(se));

        int st = _get_st_inc();
        buffer.put(int2(st));
        buffer.put(int1(st));
        buffer.put(int0(st));

        byte[] arr = buffer.array();
        char[] chars = new char[ID_LENGTH];

        int i = 0;
        for (byte b : arr) {
            chars[i++] = HEX_CHARS[b >> 4 & 0xF];
            chars[i++] = HEX_CHARS[b & 0xF];
        }

        return new String(chars);
    }

    /**
     * Validator id.
     * 
     * @param ids id array.
     * @return whether ids is valid.
     */
    public static boolean isValid(final String... ids) {
        for (int i = 0; i < ids.length; i++) {
            String id = ids[i];
            if (null == id || id.length() != ID_LENGTH) {
                return false;
            }

            for (int j = 0; j < id.length(); j++) {
                char c = id.charAt(j);
                if (!((c >= '0' && c <= '9') || (c >= 'a' && c <= 'z'))) {
                    return false;
                }
            }
        }

        return true;
    }

    private static int _get_se_inc() {
        return atom_se.getAndIncrement() & LOW_ORDER_THREE_BYTES;
    }

    private static int _get_st_inc() {
        return atom_st.getAndIncrement() & LOW_ORDER_THREE_BYTES;
    }

    private static byte int5(final long x) {
        return (byte) (x >> 40);
    }

    private static byte int4(final long x) {
        return (byte) (x >> 32);
    }

    private static byte int3(final int x) {
        return (byte) (x >> 24);
    }

    private static byte int3(final long x) {
        return (byte) (x >> 24);
    }

    private static byte int2(final int x) {
        return (byte) (x >> 16);
    }

    private static byte int2(final long x) {
        return (byte) (x >> 16);
    }

    private static byte int1(final int x) {
        return (byte) (x >> 8);
    }

    private static byte int1(final long x) {
        return (byte) (x >> 8);
    }

    private static byte int0(final int x) {
        return (byte) (x);
    }

    private static byte int0(final long x) {
        return (byte) (x);
    }

    private static byte short1(final short x) {
        return (byte) (x >> 8);
    }

    private static byte short0(final short x) {
        return (byte) (x);
    }
}
