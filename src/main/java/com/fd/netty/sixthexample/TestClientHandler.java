package com.fd.netty.sixthexample;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.Random;

/**
 * @author ：zxq
 * @date ：Created in 2020/10/10 13:38
 */

public class TestClientHandler extends SimpleChannelInboundHandler<MyDataInfo.Person> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MyDataInfo.Person msg) throws Exception {

    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        int randomInt = new Random().nextInt(3);
        MyDataInfo.MyMessage myMessage = null;
        if (0 == randomInt) {
            myMessage = MyDataInfo.MyMessage.newBuilder()
                    .setDataType(MyDataInfo.MyMessage.DataType.PersonType)
                    .setPerson(
                            MyDataInfo.Person.newBuilder()
                                    .setName("悟空")
                                    .setAge(500)
                                    .setAddress("花果山")
                                    .build()
                    ).build();

        } else if (1 == randomInt) {
            myMessage = MyDataInfo.MyMessage.newBuilder()
                    .setDataType(MyDataInfo.MyMessage.DataType.DogType)
                    .setDog(
                            MyDataInfo.Dog.newBuilder()
                                    .setName("二哈")
                                    .setAge("12")
                                    .build()
                    ).build();
        } else {
            myMessage = MyDataInfo.MyMessage.newBuilder()
                    .setDataType(MyDataInfo.MyMessage.DataType.CatType)
                    .setCat(
                            MyDataInfo.Cat.newBuilder()
                                    .setName("咖啡猫")
                                    .setCity("冰")
                                    .build()
                    ).build();
        }

        ctx.channel().writeAndFlush(myMessage);
    }
}
