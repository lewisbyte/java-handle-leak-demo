package com.github.lewisbyte;

import java.io.File;
import java.io.IOException;

import cn.hutool.core.io.FileUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

@Slf4j
public class ConnectionResourceLeakExampleTest {

    /* 临时文件 */
    private File tmpFile;

    /**
     * 单元测试开始之前创建一个临时文件用于测试
     */
    @Before
    public void setUp() {
        log.info("创建临时文件");
        this.tmpFile = FileUtil.createTempFile();
        log.info("创建的临时文件路径是: {}", tmpFile.getAbsolutePath());
    }

    /**
     * 单元测试结束，清除临时文件
     */
    @After
    public void tearDown() {
        log.info("删除临时文件");
        FileUtil.del(this.tmpFile);
        log.info("删除的临时文件路径是: {}", tmpFile.getAbsolutePath());
    }

    @Test
    public void testHandleCount() throws IOException {
        log.info("测试句柄计数");

        new ProcessBuilder("sh", "-c", "lsof | wc -l") // Linux / Unix terminal
                .start();

    }
}