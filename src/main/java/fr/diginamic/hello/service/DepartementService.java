package fr.diginamic.hello.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import fr.diginamic.hello.dto.DepartementDto;
import fr.diginamic.hello.entities.Departement;
import fr.diginamic.hello.entities.Ville;
import fr.diginamic.hello.repository.DepartementRepository;
import jakarta.transaction.Transactional;

@Service
public class DepartementService {
	@Autowired
	private DepartementRepository departementRepository;
    @Autowired
    private DTOService dtoService;
    
    private List<Departement> departementList = new ArrayList<>();

	public List<DepartementDto> getDepartements(){
		return departementRepository.findBy().stream()
                .map(dtoService::convertToDepartementDTO)
                .collect(Collectors.toList());
	}
	/*
	 * insert en bdd les département et les converti en Dto (normalement pour les renvoyés à une route
	 */
	@Transactional
    public DepartementDto insertDepartements(DepartementDto departementDto) {
		Departement departement = dtoService.convertToDepartementEntity(departementDto);
		Departement deptBdd = departementRepository.findByCodeDepartement(departement.getCodeDepartement());
		if(deptBdd==null) {
			Departement saveDepartement = departementRepository.save(departement);
			return dtoService.convertToDepartementDTO(saveDepartement);
		}
		return dtoService.convertToDepartementDTO(deptBdd);
    }
	
	/*
	 * méthode qui récupère un département pour être ensuite utilisé dans la méthode addDepartement
	 */
	@Transactional
    public void handleDepartements(Departement departement) {
        departementList.add(departement);
    }
	
	/**
	 * méthode qui converti une liste de département en départementdto et l'ajoute en bdd 
	 * @return
	 */
	@Transactional
	public List<DepartementDto> addDepartements(){
		  List<Departement> savedDepartements = (List<Departement>) departementRepository.saveAll(departementList);
	        return savedDepartements.stream()
	                .map(dtoService::convertToDepartementDTO)
	                .collect(Collectors.toList());	
	}
	
	public  Departement handleNomDepartement(String codeDepartement) {
		RestTemplate restTemplate = new RestTemplate();
		String url = "https://geo.api.gouv.fr/departements/"+codeDepartement+"?fields=nom,code,codeRegion";
		 return restTemplate.getForObject(url, Departement.class);
	}
}
