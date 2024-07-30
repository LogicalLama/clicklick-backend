package server.form.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import server.form.model.FormEntry;

@Repository
public interface FormEntryRepository extends JpaRepository<FormEntry, String> {
}
