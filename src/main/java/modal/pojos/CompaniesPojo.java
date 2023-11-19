package modal.pojos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@JsonIgnoreProperties(ignoreUnknown = true)
public class CompaniesPojo {
    public String MainDomainValue;
    public String CompanyName;
    public String EcosystemId;
    public String LicenseType;
    public boolean IsSubsidiary;
    public boolean IsCloudProvider;
    public String CompanyId;
    public String ScanStatus;
    public String DomainName;
    public List<Ecosystems> Ecosystems;
}
