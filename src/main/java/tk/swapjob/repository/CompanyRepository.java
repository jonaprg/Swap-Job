package tk.swapjob.repository;


import org.springframework.data.repository.CrudRepository;
import tk.swapjob.model.Company;
import tk.swapjob.model.Offer;

import java.util.ArrayList;
import java.util.List;

public interface CompanyRepository extends CrudRepository<Company, Long> {
    Company findCompanyByEmail(String email);
}
