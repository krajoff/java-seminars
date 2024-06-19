package com.example.bot;

import api.longpoll.bots.BotsLongPoll;
import api.longpoll.bots.LongPollBot;
import api.longpoll.bots.exceptions.BotsLongPollException;
import api.longpoll.bots.exceptions.BotsLongPollHttpException;
import api.longpoll.bots.methods.messages.MessagesSend;
import api.longpoll.bots.model.events.messages.MessageNewEvent;
import api.longpoll.bots.model.objects.basic.Message;

public class MyBot extends LongPollBot {
    @Override
    public void onMessageNew(MessageNewEvent messageNewEvent) {

        Message message = messageNewEvent.getMessage();

        if (message.hasText()) {
            String response = "Вы написали: " + message.getText();
            try {
                new MessagesSend(this)
                        .setPeerId(message.getPeerId())
                        .setMessage(response)
                        .execute();
            } catch (BotsLongPollHttpException | BotsLongPollException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public String getAccessToken() {
        return "vk1.a.B1WFpS5isKADqV-CiNyOafTAaXi1dVXxLeKzyWcXMuFaWIdV0x_lS9ncJghUDUiXbhcfCv8Ap7Fx6jE_1JgchKv2Kmnx2Xw5KOn30YpHJtfv4kw0KwvYSrYQR9x4XEwT5ibouXs7XYbD3PbqaTMRKnM2WN7bJkssIsgPzaIi4goOxzkcF7esSZ0gU97dKUXyUA9nH8q-tuMunsO881VGCw";
    }

    @Override
    public int getGroupId() {
        return 226052312;
    }

    public static void main(String[] args) throws BotsLongPollHttpException, BotsLongPollException {
        new BotsLongPoll(new MyBot()).run();
    }
}