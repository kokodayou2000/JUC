package jiang.demo02;

//import jdk.internal.vm.annotation.Contended;


public class Entry {
    //伪共享，cpu处理缓存块的时候，为了内存对齐。。。
//    @Contended
    String id;
    float price;
    Integer capable = 10;

    public Entry(String id, float price) {
        this.id = id;
        this.price = price;
    }

    public Entry(String id, float price, Integer capable) {
        this.id = id;
        this.price = price;
        this.capable = capable;
    }
}

