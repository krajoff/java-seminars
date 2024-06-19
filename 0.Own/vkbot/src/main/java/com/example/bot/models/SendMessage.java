package com.example.bot.models;

public class SendMessage {
    private int peer_id;
    private int random_id;
    private String message;

    public SendMessage(int peer_id, int random_id, String message) {
        this.peer_id = peer_id;
        this.random_id = random_id;
        this.message = message;
    }

    public int getPeer_id() {
        return peer_id;
    }

    public void setPeer_id(int peer_id) {
        this.peer_id = peer_id;
    }

    public int getRandom_id() {
        return random_id;
    }

    public void setRandom_id(int random_id) {
        this.random_id = random_id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}