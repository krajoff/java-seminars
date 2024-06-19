package com.example.bot.server;

import com.example.bot.models.Event;
import com.example.bot.models.Message;
import com.example.bot.models.SendMessage;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class VkBotService {
    @Value("${vk.accessToken}")
    private String accessToken;

    private final RestTemplate restTemplate = new RestTemplate();
    private String server;
    private String key;
    private String ts;

    @PostConstruct
    public void init() {
        getLongPollServer();
        startLongPoll();
    }

    private void getLongPollServer() {
        String url = "https://api.vk.com/method/messages.getLongPollServer?access_token=" + accessToken + "&v=5.131";
        LongPollServerResponse response = restTemplate.getForObject(url, LongPollServerResponse.class);
        this.server = response.getServer();
        this.key = response.getKey();
        this.ts = response.getTs();
    }

    @Scheduled(fixedDelay = 25000)
    private void startLongPoll() {
        String url = server + "?act=a_check&key=" + key + "&ts=" + ts + "&wait=25&mode=2&version=3";
        LongPollEvent response = restTemplate.getForObject(url, LongPollEvent.class);
        this.ts = String.valueOf(response.getTs());
        processEvents(response.getUpdates());
    }

    private void processEvents(List<List<Object>> updates) {
        for (List<Object> update : updates) {
            if (update.get(0).equals(4)) {
                int peerId = (int) update.get(3);
                String message = (String) update.get(5);
                if (message.contains("@your_bot_name")) {  // Убедитесь, что имя бота указано правильно
                    String replyText = "Вы сказали: " + message;
                    sendMessage(peerId, replyText);
                }
            }
        }
    }

    private void sendMessage(int peerId, String message) {
        String url = "https://api.vk.com/method/messages.send";
        int randomId = (int) (Math.random() * 100000);

        Map<String, Object> params = new HashMap<>();
        params.put("peer_id", peerId);
        params.put("message", message);
        params.put("random_id", randomId);
        params.put("access_token", accessToken);
        params.put("v", "5.131");

        restTemplate.postForObject(url, params, String.class);
    }
}

