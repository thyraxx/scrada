package com.thyraxx.scrada.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Telegram {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String telegramBotKey;

    @ElementCollection
    private List<Long> chatIdList;

    @ManyToOne
    private UserModel userModel;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTelegramBotKey() {
        return telegramBotKey;
    }

    public void setTelegramBotKey(String telegramBotKey) {
        this.telegramBotKey = telegramBotKey;
    }

    public UserModel getUser() {
        return userModel;
    }

    public void setUser(UserModel userModel) {
        this.userModel = userModel;
    }
}
