package server.form.controller;

import io.micrometer.core.annotation.Timed;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import server.form.model.Form;
import server.form.model.Property;
import server.form.request.FormSubmitRequest;
import server.form.response.FormSubmitResponse;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/form")
@Slf4j
@Timed
public class FormController {

    @RequestMapping("")
    public ResponseEntity<Form> getContent(@RequestParam String id) {
        if(!id.equals("21b3b904-f7e6-4f4e-9b94-bcdc5f72ad39")) {
            throw new RuntimeException("form not found");
        }

        Map<String, Property> properties = new HashMap<>();
        Property nameProperty = Property.builder()
                .type("string")
                .description("user’s name")
                .isRequired(true)
                .build();

        Property dobProperty = Property.builder()
                .type("string")
                .description("user’s date of birth")
                .pattern("^(0?[1-9]|[12][0-9]|3[01])[\\/\\-](0?[1-9]|1[012])[\\/\\-]\\d{4}$")
                .patternError("Enter date needs in format dd/MM/yyyy, like 22/11/2011.")
                .isRequired(false)
                .build();

        Property mobileProperty = Property.builder()
                .type("string")
                .description("user’s phone number")
                .pattern("^[+]{1}(?:[0-9\\-\\(\\)\\/\\.]\\s?){6, 15}[0-9]{1}$")
                .patternError("Enter mobile number with country code, like +918282828888.")
                .isRequired(true)
                .build();

        properties.put("name", nameProperty);
        properties.put("dob", dobProperty);
        properties.put("mobileNumber", mobileProperty);

        Form.FormSchema formSchema = Form.FormSchema.builder()
                .properties(properties)
                .build();

        Form form = Form.builder()
                .id(id)
                .name("Lead form")
                .formSchema(formSchema)
                .build();
        return ResponseEntity.ok(form);
    }

    @PostMapping("/submit")
    public ResponseEntity<FormSubmitResponse> submitForm(@Valid @RequestBody FormSubmitRequest request) {
        // Perform your logic for processing the form submission here
        // For this example, we will return a dummy response

        FormSubmitResponse response = FormSubmitResponse.builder()
                .gameToken("4d00c056-68cc-4222-a952-ff3712bcd331")
                .gameId("6a9c7d01-e717-432a-8856-e8bb7de4eaa1")
                .build();

        return ResponseEntity.ok(response);
    }
}
