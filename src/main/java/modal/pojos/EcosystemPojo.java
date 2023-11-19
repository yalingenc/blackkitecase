package modal.pojos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@JsonIgnoreProperties(ignoreUnknown = true)
public class EcosystemPojo {
    public String Name;
    public String EcosystemId;
    public String EcosystemName;
    public String InsertDate;
    public String InsertUser;
}
