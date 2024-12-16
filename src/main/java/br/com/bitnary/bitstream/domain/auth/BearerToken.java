package br.com.bitnary.bitstream.domain.auth;

public class BearerToken {
    private String type = "Bearer";
    private String token;

    public BearerToken() {
        this.generateToken();
    }

    private void generateToken() {
        this.type = "Bearer";
        this.token = "ABCCDEFDRSDEFG";
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getType() {
        return type;
    }

    public String getToken() {
        return token;
    }
}
