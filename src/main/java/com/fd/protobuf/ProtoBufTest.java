package com.fd.protobuf;

/**
 * @author ：zxq
 * @date ：Created in 2020/10/12 15:01
 */

public class ProtoBufTest {
    public static void main(String[] args) throws Exception {
        DataInfo.Student student = DataInfo.Student.newBuilder()
                .setName("悟空")
                .setAge(500)
                .setAddress("花果山")
                .build();
        byte[] student2ByteArray = student.toByteArray();

        DataInfo.Student student2 = DataInfo.Student.parseFrom(student2ByteArray);

        System.out.println(student2.toString());
        System.out.println(student2.getName());
        System.out.println(student2.getAge());
        System.out.println(student2.getAddress());
    }
}
