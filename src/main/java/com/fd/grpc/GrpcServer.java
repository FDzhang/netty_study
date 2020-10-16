package com.fd.grpc;

import io.grpc.Server;
import io.grpc.ServerBuilder;

import java.util.concurrent.TimeUnit;

/**
 * @author ：zxq
 * @date ：Created in 2020/10/15 17:52
 */

public class GrpcServer {
    private Server server;

    private void start() throws Exception {
        this.server = ServerBuilder.forPort(8899).addService(new StudentServiceImpl()).build().start();
        System.out.println("server started");

        Runtime.getRuntime().addShutdownHook(new Thread(
                () ->{
                    System.err.println("关闭jvm");
                    GrpcServer.this.stop();
                }
        ));

        System.out.println("执行到这里");
    }

    private void stop(){
        if (null != this.server){
            this.server.shutdown();
        }
    }

    private void awaitTermination() throws InterruptedException {
        if (null != this.server){
            this.server.awaitTermination(3000, TimeUnit.MILLISECONDS);
        }
    }

    public static void main(String[] args) throws Exception {
        GrpcServer server = new GrpcServer();
        server.start();
        server.awaitTermination();
    }

}
