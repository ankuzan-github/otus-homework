package ru.otus.hw03.oom;

import javax.management.MBeanServer;
import javax.management.ObjectName;
import java.lang.management.ManagementFactory;
import java.util.ArrayList;

import static ru.otus.hw03.Utils.switchOnMonitoring;

public class OOMDemo {

    public static void main(String... args) throws Exception {
        System.out.println("Starting pid: " + ManagementFactory.getRuntimeMXBean().getName());
        switchOnMonitoring(new ArrayList<>());
        long beginTime = System.currentTimeMillis();

        int size = 5 * 100;
        int loopCounter = 1_000_000;
        MBeanServer mbs = ManagementFactory.getPlatformMBeanServer();

        ObjectName name = new ObjectName("ru.otus.hw03:type=OOM");

        OOM mbean = new OOM(loopCounter);
        mbs.registerMBean(mbean, name);
        mbean.setSize(size);
        mbean.run();

        System.out.println("time:" + (System.currentTimeMillis() - beginTime) / 1000);
    }

}
