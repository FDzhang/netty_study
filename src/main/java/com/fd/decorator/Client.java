package com.fd.decorator;

/**
 * @author ：zxq
 * @date ：Created in 2020/10/22 15:06
 */

public class Client {

    public static void main(String[] args) {
        Component component =
                new ConcreteDecorator2(new ConcreteDecorator1(new ConcreteComponent()));

        component.doSomething();
    }
}
