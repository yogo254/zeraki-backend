package com.zeraki.zerakibackend.app.institution;

import java.util.List;

/**
 * InstitutionService
 */
public interface InstitutionService {
    public Institution addInstitution(Institution institution);

    public List<Institution> getAllInstitutions();

    public List<Institution> getAllSortedByNameInstitutions();

    public List<Institution> search(String searchTerm);

    public Institution editInstitution(Institution institution);

    public String deleteInstitution(String id);

}