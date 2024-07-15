package fr.diginamic.hello.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import fr.diginamic.hello.entities.Departement;

public interface DepartementRepository extends CrudRepository<Departement, Integer> {

	Departement findByCodeDepartement(String codeDepartement);
	List<Departement> findBy();
	
	
}
