package com.fd.grpc;

import com.fd.proto.*;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;

import java.time.LocalDateTime;
import java.util.Iterator;

/**
 * @author ：zxq
 * @date ：Created in 2020/10/16 9:35
 */

public class GrpcClient {

    public static void main(String[] args) {
        ManagedChannel managedChannel = ManagedChannelBuilder
                .forAddress("127.0.0.1", 8899)
                .usePlaintext().build();
        StudentServiceGrpc.StudentServiceBlockingStub blockingStub = StudentServiceGrpc.newBlockingStub(managedChannel);
        StudentServiceGrpc.StudentServiceStub stub = StudentServiceGrpc.newStub(managedChannel);


        MyResponse myResponse =
                blockingStub.getRealNameByUsername(MyRequest.newBuilder().setUsername("wk").build());
        System.out.println(myResponse.getRealname());
//
//
//        System.out.println("----------------");
//
//        Iterator<StudentResponse> studentsByAge = blockingStub.getStudentsByAge(StudentRequest.newBuilder().setAge(20).build());
//
//        studentsByAge.forEachRemaining(
//                s -> System.out.println(s.getName() + "," + s.getAge() + "," + s.getCity())
//        );
//
//        System.out.println("----------------");

//        StreamObserver<StudentResponseList> streamObserver = new StreamObserver<StudentResponseList>() {
//
//            @Override
//            public void onNext(StudentResponseList value) {
//                value.getStudentResponseList().forEach(
//                        s -> {
//                            System.out.println(s.getName());
//                            System.out.println(s.getAge());
//                            System.out.println(s.getCity());
//                            System.out.println("********");
//                        }
//                );
//            }
//
//            @Override
//            public void onError(Throwable t) {
//                System.out.println(t.getMessage());
//            }
//
//            @Override
//            public void onCompleted() {
//                System.out.println("completed!");
//            }
//        };
//
//        StreamObserver<StudentRequest> studentsWrapperByAge = stub.getStudentsWrapperByAge(streamObserver);
//
//        studentsWrapperByAge.onNext(StudentRequest.newBuilder().setAge(20).build());
//        studentsWrapperByAge.onNext(StudentRequest.newBuilder().setAge(30).build());
//        studentsWrapperByAge.onNext(StudentRequest.newBuilder().setAge(40).build());
//        studentsWrapperByAge.onNext(StudentRequest.newBuilder().setAge(50).build());
//
//        studentsWrapperByAge.onCompleted();
//
//        try {
//            Thread.sleep(50000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//
//
//        StreamObserver<StreamRequest> requestStreamObserver = stub.biTalk(new StreamObserver<StreamResponse>() {
//            @Override
//            public void onNext(StreamResponse value) {
//                System.out.println(value.getResponseInfo());
//            }
//
//            @Override
//            public void onError(Throwable t) {
//                System.out.println(t.getMessage());
//            }
//
//            @Override
//            public void onCompleted() {
//                System.out.println("onCompleted");
//            }
//        });
//
//        for (int i = 0; i < 10; i++) {
//            requestStreamObserver.onNext(
//                    StreamRequest.newBuilder().setRequestInfo(LocalDateTime.now().toString()).build()
//            );
//            try {
//                Thread.sleep(1000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }
//        try {
//            Thread.sleep(50000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
    }
}
