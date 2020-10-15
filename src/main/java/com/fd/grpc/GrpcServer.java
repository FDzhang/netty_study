package com.fd.grpc;

import io.grpc.Server;
import io.grpc.ServerBuilder;

/**
 * @author ：zxq
 * @date ：Created in 2020/10/15 17:52
 */

public class GrpcServer {
    private Server server;

    private void start() throws Exception {
        this.server = ServerBuilder.forPort(8899).addService(new StudentServiceImpl()).build().start();
    }

}
