package modal.payloads;

import modal.pojos.EcosystemPojo;

public class CreateEcosystemPayloads {
    public String ecosystemName = "EcosystemTestName";

    public EcosystemPojo createEcosystem(){
        return EcosystemPojo
                .builder()
                .Name(ecosystemName)
                .build();
    }
}
