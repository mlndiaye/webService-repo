package sn.edu.ept.git.dic2.demoServiceWeb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sn.edu.ept.git.dic2.demoServiceWeb.entities.Filiaire;
import sn.edu.ept.git.dic2.demoServiceWeb.services.FiliaireService;

import java.util.List;

@RestController
@RequestMapping("/filiaires")
public class FiliaireController {

    @Autowired
    private FiliaireService filiaireService;

    @GetMapping
    public List<FiliaireDTO> getFiliaires() {
        return filiaireService.getAllFiliaire().stream()
                .map(FiliaireDTO::fromFiliaire).toList();
    }

    @GetMapping("/nom/{nom}")
    public Filiaire findByNom(@PathVariable("nom") String nomDpt) {
        return filiaireService.findByNom(nomDpt);
    }
}
