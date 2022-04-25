package jiang.demo02;

import java.util.concurrent.ConcurrentHashMap;

public class Store {
    //商店负责存储商品，制定商品价格，卖商品
    // id 商品名称 商品的价钱

    //单例
    private static volatile Store instance;
    //只是记录一个总数，如果总数达到110的时候，就可以暂停接受供货了
    private static volatile int ALlCapable = 0;

    public synchronized static Store getInstance(){
        if (instance == null){
            synchronized (Store.class){
                if (instance == null){
                    instance = new Store();
                }
            }
        }
        return instance;
    }

    private Store() {

        System.out.println("create store");
        Entry apple = new Entry("001", (float) 10.1);
        map.put("001",apple);
        Entry banana = new Entry("002", (float) 12.7);
        map.put("002",banana);


    }


    ConcurrentHashMap<String,Entry> map = new ConcurrentHashMap<String,Entry>();

    public synchronized boolean inStore(String id,int num){
        //传入数量和id
        Entry entry = map.get(id);
        if (entry.capable > 50){
            System.out.println("当前商品已经足够多了"+entry.capable);
            return false;
        }else{
            entry.capable += num;
        }
        return true;
    }

    public synchronized void outStore(String id,int num){

        Entry entry = map.get(id);
        if (entry.capable - num < 0){
            System.out.println("当前商品已经不够了"+entry.capable);
            return;
        }else {
            entry.capable -= num;
        }

    }

    public void modifyPrice(String id,int price){
        Entry entry = map.get(id);
        entry.price = price;
    }



}
