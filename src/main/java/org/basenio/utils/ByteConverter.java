package org.basenio.utils;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;

public class ByteConverter {
    private final static String CHAR_SET = "UTF-8";

    public enum ByteOrder {
        Little_Endian, Big_Endian
    }

    public static short decodeShort(final byte[] source, int index, ByteOrder byteOrder) {

        switch (byteOrder) {
            case Little_Endian:
                return (short) (((source[index + 1] & 0xFF) << 8) | (source[index] & 0xFF));
            case Big_Endian:
                return (short) (((source[index] & 0xFF) << 8) | (source[index + 1] & 0xFF));
            default:
                throw new IllegalArgumentException("Unknown ByteOrder detected : " + byteOrder);
        }
    }

    public static int decodeInt(final byte[] source, int index, ByteOrder byteOrder) {
        switch (byteOrder) {
            case Little_Endian:
                return (((source[index + 3] & 0xff) << 24)
                        | ((source[index + 2] & 0xff) << 16)
                        | ((source[index + 1] & 0xff) << 8)
                        | (source[index] & 0xff));
            case Big_Endian:
                return (int) (((source[index] & 0xff) << 24)
                        | ((source[index + 1] & 0xff) << 16)
                        | ((source[index + 2] & 0xff) << 8)
                        | (source[index + 3] & 0xff));
            default:
                throw new IllegalArgumentException("Unknown ByteOrder detected : " + byteOrder);
        }
    }

    public static long decodeLong(final byte[] source, int index, ByteOrder byteOrder) {
        switch (byteOrder) {
            case Little_Endian:
                return (long) (((source[index + 7] & 0xffl) << 56)
                        | ((source[index + 6] & 0xffl) << 48)
                        | ((source[index + 5] & 0xffl) << 40)
                        | ((source[index + 4] & 0xffl) << 32)
                        | ((source[index + 3] & 0xffl) << 24)
                        | ((source[index + 2] & 0xffl) << 16)
                        | ((source[index + 1] & 0xffl) << 8)
                        | (source[index] & 0xffl));
            case Big_Endian:
                return (long) (((source[index] & 0xffl) << 56)
                        | ((source[index + 1] & 0xffl) << 48)
                        | ((source[index + 2] & 0xffl) << 40)
                        | ((source[index + 3] & 0xffl) << 32)
                        | ((source[index + 4] & 0xffl) << 24)
                        | ((source[index + 5] & 0xffl) << 16)
                        | ((source[index + 6] & 0xffl) << 8)
                        | (source[index + 7] & 0xffl));
            default:
                throw new IllegalArgumentException("Unknown ByteOrder detected : " + byteOrder);
        }
    }

    public static String decodeString(final byte[] source, int index, int len) {
        String res;
        int nMaxLen = (source.length < (index + len)) ? source.length : (index + len);
        for (int i = index; i < nMaxLen; i++) {
            if (source[i] == 0) {
                len = i - index;
                break;
            }
        }
        try {
            res = new String(source, index, len, CHAR_SET);
        } catch (UnsupportedEncodingException ex) {
            res = new String(source);
        }
        if (res != null)
            res = res.trim();

        return res;
    }

    public static int encodeShort(byte[] goal, int index, ByteOrder byteOrder, byte dataValue) {
        switch (byteOrder) {
            case Little_Endian:
                goal[index] = (byte) (dataValue & 0xFF);
                goal[index + 1] = (byte) (dataValue >> 8 & 0xFF);
            case Big_Endian:
                goal[index + 1] = (byte) (dataValue & 0xFF);
                goal[index] = (byte) (dataValue >> 8 & 0xFF);
            default:
                throw new IllegalArgumentException("Unknown ByteOrder detected : " + byteOrder);
        }
    }

    public static int encodeInt(byte[] goal, int index, ByteOrder byteOrder, int datavalue) {
        switch (byteOrder) {
            case Little_Endian:
                goal[index] = (byte) (datavalue & 0xff);
                goal[index + 1] = (byte) (datavalue >> 8 & 0xff);
                goal[index + 2] = (byte) (datavalue >> 16 & 0xff);
                goal[index + 3] = (byte) (datavalue >> 24 & 0xff);
                return 4;
            case Big_Endian:
                goal[index + 3] = (byte) (datavalue & 0xff);
                goal[index + 2] = (byte) (datavalue >> 8 & 0xff);
                goal[index + 1] = (byte) (datavalue >> 16 & 0xff);
                goal[index] = (byte) (datavalue >> 24 & 0xff);
                return 4;
            default:
                throw new IllegalArgumentException("Unknown ByteOrder detected : " + byteOrder);
        }
    }

    public static int encodeLong(byte[] goal, int index, ByteOrder byteOrder, long datavalue) {
        switch (byteOrder) {
            case Little_Endian:
                goal[index] = (byte) (datavalue & 0xff);
                goal[index + 1] = (byte) (datavalue >> 8 & 0xff);
                goal[index + 2] = (byte) (datavalue >> 16 & 0xff);
                goal[index + 3] = (byte) (datavalue >> 24 & 0xff);
                goal[index + 4] = (byte) (datavalue >> 32 & 0xff);
                goal[index + 5] = (byte) (datavalue >> 40 & 0xff);
                goal[index + 6] = (byte) (datavalue >> 48 & 0xff);
                goal[index + 7] = (byte) (datavalue >> 56 & 0xff);
                return 8;
            case Big_Endian:
                goal[index + 7] = (byte) (datavalue & 0xff);
                goal[index + 6] = (byte) (datavalue >> 8 & 0xff);
                goal[index + 5] = (byte) (datavalue >> 16 & 0xff);
                goal[index + 4] = (byte) (datavalue >> 24 & 0xff);
                goal[index + 3] = (byte) (datavalue >> 32 & 0xff);
                goal[index + 2] = (byte) (datavalue >> 40 & 0xff);
                goal[index + 1] = (byte) (datavalue >> 48 & 0xff);
                goal[index] = (byte) (datavalue >> 56 & 0xff);
                return 8;
            default:
                throw new IllegalArgumentException("Unknown ByteOrder detected : " + byteOrder);
        }
    }

    public static int encodeString(byte[] goal, int index, int len, String datavalue) {
        byte[] temp;

        try {
            temp = datavalue.getBytes(CHAR_SET);
        } catch (UnsupportedEncodingException ex) {
            temp = datavalue.getBytes();
        }

        int i;
        for (i = 0; i < temp.length; i++) {
            goal[index + i] = temp[i];
        }

        while (i < len) {
            goal[index + i] = 0;
            i++;
        }

        return len;
    }

    public static int byteCopy(byte[] dst, byte[] src, int dstPos, int len) {
        int i;
        for (i = 0; i < len; i++) {
            dst[dstPos + i] = src[i];
        }
        return i;
    }
}
