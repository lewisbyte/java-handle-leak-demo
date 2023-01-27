package com.github.lewisbyte;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import sun.management.VMManagement;

/**
 * @author xiaodongxu
 * 连接资源泄露样例
 * 2023/1/14
 **/
@Slf4j
public class ConnectionResourceLeakExample {
    /**
     * 打开文件并且关闭文件资源
     *
     * @param filePath 文件路径
     */
    public void openFileResource(String filePath) throws FileNotFoundException {
        BufferedReader ignored = new BufferedReader(new FileReader(filePath));
    }

    /**
     * 打开文件不关闭文件资源
     *
     * @param filePath 文件路径
     */
    public void openFileAndCloseResource(String filePath) throws IOException {
        try (BufferedReader ignored = new BufferedReader(new FileReader(filePath))) {
        } catch (Exception e) {
            throw e;
        }
    }

    /**
     * 获取当前进程的句柄数量
     */
    @SneakyThrows
    public int printAndGetCurrentProcessHandleNumber() {
        int pid = getCurrentProcessId();
        StringBuilder message = new StringBuilder();
        message.append("获取当前进程 pid: ").append(pid).append("句柄数量：");

        Process processResult = new ProcessBuilder("bash", "-c", String.format("lsof | grep %d | wc -l", pid))
                .start();

        BufferedReader reader = new BufferedReader(new InputStreamReader(processResult.getInputStream()));
        String line;
        while ((line = reader.readLine()) != null) {
            message.append(' ');
            message.append(line);
        }

        log.info(message.toString());
        return 0;
    }

    /**
     * 获取当前的进程id
     *
     * @return 进程id
     */
    @SneakyThrows
    private static int getCurrentProcessId() {

        RuntimeMXBean runtime = ManagementFactory.getRuntimeMXBean();
        Field jvm = runtime.getClass().getDeclaredField("jvm");
        jvm.setAccessible(true);

        VMManagement management = (VMManagement) jvm.get(runtime);
        Method method = management.getClass().getDeclaredMethod("getProcessId");
        method.setAccessible(true);

        return (Integer) method.invoke(management);
    }
}
