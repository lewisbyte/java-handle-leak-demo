# java-handle-leak-demo(java句柄泄露样例)

- 本仓库为样例仓库吗，通过java来复现linux里的句柄泄露问题


- 工程使用说明：
- jdk-8
- 此样例工程只能在linux或者macos下使用，不能在window系统使用。
- 本样例工程主要展示：linux的句柄泄露相关的演示
- 执行命令 ` mvn clean test `，观察日志输出打印

- 单元测试日志

```log
[main] INFO com.github.lewisbyte.ConnectionResourceLeakExampleTest - ------------openFile-测试打开文件，不关闭文件资源------------
[main] INFO com.github.lewisbyte.ConnectionResourceLeakExample - 当前进程 pid: 19956 句柄数量：53
[main] INFO com.github.lewisbyte.ConnectionResourceLeakExample - 当前进程 pid: 19956 句柄数量：1053
[main] INFO com.github.lewisbyte.ConnectionResourceLeakExampleTest - 打开文件不关闭文件资源，进程句柄增加 1000
[main] INFO com.github.lewisbyte.ConnectionResourceLeakExampleTest - ------------openFile-测试打开文件，不关闭文件资源------------
[main] INFO com.github.lewisbyte.ConnectionResourceLeakExampleTest - ------------openFileAndCloseResource-测试打开文件，关闭文件资源------------
[main] INFO com.github.lewisbyte.ConnectionResourceLeakExample - 当前进程 pid: 19956 句柄数量：1055
[main] INFO com.github.lewisbyte.ConnectionResourceLeakExample - 当前进程 pid: 19956 句柄数量：1055
[main] INFO com.github.lewisbyte.ConnectionResourceLeakExampleTest - 打开文件关闭文件资源，进程句柄增加 0
[main] INFO com.github.lewisbyte.ConnectionResourceLeakExampleTest - ------------openFileAndCloseResource-测试打开文件，关闭文件资源------------
```

- 日志说明
- 通过日志可以观察到：
- 在打开文件之后，进行关闭操作的测试方法中，句柄的数量没有增加。文件句柄被及时关闭
- 在打开文件之后，没有进行关闭操作的测试方法中，句柄的数量出现了1000个数量的增长，符合预期