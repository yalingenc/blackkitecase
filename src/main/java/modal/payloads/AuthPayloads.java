package modal.payloads;


import modal.pojos.AuthPojo;

public class AuthPayloads {

    public AuthPojo getAuthData(){
        return AuthPojo
                .builder()
                .grant_type("client_credentials")
                .client_Id("fa7ef14c658a41ae9c11fc895d5e6040")
                .client_secret("5a6c4070bdce4d3090c30931020b8bd7")
                .build();
    }


}
