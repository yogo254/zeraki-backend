package com.zeraki.zerakibackend.controler;

import java.util.List;

import com.zeraki.zerakibackend.app.institution.Institution;
import com.zeraki.zerakibackend.app.institution.InstitutionService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin/institution")
public class InstitutionControler {
    @Autowired
    private InstitutionService institutionService;

    @PostMapping
    public Institution addInstitution(@RequestBody Institution institution) {
        return institutionService.addInstitution(institution);
    }

    @GetMapping
    public List<Institution> getAllInstitutions() {
        return institutionService.getAllInstitutions();

    }

    @GetMapping("/sorted")

    public List<Institution> getAllSortedByNameInstitutions() {
        return institutionService.getAllSortedByNameInstitutions();
    }

    @GetMapping("/search")
    public List<Institution> search(@RequestParam String search) {
        return institutionService.search(search);
    }

    @PutMapping
    public Institution editInstitution(@RequestBody Institution institution) {
        return institutionService.editInstitution(institution);
    }

    @DeleteMapping
    public String deleteInstitution(@RequestParam String id) {
        return institutionService.deleteInstitution(id);
    }

}
