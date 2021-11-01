package sudo.client_user.union;

/**
 * @author glz
 * @version 1.0
 * @description 雪花算法
 *
 *
 * （1）高性能高可用：生成时不依赖于数据库，完全在内存中生成。
 *
 * （2）容量大：每秒中能生成数百万的自增ID。
 *
 * （3）ID自增：存入数据库中，索引效率高。
 *
 *   所谓的雪花算法是指定一个唯一键，通过指定位置的代表意思与某些位置的有顺序随机性，构建一个定长的数 （类似的有zookeeper的高 、低 32位的选举 都属于）
 *   在规定的长度，规定的时间范围内，规定的生成规则内，生成一个不依赖空间生成唯一规则
 * @date 2021/11/20:44
 */
public class SnowFlakeUnion {

    private long workerId; //  机器id

    private long datacenterId; // 机房id

    private long sequence; // 最新序列号

    private long twepoch = 1585644268888L;  // 时间初始值

    private long workerIdBits = 5l; // 5位机器id

    private long datacenterIdBits = 5l; // 机房id

    private long sequenceBits = 12l;

    private long maxWorkerId = -1l;

    private long maxDatacenterId = -1l^(-1l <<workerIdBits);


    private long workerIdShift = sequenceBits;
    private long datacenterIdShift = sequenceBits + workerIdBits;
    private long timestampLeftShift = sequenceBits + workerIdBits + datacenterIdBits;
    private long sequenceMask = -1L ^ (-1L << sequenceBits);


    public long lastTimestamp = -1L;

    public long getWorkerId(){
        return datacenterId;
    }

    public long getTimestamp(){
        return System.currentTimeMillis();
    }

    public SnowFlakeUnion(long workerId,long datacenterId,long sequence){
        // 检查机房id和机器id是否超过31 不能小于0
        if (workerId > maxWorkerId || workerId < 0) {
            throw new IllegalArgumentException(
                    String.format("worker Id can't be greater than %d or less than 0",maxWorkerId));
        }
        if (datacenterId > maxDatacenterId || datacenterId < 0) {

            throw new IllegalArgumentException(
                    String.format("datacenter Id can't be greater than %d or less than 0",maxDatacenterId));
        }
        this.workerId = workerId;
        this.datacenterId = datacenterId;
        this.sequence = sequence;

    }



    // 这个是核心方法，通过调用nextId()方法，让当前这台机器上的snowflake算法程序生成一个全局唯一的id
    public synchronized long nextId() {
        // 这儿就是获取当前时间戳，单位是毫秒
        long timestamp = timeGen();
        if (timestamp < lastTimestamp) {

            System.err.printf(
                    "clock is moving backwards. Rejecting requests until %d.", lastTimestamp);
            throw new RuntimeException(
                    String.format("Clock moved backwards. Refusing to generate id for %d milliseconds",
                            lastTimestamp - timestamp));
        }

        // 下面是说假设在同一个毫秒内，又发送了一个请求生成一个id
        // 这个时候就得把seqence序号给递增1，最多就是4096
        if (lastTimestamp == timestamp) {

            // 这个意思是说一个毫秒内最多只能有4096个数字，无论你传递多少进来，
            //这个位运算保证始终就是在4096这个范围内，避免你自己传递个sequence超过了4096这个范围
            sequence = (sequence + 1) & sequenceMask;
            //当某一毫秒的时间，产生的id数 超过4095，系统会进入等待，直到下一毫秒，系统继续产生ID
            if (sequence == 0) {
                timestamp = tilNextMillis(lastTimestamp);
            }

        } else {
            sequence = 0;
        }
        // 这儿记录一下最近一次生成id的时间戳，单位是毫秒
        lastTimestamp = timestamp;
        // 这儿就是最核心的二进制位运算操作，生成一个64bit的id
        // 先将当前时间戳左移，放到41 bit那儿；将机房id左移放到5 bit那儿；将机器id左移放到5 bit那儿；将序号放最后12 bit
        // 最后拼接起来成一个64 bit的二进制数字，转换成10进制就是个long型
        return ((timestamp - twepoch) << timestampLeftShift) |
                (datacenterId << datacenterIdShift) |
                (workerId << workerIdShift) | sequence;
    }


    /**
     * 当某一毫秒的时间，产生的id数 超过4095，系统会进入等待，直到下一毫秒，系统继续产生ID
     * @param lastTimestamp
     * @return
     */
    private long tilNextMillis(long lastTimestamp) {

        long timestamp = timeGen();

        while (timestamp <= lastTimestamp) {
            timestamp = timeGen();
        }
        return timestamp;
    }
    //获取当前时间戳
    private long timeGen(){
        return System.currentTimeMillis();
    }

}
