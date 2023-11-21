package stepDefinitions;

import clients.BlackKiteClients;
import io.cucumber.core.internal.com.fasterxml.jackson.core.JsonProcessingException;
import io.cucumber.core.internal.com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import modal.payloads.AuthPayloads;
import modal.payloads.CompanyPayloads;
import modal.payloads.CompanySearchPayloads;
import modal.payloads.CreateEcosystemPayloads;
import modal.pojos.AuthPojo;
import modal.pojos.CompaniesPojo;
import modal.pojos.CompanySearchPojo;
import modal.pojos.EcosystemPojo;
import org.awaitility.Awaitility;
import org.junit.Assert;
import java.io.IOException;
import java.util.Collections;
import java.util.concurrent.TimeUnit;

public class test {
    BlackKiteClients blackKiteClients;
    AuthPayloads authPayloads;
    AuthPojo authPojo;
    EcosystemPojo ecosystemPojo;
    CreateEcosystemPayloads createEcosystemPayloads;
    CompanyPayloads companyPayloads;
    CompaniesPojo companiesPojo;
    CompaniesPojo[] companies;
    EcosystemPojo[] ecosystems;
    CompanySearchPojo companySearchPojo;
    CompanySearchPayloads companySearchPayloads;
    Response response;
    String jsonList;
    boolean isEqual;
    ObjectMapper objectMapper = new ObjectMapper();
    public test(){
        blackKiteClients = new BlackKiteClients();
        authPayloads = new AuthPayloads();
        authPojo = new AuthPojo();
        ecosystemPojo = new EcosystemPojo();
        createEcosystemPayloads = new CreateEcosystemPayloads();
        companyPayloads = new CompanyPayloads();
        companiesPojo = new CompaniesPojo();
        companySearchPayloads = new CompanySearchPayloads();
        companySearchPojo = new CompanySearchPojo();
    }

    @Given("User should gets token")
    public void user_should_gets_token() {
        authPojo = blackKiteClients.postOperations(blackKiteClients.prop.getProperty("basePathForAuth"),authPayloads.getAuthData()).getBody().as(AuthPojo.class);
        Assert.assertNotNull(authPojo.getAccess_token());
    }
    @Given("Authentication should be established")
    public void authentication_should_be_established() {
        Assert.assertNotNull(authPojo.getAccess_token());
    }

    @And("User verifies that ecosystem should not created before")
    public void user_verifies_that_ecosystem_should_not_created_before() throws JsonProcessingException {
        response = blackKiteClients.getOperations(blackKiteClients.prop.getProperty("basePathForEcosystems"),authPojo.getAccess_token(),"name",createEcosystemPayloads.ecosystemName);
        jsonList = response.asPrettyString();
        ecosystems = objectMapper.readValue(jsonList, EcosystemPojo[].class);
        Assert.assertEquals(0,ecosystems.length);
    }

    @When("User send post request to the api")
    public void user_send_post_request_to_the_api() {
        response = blackKiteClients.postOperations(blackKiteClients.prop.getProperty("basePathForEcosystems"),createEcosystemPayloads.createEcosystem(),authPojo.getAccess_token());
        Assert.assertEquals(200,response.getStatusCode());
        ecosystemPojo = response.getBody().as(EcosystemPojo.class);
    }

    @Then("User verifies that ecosystem should be created")
    public void user_verifies_that_ecosystem_should_be_created() throws IOException {
        response = blackKiteClients.getOperations(blackKiteClients.prop.getProperty("basePathForEcosystems"),authPojo.getAccess_token(),"id",ecosystemPojo.getEcosystemId());
        Assert.assertEquals(200,response.getStatusCode());
        jsonList = response.asPrettyString();
        ecosystems = objectMapper.readValue(jsonList, EcosystemPojo[].class);
        isEqual = false;
        for (EcosystemPojo m :  ecosystems) {
            if (m.EcosystemId.equals(ecosystemPojo.EcosystemId));
            {
                isEqual=true;
            }
        }
        Assert.assertTrue(isEqual);
    }

    @And("User send post request to the api for creating company")
    public void userSendPostRequestToTheApiForCreatingCompany() {
        response = blackKiteClients.postOperations(blackKiteClients.prop.getProperty("basePathForCompanies"),companyPayloads.createCompany(ecosystemPojo.getEcosystemId()),authPojo.getAccess_token());
        companiesPojo = response.getBody().as(CompaniesPojo.class);
    }

    @And("User verifies that company should be created")
    public void userVerifiesThatCompanyShouldBeCreated() {
        Assert.assertEquals("majidalfuttaim.com",companiesPojo.getMainDomainValue());
    }

    @And("User Searches the company which is created")
    public void userSearchesTheCompanyWhichIsCreated() {
        response = blackKiteClients.postOperations(blackKiteClients.prop.getProperty("basePathForCompaniesSearch"),companySearchPayloads.searchCompany(Collections.singletonList(ecosystemPojo.getEcosystemId())),authPojo.getAccess_token());
        companySearchPojo = response.getBody().as(CompanySearchPojo.class);
    }

    @And("User should gets the company info")
    public void userShouldGetsTheCompanyInfo() throws JsonProcessingException {
        Awaitility.await().pollInterval(5, TimeUnit.SECONDS).atLeast(5,TimeUnit.SECONDS).atMost(320,TimeUnit.SECONDS).untilAsserted(()->{
            response = blackKiteClients.getOperations(blackKiteClients.prop.getProperty("basePathForCompanies"),authPojo.getAccess_token(),"key",companySearchPojo.getKey());
            jsonList = response.asPrettyString();
            companies = objectMapper.readValue(jsonList, CompaniesPojo[].class);
            isEqual = false;
            for (CompaniesPojo m :  companies) {
                if (m.getScanStatus().equals("Extended Rescan Results Ready"));
                {
                    isEqual=true;
                    Assert.assertEquals(companiesPojo.CompanyId,m.getCompanyId());
                    Assert.assertEquals(companyPayloads.companyName,m.getCompanyName());
                    Assert.assertEquals(companiesPojo.MainDomainValue,m.getDomainName());
                    Assert.assertEquals(ecosystemPojo.getEcosystemId(),m.Ecosystems.get(0).EcosystemId);
                    Assert.assertEquals(createEcosystemPayloads.ecosystemName,m.Ecosystems.get(0).EcosystemName);
                }
            }
            Assert.assertTrue(isEqual);
        });


    }

    @And("User deletes company")
    public void userDeletesCompany() {
        response = blackKiteClients.deleteOperations(blackKiteClients.prop.getProperty("basePathForCompanies"),authPojo.getAccess_token(),"",companiesPojo.getCompanyId(),"EcosystemId",ecosystemPojo.getEcosystemId());
        Assert.assertEquals("OK",response.asPrettyString().replace("\"", ""));
    }

    @And("User deletes ecosystem")
    public void userDeletesEcosystem() {
        response = blackKiteClients.deleteOperations(blackKiteClients.prop.getProperty("basePathForEcosystems"),authPojo.getAccess_token(),"id ",ecosystemPojo.EcosystemId);
        Assert.assertEquals("OK",response.asPrettyString().replace("\"", ""));
        Assert.assertEquals("OK",response.asPrettyString().replace("\"", ""));
    }

    @And("User verifies company is deleted")
    public void userVerifiesCompanyIsDeleted() throws JsonProcessingException {
        response = blackKiteClients.getOperations(blackKiteClients.prop.getProperty("basePathForCompanies"),authPojo.getAccess_token(),"key",companySearchPojo.getKey());
        jsonList = response.asPrettyString();
        companies = objectMapper.readValue(jsonList, CompaniesPojo[].class);
        Assert.assertEquals(0,companies.length);
    }
}
