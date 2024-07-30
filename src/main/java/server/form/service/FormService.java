package server.form.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import server.form.model.Form;
import server.form.model.FormEntry;
import server.form.model.FormProperty;
import server.form.repository.FormEntryRepository;
import server.form.repository.FormPropertyRepository;
import server.form.repository.FormRepository;

import java.util.List;
import java.util.Optional;

@Service
public class FormService {

    @Autowired
    private FormRepository formRepository;

    @Autowired
    private FormPropertyRepository formPropertyRepository;

    @Autowired
    private FormEntryRepository formEntryRepository;

    public Optional<Form> getFormById(String id) {
        return formRepository.findById(id);
    }

    public List<FormProperty> getFormPropertiesByFormId(String formId) {
        return formPropertyRepository.findByFormId(formId);
    }

    public Form createForm(Form form) {
        return formRepository.save(form);
    }

    public FormProperty createFormProperty(FormProperty formProperty) {
        return formPropertyRepository.save(formProperty);
    }

    public FormEntry createFormEntry(FormEntry formEntry) {
        return formEntryRepository.save(formEntry);
    }

    public Optional<FormEntry> getFormEntryById(String id) {
        return formEntryRepository.findById(id);
    }
}
