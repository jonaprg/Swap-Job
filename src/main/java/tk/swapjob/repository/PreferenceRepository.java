package tk.swapjob.repository;

import org.springframework.data.repository.CrudRepository;
import tk.swapjob.model.Preference;

import java.util.List;

public interface PreferenceRepository  extends CrudRepository<Preference, Long> {
    List<Preference> findAll();
}
