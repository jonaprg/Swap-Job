package tk.swapjob.webservice.repository;


import org.springframework.data.repository.CrudRepository;
import tk.swapjob.webservice.model.Company;

public interface CompanyRepository extends CrudRepository<Company, Long> {
    Company findCompanyByEmail(String email);
}
