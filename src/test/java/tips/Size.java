package tips;

import org.apache.lucene.util.RamUsageEstimator;

import java.util.HashMap;
import java.util.Map;

public class Size {
    //计算java中对象的大小
    /*
    对象大小包含
    头和内容
    头 （地址(4),标记(8),数组长度(4)）
    地址就是地址
    标记比如锁标记，hashcode，gc年龄...
    数组长度 如果对象是数组，会有一个记录长度的，如果不是数组，就不会有这个参数
    内容（）
    观察Integer类里面的参数，包含static属性的放到了方法区不是存放在对象里面的
    实际上Integer类里面就一个int类型参数
    private final int value;
    所以该对象的大小
    address(4) + mark(8)+内容(int value) == 16


     */
    public static void main(String[] args) {
        Integer integer = 999;
        System.out.println(RamUsageEstimator.shallowSizeOf(integer));
        String str = "a";
        System.out.println(RamUsageEstimator.shallowSizeOf(str));
        Person person = new Person();
        System.out.println(RamUsageEstimator.shallowSizeOf(person));
        //map的大小
        Map<String,String> map = new HashMap<>();
        /*
        Map inherit AbstractMap<K,V>
        {
            transient Set<K>        keySet;
            transient Collection<V> values;
            这两个引用类型的大小是4个字节
            加起来8个
        }
        HashMap里面的
        transient Node<K,V>[] table;
        transient Set<Map.Entry<K,V>>[] entrySet;
        transient int size;
        transient int modCount;
        int threshold;
        6 * 4

        head(4) + mark(8)
        4 + 8 + (4+4) + (6*4)
        count =44 但是需要对齐，就变成48了

         */
        System.out.println(RamUsageEstimator.shallowSizeOf(map));


    }
    static class Person{
        Integer age;
        String name;
    }
}
