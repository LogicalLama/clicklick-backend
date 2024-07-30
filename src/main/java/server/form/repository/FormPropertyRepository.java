package server.form.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import server.form.model.FormProperty;
import server.form.model.FormPropertyId;

import java.util.List;

@Repository
public interface FormPropertyRepository extends JpaRepository<FormProperty, FormPropertyId> {
    List<FormProperty> findByFormId(String formId);
}
