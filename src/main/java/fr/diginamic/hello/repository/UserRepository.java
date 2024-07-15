package fr.diginamic.hello.repository;

import org.springframework.data.repository.CrudRepository;

import fr.diginamic.hello.entities.UserAccount;

public interface UserRepository extends CrudRepository<UserAccount, Integer>{
	UserAccount findByUserName(String userName);
}
