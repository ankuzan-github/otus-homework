package ru.otus.hw03.gc;

import ru.otus.hw03.Utils;

import javax.management.MBeanServer;
import javax.management.ObjectName;
import java.lang.management.ManagementFactory;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by tully.
 * <p>
 * Updated by Sergey
 */
/*
О формате логов
http://openjdk.java.net/jeps/158


-Xms512m
-Xmx512m
-Xlog:gc=debug:file=./gc-%p-%t.log:tags,uptime,time,level:filecount=5,filesize=10m
-XX:+HeapDumpOnOutOfMemoryError
-XX:HeapDumpPath=./dump
-XX:+UseG1GC
*/

/*
1)
    default, time: 83 sec (82 without Label_1)
2)
    -XX:MaxGCPauseMillis=100000, time: 82 sec //Sets a target for the maximum GC pause time.
    -XX:GCPauseIntervalMills=
3)
    -XX:MaxGCPauseMillis=10, time: 91 sec

4)
-Xms2048m
-Xmx2048m
    time: 81 sec

5)
-Xms5120m
-Xmx5120m
    time: 80 sec

5)
-Xms20480m
-Xmx20480m
    time: 81 sec (72 without Label_1)

*/

public class GcDemo {
    public static void main(String... args) throws Exception {
        System.out.println("Starting pid: " + ManagementFactory.getRuntimeMXBean().getName());
        List<Long> gcTimeList = new ArrayList<>();
        Utils.switchOnMonitoring(gcTimeList);
        long beginTime = System.currentTimeMillis();

        int size = 5 * 1000;
        int loopCounter = 1_000_000;
        MBeanServer mbs = ManagementFactory.getPlatformMBeanServer();

        ObjectName name = new ObjectName("ru.otus.hw03:type=Benchmark");

        Benchmark mbean = new Benchmark(loopCounter);
        mbs.registerMBean(mbean, name);
        mbean.setSize(size);
        mbean.run();

        System.out.println("GC duration: " + gcTimeList.stream().mapToLong(i -> i).sum() / 1000);
        System.out.println("Max GC cleanup: "+ gcTimeList.stream().reduce(Long::max).orElse(0L));
        System.out.println("Work time: " + (System.currentTimeMillis() - beginTime) / 1000);
    }
}
