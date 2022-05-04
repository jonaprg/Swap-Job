package tk.swapjob.repository;


import org.springframework.data.repository.CrudRepository;
import tk.swapjob.model.Company;

public interface CompanyRepository extends CrudRepository<Company, Long> {
    Company findCompanyByEmail(String email);
}
