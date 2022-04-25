package jiang.demo02;

import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

public class Producer {
    //生产商品
    //假设这个生产者每秒钟生产一个apple，两个banana
    static Store store = Store.getInstance();

    private static volatile  int money = 1000;


    ConcurrentHashMap<String,Entry> map = new ConcurrentHashMap<String,Entry>();

    public Producer() {
        map.put("001",new Entry("001",(float)(10.1),0));
        map.put("002",new Entry("002",(float)(12.1),0));
    }

    public void start_production() throws InterruptedException {
        //
        while (true){
            TimeUnit.SECONDS.sleep(1);
            if (!store.inStore("001", 1)){
                //如果存入商店失败
                Entry entry = map.get("001");
                entry.capable +=1;
            }else{
                System.out.println("Produce 供货了一个苹果：当前商店剩余苹果数-->"+store.map.get("001").capable);
            }

            if (!store.inStore("002",2)) {
                Entry entry = map.get("002");
                entry.capable +=2;
            }else {
                System.out.println("Produce 供货了两个香蕉：当前商店剩余香蕉数-->"+store.map.get("002").capable);
            }

            money +=1;
        }
    }
}
