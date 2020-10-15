package com.fd.grpc;

import com.fd.proto.MyRequest;
import com.fd.proto.MyResponse;
import com.fd.proto.StudentServiceGrpc;
import io.grpc.stub.StreamObserver;

/**
 * @author ：zxq
 * @date ：Created in 2020/10/15 17:45
 */

public class StudentServiceImpl extends StudentServiceGrpc.StudentServiceImplBase {

    @Override
    public void getRealNameByUsername(MyRequest request, StreamObserver<MyResponse> responseObserver) {
        System.out.println("接收到客户端信息： " + request.getUsername());

        MyResponse realname = MyResponse.newBuilder()
                .setRealname("路飞").build();

        responseObserver.onNext(realname);
        responseObserver.onCompleted();
    }
}
