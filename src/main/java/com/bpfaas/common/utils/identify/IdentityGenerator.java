package com.bpfaas.common.utils.identify;

import com.bpfaas.common.exception.BpRuntimeException;
import org.scijava.nativelib.NativeLoader;

import java.io.IOException;

/**
 * 分布式ID生成器
 *
 * @author rjf
 * @since 2021/1/26
 */
public class IdentityGenerator {

    static {
        /* System.load("/natives/osx_64/libidentify.dylib"); */
        try {
            NativeLoader.loadLibrary("identify");
        } catch (IOException e) {
            throw new BpRuntimeException("ID生成库加载失败", e);
        }

        // 初始化
        init();
    }

    /**
     * 生成下一个ID
     *
     * @return Id
     */
    public static String nextId() {
        return gen();
    }

    private static native void init();

    private static native String gen();

    public static void main(String[] args) {
        for (int i = 0; i < 5; i++) {
            System.out.println(IdentityGenerator.nextId());
        }
    }

}
