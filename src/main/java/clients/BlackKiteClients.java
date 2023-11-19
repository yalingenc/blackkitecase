package clients;

import io.restassured.response.Response;
import modal.payloads.AuthPayloads;

import static io.restassured.RestAssured.given;
public class BlackKiteClients extends BaseClient{

    public AuthPayloads authPayloads = new AuthPayloads();
    public Response response;

    public <T> Response postOperations(String basePath, T classType ){
        response =
                given(requestSpecification)
                        .basePath(basePath)
                        .body(classType)
                        .when()
                        .post()
                        .then()
                        .extract().response();
        return response;
    }

    public <T> Response postOperations(String basePath, T classType, String bearerToken ){
        response =
                given(requestSpecification)
                        .basePath(basePath)
                        .headers(
                                "Authorization",
                                "Bearer " + bearerToken)
                        .body(classType)
                        .when()
                        .post()
                        .then()
                        .extract().response();
        return response;
    }

    public Response getOperations(String basePath,String bearerToken){
        response =
                given(requestSpecification)
                        .basePath(basePath)
                        .headers(
                                "Authorization",
                                "Bearer " + bearerToken)
                        .when()
                        .get()
                        .then()
                        .extract().response();
        return response;
    }

    public Response getOperations(String basePath,String bearerToken, String parameterName, String parameter){
        response =
                given(requestSpecification)
                        .basePath(basePath)
                        .headers(
                                "Authorization",
                                "Bearer " + bearerToken)
                        .param(parameterName,parameter)
                        .when()
                        .get()
                        .then()
                        .extract().response();
        return response;
    }

    public Response deleteOperations(String basePath,String bearerToken, String firstParameterName, String firstParameter, String secondParameterName, String secondParameter){
        response =
                given(requestSpecification)
                        .basePath(basePath+"/"+firstParameter)
                        .headers(
                                "Authorization",
                                "Bearer " + bearerToken)
                        .param(secondParameterName,secondParameter)
                        .when()
                        .delete()
                        .then()
                        .extract().response();
        return response;
    }

    public Response deleteOperations(String basePath,String bearerToken, String parameterName, String parameter){
        response =
                given(requestSpecification)
                        .basePath(basePath+"/"+parameter)
                        .headers(
                                "Authorization",
                                "Bearer " + bearerToken)
                        .when()
                        .delete()
                        .then()
                        .extract().response();
        return response;
    }

}
