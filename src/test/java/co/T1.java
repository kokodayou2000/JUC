package co;

import co.paralleluniverse.fibers.Fiber;
import co.paralleluniverse.fibers.SuspendExecution;
import org.junit.Test;

public class T1 {

    public static void main(String[] args) {
        new Fiber<Void>(){
            @Override
            protected Void run() {
                System.out.println("Hello Fiber");
                return null;
            }
        }.start();
    }
}
