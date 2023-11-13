package com.yuge.ing.nio;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * ByteBufferTemplate
 *
 * @author yuge
 * @since 2023-11-04
 */
public class ByteBufferTest {

    public static void main(String[] args) throws URISyntaxException {
        ClassLoader classLoader = ByteBufferTest.class.getClassLoader();
        File file = new File(classLoader.getResource("data.txt").toURI());
        // 获取FileChannel的方法： 1. 输入输出流，2.RandomAccessFile
        try (FileChannel channel = new FileInputStream(file).getChannel()) {
            while (true) {
                // 1. 获取一个ByteBuffer
                ByteBuffer buffer = ByteBuffer.allocate(10);
                // 2. 读取数据到buffer中
                int read = channel.read(buffer);
                if (read == -1) {
                    break;
                }
                // 3. 切换读写模式
                buffer.flip();
                // 4. 处理数据
                while (buffer.hasRemaining()) {
                    System.out.print((char) buffer.get());
                }
                // 5. 清空buffer
                buffer.clear();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void allocate() {
        // class java.nio.HeapByteBuffer  - java 堆内存，读写效率低，受到GC的影响
        ByteBuffer heapByteBuffer = ByteBuffer.allocate(10);
        System.out.println(heapByteBuffer);
        // class java.nio.DirectByteBuffer  - 直接内存，读写效率高（少一次拷贝），不受GC的影响，分配效率低（受操作系统限制），使用不当会导致内存泄漏
        ByteBuffer directByteBuffer = ByteBuffer.allocateDirect(10);
        System.out.println(directByteBuffer);
    }

    public void get() {
        ByteBuffer byteBuffer = ByteBuffer.allocate(10);
        byteBuffer.put(new byte[]{'1', '2', '3', '4', '5', '6', '7', '8', '9', '0'});
        // 移动position
        byteBuffer.get();
        // 不移动position
        byteBuffer.get(1);
    }

}
