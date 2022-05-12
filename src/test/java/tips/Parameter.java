package tips;

public class Parameter {

    public static void main(String[] args) {
        Person p = new Person();
        p.age = 999;
        System.out.println("p.hashCode() = "+p.hashCode());
        new Parameter().set(p);
        System.out.println(p.age);

    }
    private void set(Person p){
        //java方法之间的传递是值传递，只是拷贝一个副本给方法，但是如果是引用的副本还是指向了原来堆内存的空间
        System.out.println("p.hashCode() = " + p.hashCode());
        p.age = 1000;
        p = new Person();
        p.age=999;

    }


    static class Person{
        int age;
    }
}
