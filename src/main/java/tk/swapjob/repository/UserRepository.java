package tk.swapjob.repository;

import org.springframework.data.repository.CrudRepository;
import tk.swapjob.model.User;

public interface UserRepository extends CrudRepository<User, Long> {
    User findUserByEmail(String email);
    boolean existsUserByEmail(String email);
}
