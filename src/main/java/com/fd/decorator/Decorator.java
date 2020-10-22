package com.fd.decorator;

/**
 * @author ：zxq
 * @date ：Created in 2020/10/22 14:28
 */

public class Decorator implements Component {

    private Component component;

    public Decorator(Component component) {
        this.component = component;
    }

    @Override
    public void doSomething() {
        component.doSomething();
    }
}
