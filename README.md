# java-handle-leak-demo(java句柄泄露样例)

- 本仓库为样例仓库吗，通过java来复现linux里的句柄泄露问题



- 打开文件关闭文件资源

```log
[main] INFO com.github.lewisbyte.ConnectionResourceLeakExampleTest - ------------openFileAndCloseResource-测试打开文件关闭文件资源------------
[main] INFO com.github.lewisbyte.ConnectionResourceLeakExample - 获取当前进程 pid: 12053句柄数量：       88
[main] INFO com.github.lewisbyte.ConnectionResourceLeakExample - 获取当前进程 pid: 12053句柄数量：       88
[main] INFO com.github.lewisbyte.ConnectionResourceLeakExampleTest - ------------openFileAndCloseResource-测试打开文件关闭文件资源------------
```

- 打开文件不关闭文件资源

```log

[main] INFO com.github.lewisbyte.ConnectionResourceLeakExampleTest - ------------openFile-测试打开文件不关闭文件资源------------
[main] INFO com.github.lewisbyte.ConnectionResourceLeakExample - 获取当前进程 pid: 11954句柄数量：       53
[main] INFO com.github.lewisbyte.ConnectionResourceLeakExample - 获取当前进程 pid: 11954句柄数量：     1053
[main] INFO com.github.lewisbyte.ConnectionResourceLeakExampleTest - ------------openFile-测试打开文件不关闭文件资源------------
```