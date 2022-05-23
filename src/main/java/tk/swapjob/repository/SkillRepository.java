package tk.swapjob.repository;

import org.springframework.data.repository.CrudRepository;
import tk.swapjob.model.Skill;

import java.util.List;

public interface SkillRepository extends CrudRepository<Skill, Long> {
    List<Skill> findAll();

    Skill findById(long id);
}
