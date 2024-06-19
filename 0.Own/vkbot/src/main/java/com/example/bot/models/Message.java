package com.example.bot.models;

import java.util.List;

public class Message {
    private int date;
    private int from_id;
    private int id;
    private int out;
    private int peer_id;
    private String text;
    private int conversation_message_id;
    private List<Object> fwd_messages;
    private boolean important;
    private int random_id;
    private List<Object> attachments;
    private boolean is_hidden;

    public int getDate() {
        return date;
    }

    public void setDate(int date) {
        this.date = date;
    }

    public int getFrom_id() {
        return from_id;
    }

    public void setFrom_id(int from_id) {
        this.from_id = from_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getOut() {
        return out;
    }

    public void setOut(int out) {
        this.out = out;
    }

    public int getPeer_id() {
        return peer_id;
    }

    public void setPeer_id(int peer_id) {
        this.peer_id = peer_id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getConversation_message_id() {
        return conversation_message_id;
    }

    public void setConversation_message_id(int conversation_message_id) {
        this.conversation_message_id = conversation_message_id;
    }

    public List<Object> getFwd_messages() {
        return fwd_messages;
    }

    public void setFwd_messages(List<Object> fwd_messages) {
        this.fwd_messages = fwd_messages;
    }

    public boolean isImportant() {
        return important;
    }

    public void setImportant(boolean important) {
        this.important = important;
    }

    public int getRandom_id() {
        return random_id;
    }

    public void setRandom_id(int random_id) {
        this.random_id = random_id;
    }

    public List<Object> getAttachments() {
        return attachments;
    }

    public void setAttachments(List<Object> attachments) {
        this.attachments = attachments;
    }

    public boolean isIs_hidden() {
        return is_hidden;
    }

    public void setIs_hidden(boolean is_hidden) {
        this.is_hidden = is_hidden;
    }
}
