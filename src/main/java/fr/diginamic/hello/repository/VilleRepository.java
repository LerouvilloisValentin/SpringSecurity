package fr.diginamic.hello.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import fr.diginamic.hello.dto.VilleDto;
import fr.diginamic.hello.entities.Ville;

@Repository
public interface VilleRepository extends CrudRepository<Ville,Integer> {

	List<Ville> findByNomStartingWith(String nom);
	
	List<Ville> findByNom(String nom);

	List<VilleDto> findByPopulationGreaterThan(int min);
	
	List<Ville> findByPopulationBetween(int min, int max);

}
