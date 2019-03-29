package ru.epam.blog.app.vo;

import java.util.Objects;

public class Token {

    private String accessToken;
    private String typeToken;

    public Token() {
    }

    public Token(String accessToken, String typeToken) {
        this.accessToken = accessToken;
        this.typeToken = typeToken;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getTypeToken() {
        return typeToken;
    }

    public void setTypeToken(String typeToken) {
        this.typeToken = typeToken;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Token)) return false;
        Token token = (Token) o;
        return Objects.equals(accessToken, token.accessToken) &&
                Objects.equals(typeToken, token.typeToken);
    }

    @Override
    public int hashCode() {
        return Objects.hash(accessToken, typeToken);
    }
}
