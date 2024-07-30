package server.form.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import server.form.model.Form;

@Repository
public interface FormRepository extends JpaRepository<Form, String> {
}
