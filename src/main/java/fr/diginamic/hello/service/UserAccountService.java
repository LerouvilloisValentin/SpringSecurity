package fr.diginamic.hello.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import fr.diginamic.hello.entities.UserAccount;
import fr.diginamic.hello.entities.Ville;
import fr.diginamic.hello.repository.UserRepository;
import fr.diginamic.hello.repository.VilleRepository;
import jakarta.annotation.PostConstruct;

@Service
public class UserAccountService {
	@Autowired
	UserRepository userRepository;
	@Autowired
	PasswordEncoder passwordEncoder;
	@Autowired
	VilleRepository villeRepository;
	
	@PostConstruct
	public void init() {
		create(new UserAccount("admin3",passwordEncoder.encode("admin3"),"ROLE_ADMIN"));
		create(new UserAccount("user3",passwordEncoder.encode("user3"),"ROLE_USER"));
		Ville paris = new Ville("paris",1020000);
		Ville toulouse = new Ville("toulouse",450000);
		Ville nice = new Ville("nice",355000);
		villeRepository.save(paris);
		villeRepository.save(toulouse);
		villeRepository.save(nice);
	}
	
	public void create(UserAccount user) {
		userRepository.save(user);
	}
}
