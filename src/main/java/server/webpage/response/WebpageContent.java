package server.webpage.response;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class WebpageContent {
    private String brandName;
    private String brandLogo;
    private String heading;
    private String subheading;
    private String displayImage;
    private List<String> forms;
}