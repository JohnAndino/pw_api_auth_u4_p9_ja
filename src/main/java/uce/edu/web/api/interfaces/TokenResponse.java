package uce.edu.web.api.interfaces;

public class TokenResponse {
    public String token;
    public long expiresIn;
    public String role;

    public TokenResponse(String token, long expiresIn, String role) {
        this.token = token;
        this.expiresIn = expiresIn;
        this.role = role;
    }

    public String getToken() {
        return token;
    }

    public long getExpiresIn() {
        return expiresIn;
    }

    public String getRole() {
        return role;
    }
}
