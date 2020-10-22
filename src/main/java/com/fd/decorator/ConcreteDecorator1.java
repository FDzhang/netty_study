package com.fd.decorator;

/**
 * @author ：zxq
 * @date ：Created in 2020/10/22 15:02
 */

public class ConcreteDecorator1 extends Decorator {

    public ConcreteDecorator1(Component component) {
        super(component);
    }

    @Override
    public void doSomething() {
        super.doSomething();
        doOtherSomething();
    }

    public void doOtherSomething(){
        System.out.println("功能b");
    }
}
