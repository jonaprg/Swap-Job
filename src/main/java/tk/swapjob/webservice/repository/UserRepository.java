package tk.swapjob.webservice.repository;

import org.springframework.data.repository.CrudRepository;
import tk.swapjob.webservice.model.User;

public interface UserRepository extends CrudRepository<User, Long> {
    User findUserByEmail(String email);
}
