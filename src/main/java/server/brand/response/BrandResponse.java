package server.brand.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import server.brand.model.BrandStatus;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BrandResponse {
    private String id;
    private String name;
    private BrandStatus status;
    private String website;
    private String base64logo;
    private String businessId;
}
