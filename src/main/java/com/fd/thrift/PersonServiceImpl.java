package com.fd.thrift;

import org.apache.thrift.TException;
import thrift.generated.DataException;
import thrift.generated.Person;
import thrift.generated.PersonService;

/**
 * @author ：zxq
 * @date ：Created in 2020/10/13 14:02
 */

public class PersonServiceImpl implements PersonService.Iface {


    @Override
    public Person getPersonByUserName(String username) throws DataException, TException {
        System.out.println("get Client Param: " + username);

        Person person = new Person();
        person.setUsername(username);
        person.setAge(23);
        person.setMarried(false);
        return person;
    }

    @Override
    public void savePerson(Person person) throws DataException, TException {
        System.out.println("get Client Param: " );

        System.out.println(person.getUsername());
        System.out.println(person.getAge());
        System.out.println(person.isMarried());
    }
}
