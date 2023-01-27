package com.github.lewisbyte;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import cn.hutool.core.io.FileUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.TestMethodOrder;

@Slf4j
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ConnectionResourceLeakExampleTest {

    /* 临时文件 */
    private File tmpFile;

    private final ConnectionResourceLeakExample connectionResourceLeakExample = new ConnectionResourceLeakExample();

    /**
     * 单元测试开始之前创建一个临时文件用于测试
     */
    @Before
    public void setUp() {
        this.tmpFile = FileUtil.createTempFile();
    }

    /**
     * 单元测试结束，清除临时文件
     */
    @After
    public void tearDown() {
        FileUtil.del(this.tmpFile);
    }


    @Test
    @Ignore
    public void getCurrentProcessHandleCount() {
        connectionResourceLeakExample.printAndGetCurrentProcessHandleNumber();
    }

    @Test
    @Order(1)
    public void test001WithOpenFileAndCloseResource() throws IOException {
        log.info("------------开始test001WithOpenFileAndCloseResource-测试打开文件，关闭文件资源------------");
        int start = connectionResourceLeakExample.printAndGetCurrentProcessHandleNumber();
        int loopNum = 1000;
        for (int i = 0; i < loopNum; i++) {
            connectionResourceLeakExample.openFileAndCloseResource(tmpFile.getAbsolutePath());
        }
        int end = connectionResourceLeakExample.printAndGetCurrentProcessHandleNumber();

        log.info("打开[{}]个文件资源，进程句柄增加: [{}]", loopNum, end - start);
        log.info("------------结束test001WithOpenFileAndCloseResource-测试打开文件，关闭文件资源------------");
    }

    @Test
    @Order(2)
    public void test002WithOpenFile() throws FileNotFoundException {
        log.info("------------开始test002WithOpenFile-测试打开文件，不关闭文件资源------------");
        int start = connectionResourceLeakExample.printAndGetCurrentProcessHandleNumber();
        int loopNum = 1000;
        for (int i = 0; i < loopNum; i++) {
            connectionResourceLeakExample.openFileResource(tmpFile.getAbsolutePath());
        }
        int end = connectionResourceLeakExample.printAndGetCurrentProcessHandleNumber();
        log.info("打开[{}]个文件资源，进程句柄增加: [{}]", loopNum, end - start);
        log.info("------------结束test002WithOpenFile-测试打开文件，不关闭文件资源------------");
    }
}