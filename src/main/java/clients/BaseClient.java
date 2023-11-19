package clients;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;


public class BaseClient {
    public RequestSpecification requestSpecification;
    public Properties prop;
    public BaseClient(){
        try {
            prop = new Properties();
            FileInputStream ip = new FileInputStream("src/main/java/config/clientConfigs.properties");
            prop.load(ip);
        }catch (FileNotFoundException e){
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }

        requestSpecification = new RequestSpecBuilder()
                .setBaseUri(prop.getProperty("baseURl"))
                .setContentType("application/json")
                .build();

    }
}
