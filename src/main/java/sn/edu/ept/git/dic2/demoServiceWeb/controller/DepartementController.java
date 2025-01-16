package sn.edu.ept.git.dic2.demoServiceWeb.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sn.edu.ept.git.dic2.demoServiceWeb.entities.Departement;
import sn.edu.ept.git.dic2.demoServiceWeb.services.DepartementService;

import java.util.List;

@RestController
@RequestMapping("/departements")
public class DepartementController {

    @Autowired
    private DepartementService departementService;

    @GetMapping
    @Operation(
            summary = "Liste des départements",
            description = "Retourne l'ensemble des départements de l'EPT",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            content = @Content(
                                    mediaType = "application/json",
                                    examples = {
                                            @ExampleObject("[\n" +
                                                    "  {\n" +
                                                    "    \"code\": \"GEM\",\n" +
                                                    "    \"nom\": \"Genie electromecanique\"\n" +
                                                    "  },\n" +
                                                    "  {\n" +
                                                    "    \"code\": \"GIT\",\n" +
                                                    "    \"nom\": \"Genie Informatique et Telecom\"\n" +
                                                    "  }\n" +
                                                    "]")
                                    }
                            )
                    )
            }
    )
    public List<Departement> getDepartements() {
        return departementService.getAllDepartements();
    }

    @PostMapping
    public ResponseEntity createDepartement(@RequestBody Departement departement) {
        if(departement.getCode() == null){
            return ResponseEntity.status(451).body("Absence de code département");
        }
        if(departement.getCode().isBlank()){
            return ResponseEntity.status(452).body("le code ne doit pas etre vide");
        }
        if(departement.getNom() == null){
            return ResponseEntity.status(453).body("le nom du departement est absent");
        }
        if (departement.getNom().isBlank()) {
            return ResponseEntity.status(454).body("le nom du departement ne doit pas etre vide");
        }

        Departement tmp = departementService.getDepartementById(departement.getCode());
        if(tmp != null){
            return ResponseEntity.status(455).body("Un département avec le meme code existe deja");
        }

        tmp = departementService.findDepartementByNom(departement.getNom());
        if(tmp != null){
            return ResponseEntity.status(456).body("Un departement du meme nom existe");
        }
        departementService.createDepartement(departement);
        return ResponseEntity.status(201).body(departement);
//        return ResponseEntity.created().body(departement);

    }

}

