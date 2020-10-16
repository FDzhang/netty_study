package com.fd.grpc;

import com.fd.proto.*;
import io.grpc.stub.StreamObserver;

import java.util.UUID;

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

    @Override
    public void getStudentsByAge(StudentRequest request, StreamObserver<StudentResponse> responseObserver) {
        System.out.println("接收到客户端信息： " + request.getAge());

        StudentResponse studentResponse = StudentResponse.newBuilder()
                .setName("悟空")
                .setAge(23)
                .setCity("花果山")
                .build();
        StudentResponse studentResponse2 = StudentResponse.newBuilder()
                .setName("悟空2")
                .setAge(24)
                .setCity("花果山")
                .build();

        StudentResponse studentResponse3 = StudentResponse.newBuilder()
                .setName("悟空3")
                .setAge(25)
                .setCity("花果山")
                .build();


        responseObserver.onNext(studentResponse);
        responseObserver.onNext(studentResponse2);
        responseObserver.onNext(studentResponse3);

        responseObserver.onCompleted();
    }

    @Override
    public StreamObserver<StudentRequest> getStudentsWrapperByAge(StreamObserver<StudentResponseList> responseObserver) {

        return new StreamObserver<StudentRequest>() {
            @Override
            public void onNext(StudentRequest value) {
                System.out.println("onNext: " + value.getAge());
            }

            @Override
            public void onError(Throwable t) {
                System.out.println(t.getMessage());
            }

            @Override
            public void onCompleted() {
                StudentResponse studentResponse = StudentResponse.newBuilder()
                        .setName("悟空")
                        .setAge(23)
                        .setCity("花果山")
                        .build();
                StudentResponse studentResponse2 = StudentResponse.newBuilder()
                        .setName("悟空2")
                        .setAge(24)
                        .setCity("花果山")
                        .build();

                StudentResponse studentResponse3 = StudentResponse.newBuilder()
                        .setName("悟空3")
                        .setAge(25)
                        .setCity("花果山")
                        .build();

                StudentResponseList studentResponseList = StudentResponseList.newBuilder()
                        .addStudentResponse(studentResponse)
                        .addStudentResponse(studentResponse2)
                        .addStudentResponse(studentResponse3)
                        .build();

                responseObserver.onNext(studentResponseList);
                responseObserver.onCompleted();
            }
        };
    }

    @Override
    public StreamObserver<StreamRequest> biTalk(StreamObserver<StreamResponse> responseObserver) {

        return new StreamObserver<StreamRequest>() {
            @Override
            public void onNext(StreamRequest value) {
                System.out.println(value.getRequestInfo());

                responseObserver.onNext(StreamResponse.newBuilder().setResponseInfo(UUID.randomUUID().toString()).build());
            }

            @Override
            public void onError(Throwable t) {
                System.out.println(t.getMessage());
            }

            @Override
            public void onCompleted() {
                responseObserver.onCompleted();
            }
        };
    }
}
