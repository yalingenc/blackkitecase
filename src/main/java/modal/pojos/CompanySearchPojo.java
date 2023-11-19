package modal.pojos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@JsonIgnoreProperties(ignoreUnknown = true)
public class CompanySearchPojo {
    public String GenericSearchText;
    public List<String> EcosystemIds;
    public List<Integer> IndustryIds;
    public List<String> CountryCodes;
    public List<Integer> TagIds;
    public List<Integer> ProductGroupIds;
    public List<String> LicenseTypes;
    public List<String> LicenseStatus;
    public List<String> GradeLetters;
    public List<String> ControlCodes;
    public List<String> CveOrCweCodes;
    public boolean HasCriticalVuln;
    public boolean HasLeakIn90Days;
    public boolean HasPoorSsl;
    public boolean HasPoorDdos;
    public boolean HasPoorSmtp;
    public boolean HasPoorDns;
    public int CwssCvssBiggerThanValue;
    public String Key;
}
