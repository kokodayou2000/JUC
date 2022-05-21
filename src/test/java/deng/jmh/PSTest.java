package deng.jmh;

import com.deng.jmh.PS;
import org.openjdk.jmh.annotations.*;

public class PSTest {
    @Benchmark
//    @Warmup(iterations = 1,time = 3) 预热，迭代的次数 时间
//    @Fork(5)
//    @BenchmarkMode(Mode.Throughput) 测试的模式 1.吞吐量。。。
//    @Measurement(iterations = 1,time = 3) 测试 迭代数 时间
    public void testForEach() {
        PS.foreach();
    }
    @Benchmark
    public void testParallelForEach() {
        PS.parallel();
    }

}
