package com.example.bot.controller;

import com.example.bot.models.Event;
import com.example.bot.server.VkBotService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/callback")
public class VkCallbackController {
    private final VkBotService vkBotService;

    @Value("${vk.urlGroup}")
    private String urlGroup;

    public VkCallbackController(VkBotService vkBotService) {
        this.vkBotService = vkBotService;
    }

    @PostMapping
    public String callback(@RequestBody Event event) {
        switch (event.getType()) {
            case "confirmation":
                return urlGroup;
            case "message_new":
                vkBotService.processEvent(event);
                return "ok";
            default:
                return "ok";
        }
    }
}
