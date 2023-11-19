package modal.payloads;

import modal.pojos.AuthPojo;
import modal.pojos.CompanySearchPojo;

import java.util.ArrayList;
import java.util.List;

public class CompanySearchPayloads {
    public static ArrayList<?> dataSets;
    public CompanySearchPojo searchCompany(List<String> ecosystemId){
        return CompanySearchPojo
                .builder()
                .GenericSearchText("Health")
                .EcosystemIds(ecosystemId)
                .IndustryIds(addIndustryIds())
                .CountryCodes(addCountryCodes())
                .TagIds(addTagIds())
                .ProductGroupIds(addProductGroupIds())
                .LicenseTypes(addLicenseTypes())
                .LicenseStatus(addLicenseStatus())
                .GradeLetters(addGradeLetters())
                .ControlCodes(addControlCodes())
                .CveOrCweCodes(addCveOrCweCodes())
                .HasCriticalVuln(false)
                .HasLeakIn90Days(false)
                .HasPoorSsl(false)
                .HasPoorDdos(false)
                .HasPoorSmtp(false)
                .HasPoorDns(false)
                .CwssCvssBiggerThanValue(8)
                .build();
    }


    public ArrayList<Integer> addIndustryIds(){
        dataSets = new ArrayList<Integer>(){
            {
                add(1234);
            }
        };
        return (ArrayList<Integer>) dataSets;
    }

    public ArrayList<String> addCountryCodes(){
        dataSets = new ArrayList<String>(){
            {
                add("US");
            }
        };
        return (ArrayList<String>) dataSets;
    }

    public ArrayList<Integer> addTagIds(){
        dataSets = new ArrayList<Integer>(){
            {
                add(1234);
            }
        };
        return (ArrayList<Integer>) dataSets;
    }

    public ArrayList<Integer> addProductGroupIds(){
        dataSets = new ArrayList<Integer>(){
            {
                add(1234);
            }
        };
        return (ArrayList<Integer>) dataSets;
    }

    public ArrayList<String> addLicenseTypes(){
        dataSets = new ArrayList<String>(){
            {
                add("Not Attached");
            }
        };
        return (ArrayList<String>) dataSets;
    }

    public ArrayList<String> addLicenseStatus(){
        dataSets = new ArrayList<String>(){
            {
                add("Active");
            }
        };
        return (ArrayList<String>) dataSets;
    }

    public ArrayList<String> addGradeLetters(){
        dataSets = new ArrayList<String>(){
            {
                add("Active");
            }
        };
        return (ArrayList<String>) dataSets;
    }
    public ArrayList<String> addControlCodes(){
        dataSets = new ArrayList<String>(){
            {
                add("APPSEC-000");
            }
        };
        return (ArrayList<String>) dataSets;
    }
    public ArrayList<String> addCveOrCweCodes(){
        dataSets = new ArrayList<String>(){
            {
                add("CVE-2022-23589");
            }
        };
        return (ArrayList<String>) dataSets;
    }
}
