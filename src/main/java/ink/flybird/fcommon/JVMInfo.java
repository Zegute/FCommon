package ink.flybird.fcommon;

import java.lang.management.ManagementFactory;

public interface JVMInfo {
    static String getJavaVersion(){
        return ManagementFactory.getRuntimeMXBean().getVmVersion();
    }

    static String getJavaName(){
        return ManagementFactory.getRuntimeMXBean().getVmVendor();
    }

    static String getOSName(){
        return ManagementFactory.getOperatingSystemMXBean().getName();
    }

    static String getOSVersion(){
        return ManagementFactory.getOperatingSystemMXBean().getVersion();
    }

    static String getUsedMemory(){
        Runtime runtime=Runtime.getRuntime();
        return (runtime.totalMemory()-runtime.freeMemory())/1048576+"M";
    }

    static String getTotalMemory(){
        Runtime runtime=Runtime.getRuntime();
        return (runtime.totalMemory())/1048576+"M";
    }

    static String getUsage(){
        Runtime runtime=Runtime.getRuntime();
        return (int)(100-(runtime.freeMemory()/ (float)runtime.totalMemory())*100)+"%";
    }
}
