package modal.payloads;

import modal.pojos.CompaniesPojo;

public class CompanyPayloads {
    public String companyName = "majidalfuttaim";
    public CompaniesPojo createCompany(String ecosystemId){
        return CompaniesPojo
                .builder()
                .MainDomainValue("www.majidalfuttaim.com")
                .CompanyName(companyName)
                .EcosystemId(ecosystemId)
                .build();
    }
}
