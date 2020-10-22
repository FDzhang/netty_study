package com.fd.decorator;

/**
 * @author ：zxq
 * @date ：Created in 2020/10/22 14:27
 */

public class ConcreteComponent implements Component {
    @Override
    public void doSomething() {
        System.out.println("功能a");
    }
}
