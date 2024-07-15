package fr.diginamic.hello.controleurs;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import fr.diginamic.hello.entities.Ville;
import fr.diginamic.hello.service.VilleService;

@Controller
public class TownViewController {
	@Autowired
	VilleService villeService;
	
	@GetMapping("/townList")
	public String getTownList(Model model) {
		List<Ville> villes = villeService.getVilles();
		
		model.addAttribute("towns",villes);
		return "town/townList";
	}
	@GetMapping("/deleteTown/{id}")
	public String getTownList(@PathVariable int id) {
		villeService.deleteVille(id);
		return "redirect:/townList";
	}

}
