package server.form.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import server.form.request.FormRequest;
import server.form.response.FormResponse;
import server.form.request.FormSubmitRequest;
import server.form.model.Form;
import server.form.model.FormEntry;
import server.form.model.FormProperty;
import server.form.response.FormSubmitResponse;
import server.form.service.FormService;
import server.game.model.Game;
import server.game.model.GameSession;
import server.game.service.GameService;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/form")
public class FormController {

        @Autowired
        private FormService formService;

        @Autowired
        private GameService gameService;

        @GetMapping("/{id}")
        public ResponseEntity<FormResponse> getFormById(@PathVariable String id) {
        Optional<Form> formOptional = formService.getFormById(id);
        if (!formOptional.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        List<FormProperty> properties = formService.getFormPropertiesByFormId(id);
        Map<String, FormProperty> propertiesMap = properties.stream()
                .collect(Collectors.toMap(FormProperty::getKey, prop -> prop));

        FormResponse.FormSchema formSchema = FormResponse.FormSchema.builder()
                .properties(propertiesMap)
                .build();

        FormResponse formResponse = FormResponse.builder()
                .id(formOptional.get().getId())
                .name(formOptional.get().getName())
                .gameId(formOptional.get().getGameId())
                .formSchema(formSchema)
                .build();

        return ResponseEntity.ok(formResponse);
    }

        @PostMapping
        public ResponseEntity<FormResponse> createForm(@RequestBody FormRequest formRequest) {
        Form form = Form.builder()
                .id(UUID.randomUUID().toString())
                .name(formRequest.getName())
                .gameId(formRequest.getGameId())
                .build();
        Form createdForm = formService.createForm(form);

        formRequest.getProperties().forEach((key, prop) -> {
            FormProperty formProperty = FormProperty.builder()
                    .formId(createdForm.getId())
                    .key(key)
                    .type(prop.getType())
                    .description(prop.getDescription())
                    .pattern(prop.getPattern())
                    .patternError(prop.getPatternError())
                    .isRequired(prop.isRequired())
                    .build();
            formService.createFormProperty(formProperty);
        });

        return getFormById(createdForm.getId());
    }

        @PostMapping("/submit")
        public ResponseEntity<FormSubmitResponse> submitForm(@RequestBody FormSubmitRequest formSubmitRequest) {
        String formId = formSubmitRequest.getFormId();
        String entry = formSubmitRequest.getResponse().toString(); // Convert the response map to a string

        FormEntry formEntry = FormEntry.builder()
                .id(UUID.randomUUID().toString())
                .formId(formId)
                .entry(entry)
                .build();

        formService.createFormEntry(formEntry);

        // Fetch the form to get the associated gameId
        Optional<Form> formOptional = formService.getFormById(formId);
        if (!formOptional.isPresent()) {
            return ResponseEntity.badRequest().build();
        }
        String gameId = formOptional.get().getGameId();

        // Initiate game session
        GameSession gameSession = gameService.initiateGameSession(gameId, formEntry.getId());

        FormSubmitResponse formSubmitResponse = FormSubmitResponse.builder()
                .gameToken(gameSession.getGameToken())
                .gameId(gameSession.getGameId())
                .build();

        return ResponseEntity.ok(formSubmitResponse);
    }

    @GetMapping("/entry/{id}")
    public ResponseEntity<FormSubmitRequest> getFormEntryById(@PathVariable String id) {
        Optional<FormEntry> formEntryOptional = formService.getFormEntryById(id);
        if (!formEntryOptional.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        FormEntry formEntry = formEntryOptional.get();
        String formId = formEntry.getFormId();
        Map<String, String> response = parseEntry(formEntry.getEntry());

        FormSubmitRequest formSubmitRequest = FormSubmitRequest.builder()
                .formId(formId)
                .response(response)
                .build();

        return ResponseEntity.ok(formSubmitRequest);
    }

    private Map<String, String> parseEntry(String entry) {
        // Implement your logic to parse the entry string back to a map
        // Assuming the entry is in a simple key=value format for this example
        return Arrays.stream(entry.substring(1, entry.length() - 1).split(", "))
                .map(s -> s.split("="))
                .collect(Collectors.toMap(a -> a[0], a -> a[1]));
    }
}

//package server.form.controller;
//
//import io.micrometer.core.annotation.Timed;
//import jakarta.validation.Valid;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//import server.form.response.FormResponse;
//import server.form.model.FormProperty;
//import server.form.request.FormSubmitRequest;
//import server.form.response.FormSubmitResponse;
//
//import java.util.HashMap;
//import java.util.Map;
//
//@RestController
//@RequestMapping("/form")
//@Slf4j
//@Timed
//public class FormController {
//
//    @RequestMapping("")
//    public ResponseEntity<FormResponse> getContent(@RequestParam String id) {
//        if(!id.equals("21b3b904-f7e6-4f4e-9b94-bcdc5f72ad39")) {
//            throw new RuntimeException("form not found");
//        }
//
//        Map<String, FormProperty> properties = new HashMap<>();
//        FormProperty nameProperty = FormProperty.builder()
//                .type("string")
//                .description("user’s name")
//                .isRequired(true)
//                .build();
//
//        FormProperty dobProperty = FormProperty.builder()
//                .type("string")
//                .description("user’s date of birth")
//                .pattern("^(0?[1-9]|[12][0-9]|3[01])[\\/\\-](0?[1-9]|1[012])[\\/\\-]\\d{4}$")
//                .patternError("Enter date needs in format dd/MM/yyyy, like 22/11/2011.")
//                .isRequired(false)
//                .build();
//
//        FormProperty mobileProperty = FormProperty.builder()
//                .type("string")
//                .description("user’s phone number")
//                .pattern("^[+]{1}(?:[0-9\\-\\(\\)\\/\\.]\\s?){6,15}[0-9]{1}$")
//                .patternError("Enter mobile number with country code, like +918282828888.")
//                .isRequired(true)
//                .build();
//
//        properties.put("name", nameProperty);
//        properties.put("dob", dobProperty);
//        properties.put("mobileNumber", mobileProperty);
//
//        FormResponse.FormSchema formSchema = FormResponse.FormSchema.builder()
//                .properties(properties)
//                .build();
//
//        FormResponse formResponse = FormResponse.builder()
//                .id(id)
//                .name("Lead form")
//                .formSchema(formSchema)
//                .build();
//        return ResponseEntity.ok(formResponse);
//    }
//
//    @PostMapping("/submit")
//    public ResponseEntity<FormSubmitResponse> submitForm(@Valid @RequestBody FormSubmitRequest request) {
//        // Perform your logic for processing the form submission here
//        // For this example, we will return a dummy response
//
//        FormSubmitResponse response = FormSubmitResponse.builder()
//                .gameToken("4d00c056-68cc-4222-a952-ff3712bcd331")
//                .gameId("6a9c7d01-e717-432a-8856-e8bb7de4eaa1")
//                .build();
//
//        return ResponseEntity.ok(response);
//    }
//}
