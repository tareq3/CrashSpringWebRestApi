package com.mti.crash.dao;

import com.mti.crash.model.Person;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository("fakeDao")
public class FakePersonDataAccessService implements PersonDao {

    private static List<Person> persons = new ArrayList<>();

    @Override
    public int insertPerson(UUID id, Person person) {
        persons.add(new Person(id, person.getName()));
        return 1;
    }

    @Override
    public List<Person> selectAllPerson() {
        return persons;
    }

    @Override
    public int deletePersonById(UUID id) {
        Optional<Person> person = selectPersonById(id);
        if (!person.isPresent())
            return 0;
        persons.remove(person.get());
        return 1;
    }

    @Override
    public int updatePersonById(UUID id, Person person) {

        return selectPersonById(id)
                .map( p -> {
                    int indexofPersonToDelete = persons.indexOf(p); //we get the index number of the person that we found using id
                    if (indexofPersonToDelete >= 0) {
                        persons.set(indexofPersonToDelete, new Person(id, person.getName())); //then we update the object of that position with new object preserving the id as for update id should be same.
                        return 1;
                    }
                    return 0; //if we can't find id of that person
                })
                .orElse(0); //if person is not present or null
    }

    @Override
    public Optional<Person> selectPersonById(UUID uuid) {
        return persons.stream()
                .filter(person -> person.getId().equals(uuid))
                .findFirst();
    }
}
