package ua.artcode.billapp.repository;


import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ua.artcode.billapp.model.Person;

import java.util.List;

@Repository
public interface PersonRepository extends PagingAndSortingRepository<Person, Long> {

	List<Person> findByLastName(@Param("name") String name);
	List<Person> findByFirstName(@Param("name") String name);

}
