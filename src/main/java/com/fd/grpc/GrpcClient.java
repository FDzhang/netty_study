package com.fd.grpc;

import com.fd.proto.MyRequest;
import com.fd.proto.MyResponse;
import com.fd.proto.StudentServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

/**
 * @author ：zxq
 * @date ：Created in 2020/10/16 9:35
 */

public class GrpcClient {

    public static void main(String[] args) {
        ManagedChannel managedChannel = ManagedChannelBuilder
                .forAddress("127.0.0.1",8899)
                .usePlaintext().build();
        StudentServiceGrpc.StudentServiceBlockingStub blockingStub = StudentServiceGrpc.newBlockingStub(managedChannel);
        MyResponse myResponse =
                blockingStub.getRealNameByUsername(MyRequest.newBuilder().setUsername("wk").build());
        System.out.println(myResponse.getRealname());
    }
}
