package com.fd.decorator;

/**
 * @author ：zxq
 * @date ：Created in 2020/10/22 15:05
 */

public class ConcreteDecorator2 extends Decorator {

    public ConcreteDecorator2(Component component) {
        super(component);
    }

    @Override
    public void doSomething() {
        doOtherSomething();
        super.doSomething();
    }


    public void doOtherSomething(){
        System.out.println("功能c");
    }
}
