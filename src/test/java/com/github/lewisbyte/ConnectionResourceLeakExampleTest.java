package com.github.lewisbyte;

import java.io.File;
import java.io.FileNotFoundException;
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
    public void getCurrentProcessHandleCount() {
        connectionResourceLeakExample.printAndGetCurrentProcessHandleNumber();
    }

    @Test
    public void openFileAndCloseResource() throws IOException {
        log.info("------------openFileAndCloseResource-测试打开文件关闭文件资源------------");
        connectionResourceLeakExample.printAndGetCurrentProcessHandleNumber();
        for (int i = 0; i < 1000; i++) {
            connectionResourceLeakExample.openFileAndCloseResource(tmpFile.getAbsolutePath());
        }
        connectionResourceLeakExample.printAndGetCurrentProcessHandleNumber();
        log.info("------------openFileAndCloseResource-测试打开文件关闭文件资源------------");
    }

    @Test
    public void openFile() throws FileNotFoundException {
        log.info("------------openFile-测试打开文件不关闭文件资源------------");
        connectionResourceLeakExample.printAndGetCurrentProcessHandleNumber();
        for (int i = 0; i < 1000; i++) {
            connectionResourceLeakExample.openFileResource(tmpFile.getAbsolutePath());
        }
        connectionResourceLeakExample.printAndGetCurrentProcessHandleNumber();
        log.info("------------openFile-测试打开文件不关闭文件资源------------");
    }


}