package com.thyraxx.scrada.model;

import org.springframework.lang.NonNull;

import javax.persistence.*;
import java.util.Map;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NonNull
    private String username;
    private String password;

    @ElementCollection
    private Map<String, String> api_info;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Map<String, String> getApi_info() {
        return api_info;
    }

    public void setApi_info(Map<String, String> api_info) {
        this.api_info = api_info;
    }
}
