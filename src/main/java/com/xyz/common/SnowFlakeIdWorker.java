package com.xyz.common;

/**
 * @author Lzs
 * @date 2021/1/22 10:55
 * @description 雪花算法(分布式全局唯一id)
 * SnowFlake的结构如下(每部分用-分开):<br>
 * 0 - 0000000000 0000000000 0000000000 0000000000 0 - 00000 - 00000 - 000000000000 <br>
 * 1位标识，由于long基本类型在Java中是带符号的，最高位是符号位，正数是0，负数是1，所以id一般是正数，最高位是0<br>
 * 41位时间截(毫秒级)，注意，41位时间截不是存储当前时间的时间截，而是存储时间截的差值（当前时间截 - 开始时间截)
 * 得到的值），这里的的开始时间截，一般是我们的id生成器开始使用的时间，由我们程序来指定的（如下下面程序IdWorker类的startTime属性）。
 * 41位的时间截，可以使用69年，年T = (1L << 41) / (1000L * 60 * 60 * 24 * 365) = 69<br>
 * 10位的数据机器位，可以部署在1024个节点，包括5位datacenterId和5位workerId<br>
 * 12位序列，毫秒内的计数，12位的计数顺序号支持每个节点每毫秒(同一机器，同一时间截)产生4096个ID序号<br>
 */
public class SnowFlakeIdWorker {
//    开始时间戳
    private final long twepoch = 1420041600000L;
//    机器id所占位数
    private final long workerIdBits = 5L;
//    数据标识所占的位数
    private final long dataCenterIdBits = 5L;
//     支持的最大机器id
    private final long maxWorkerId = -1L ^ (-1L << workerIdBits);
//     最大支持的数据di
    private final long maxDataCenterId = -1L ^ (-1L << dataCenterIdBits);
//    序列在id中占的位数
    private final long sequenceBits = 12L;
//    机器Id向左移12位
    private final long workerIdShift = sequenceBits;
//    数据标识Id向左移12+5位
    private final long dataCenterIdShift = sequenceBits + workerIdBits;
//    时间戳向左移12 + 5 + 5 位
    private final long timestampLeftShift = sequenceBits + workerIdBits + dataCenterIdBits;
//    生成序列的掩码, 这里为4095, 0b111111111111 = 4095
    private final long sequenceMask = -1L ^ (-1L << sequenceBits);

//    工作机器id(0-31)
    private long workerId;
//    数据中心id(0-31)
    private long dataCenterId;
//    毫秒内序列
    private long sequence = 0L;
//    上上次生成Id的时间戳
    private long lastTimestamp = -1L;

    /**
     * 构造函数
     * @param workerId 工作机器id
     * @param dataCenterId 数据中心id
     */
    public SnowFlakeIdWorker(long workerId, long dataCenterId) {
        if(workerId > maxWorkerId || workerId < 0) {
            throw new IllegalArgumentException(String.format("worker id can't be greater than %d or less than 0", maxWorkerId));
        }
        if(dataCenterId > maxDataCenterId || dataCenterId < 0) {
            throw new IllegalArgumentException(String.format("dataCenter id can't be greater than %d or less than 0", maxDataCenterId));
        }
        this.dataCenterId = dataCenterId;
        this.workerId = workerId;
    }

    /**
     * 获取下一id, 线程安全
     * @return snowflakeId
     */
    public synchronized long nextId() {
        long timestamp = timeGen();

//        当前时钟小于上一次id的时钟, 系统时钟异常, 报错
        if(timestamp < lastTimestamp) {
            throw new RuntimeException(
                    String.format("Clock moved backwards. Refusing to generate id for %d millisecond", lastTimestamp = timestamp)
            );
        }
//        和上次生成id在同一毫秒内, 则进行毫秒内序列
        if(timestamp == lastTimestamp) {
            sequence = (sequence + 1) & sequenceMask;
//            证明当前毫秒可用序列已经用完, sequence溢出, 应阻塞到下一毫秒
            if (sequence == 0) {
                timestamp = tilNexMillis(lastTimestamp);
            }
        }
        // 进入下个毫秒, 毫秒内序列归零
        else{
            sequence = 0L;
        }
//        更新上次id的时间戳
        lastTimestamp = timestamp;

        return ((timestamp - twepoch) << timestampLeftShift) // 时间戳
                | (dataCenterId << dataCenterIdShift)   // 数据中心id
                | (workerId << workerIdShift)   // 工作机器id
                | sequence;     // 毫秒内序列
    }

    /**
     * 阻塞到下一毫秒
     * @param lastTimestamp 上次生成id的时间戳
     * @return 当前时间戳
     */
    protected long tilNexMillis(long lastTimestamp) {
        long timestamp = timeGen();
        while ((timestamp <= lastTimestamp)) {
            timestamp = timeGen();
        }
        return timestamp;
    }

    /**
     * 返回已毫秒为单位的单枪时间
     * @return 当前时间(毫秒)
     */
    protected long timeGen() {
        return System.currentTimeMillis();
    }
}