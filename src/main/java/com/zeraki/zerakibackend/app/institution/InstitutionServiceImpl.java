package com.zeraki.zerakibackend.app.institution;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.zeraki.zerakibackend.app.exception.NoSuchElementFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class InstitutionServiceImpl implements InstitutionService {
    @Autowired
    private InstitutionRepo institutionRepo;

    @Override
    public Institution addInstitution(Institution institution) {
        institution.setKeywords(institution.toString());
        return institutionRepo.save(institution);
    }

    @Override
    public List<Institution> getAllInstitutions() {
        return institutionRepo.findAll();
    }

    @Override
    public List<Institution> getAllSortedByNameInstitutions() {
        return institutionRepo.findAll(Sort.by("name"));
    }

    @Override
    public List<Institution> search(String searchTerm) {
        List<Institution> result = new ArrayList<>();
        String[] words = searchTerm.trim().split(" ");
        for (String word : words) {
            result.addAll(institutionRepo.findByKeywordsContainingIgnoreCase(word.trim()));

        }

        return result.stream().distinct().collect(Collectors.toList());

    }

    @Override
    public Institution editInstitution(Institution institution) {
        Optional<Institution> op = institutionRepo.findById(institution.getId());
        if (op.isPresent()) {
            Institution old = op.get();
            old.setName(institution.getName());
            old.setLocation(institution.getLocation());
            old.setKeywords(old.toString());
            return institutionRepo.save(old);

        }

        throw new NoSuchElementFoundException("No institution found.");

    }

    @Override
    public String deleteInstitution(String id) {
        if (institutionRepo.existsById(id)) {
            institutionRepo.deleteById(id);
            return "Institution deleted successfuly";
        }

        throw new NoSuchElementFoundException("No institution found.");
    }

}
