package tk.swapjob.repository;

import org.springframework.data.repository.CrudRepository;
import tk.swapjob.model.Skill;

import java.util.List;
import java.util.Optional;

public interface SkillRepository extends CrudRepository<Skill, Long> {
    List<Skill> findAll();

    Optional<Skill> findById(Integer id);
}
