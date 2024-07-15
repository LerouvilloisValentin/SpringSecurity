package fr.diginamic.hello.service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Service;

import fr.diginamic.hello.dao.VilleDao;
import fr.diginamic.hello.dto.VilleDto;
import fr.diginamic.hello.entities.Ville;
import fr.diginamic.hello.repository.VilleRepository;
import jakarta.transaction.Transactional;

@Service
public class VilleService {

    @Autowired
    private VilleDao villeDao;

    @Autowired
    private VilleRepository villeRepository;

    @Autowired
    private DTOService dtoService;

    
    /**
     * Couche avec le Dao
     * @param nom
     * @return
     */
    public List<VilleDto> extractVilleByName(String nom) {
        return villeRepository.findByNom(nom).stream()
                .map(dtoService::convertToVilleDTO)
                .collect(Collectors.toList());
    }

    public List<VilleDto> updateVille(int idVille, VilleDto villeUpdated) {
        Ville ville = dtoService.convertToVilleEntity(villeUpdated);
        return villeDao.updateVille(idVille, ville).stream()
                .map(dtoService::convertToVilleDTO)
                .collect(Collectors.toList());
    }

    public void deleteVille(int id) {
       villeRepository.deleteById(id);
    }
    
//    public List<VilleDto> deleteVille(int idVille) {
//        return villeDao.deleteVille(idVille).stream()
//                .map(dtoService::convertToVilleDTO)
//                .collect(Collectors.toList());
//    }

    public List<VilleDto> findByNomStartingWith(String nom) {
        return villeRepository.findByNomStartingWith(nom).stream()
                .map(dtoService::convertToVilleDTO)
                .collect(Collectors.toList());
    }

    public VilleDto getVille(int idVille) {
        Ville ville = villeRepository.findById(idVille).orElse(null);
        return dtoService.convertToVilleDTO(ville);
    }

    @Transactional
    public Ville insertVille(Ville ville) {
//        Ville ville = dtoService.convertToVilleEntity(villeDto);
    	return  villeRepository.save(ville);
//        return dtoService.convertToVilleDTO(savedVille);
    }
//    @EnableScheduling
    public List<Ville> getVilles() {
        return (List<Ville>) villeRepository.findAll();
  
    }

//    public List<VilleDto> popSuperieurA(int min) {
//        return villeRepository.findByPopulationGreaterThan(min).stream()
//                .map(dtoService::convertToVilleDTO)
//                .collect(Collectors.toList());
//    }

    public List<VilleDto> popEntreMinMax(int min, int max) {
        return villeRepository.findByPopulationBetween(min, max).stream()
                .map(dtoService::convertToVilleDTO)
                .collect(Collectors.toList());
    }
}
