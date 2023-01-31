package com.thyraxx.scrada.telegrambot;

import com.pengrad.telegrambot.Callback;
import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.request.SendMessage;
import com.pengrad.telegrambot.response.SendResponse;
import com.thyraxx.scrada.customproperties.CustomProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

import static com.pengrad.telegrambot.model.request.ParseMode.HTML;

public class SmashggTelegramBot {

    private static final Logger logger = LoggerFactory.getLogger(SmashggTelegramBot.class);

    private static final TelegramBot smashTelegramBot = new TelegramBot(CustomProperties.getTelegramBotKey());

    public static TelegramBot smashTelegramBotInstance() {
        return smashTelegramBot;
    }

    public static void sendMessage(long chatId, String message)
    {
        // Async call
        // TODO: add check if message is sucecsfully send, otheriwse keep retrying?
        SendMessage sendMessage = new SendMessage(chatId, message).parseMode(HTML);

        logger.debug("Send to chatId: {} %n with message: {} %n", chatId, message);
        smashTelegramBot.execute(sendMessage, new Callback<SendMessage, SendResponse>() {
            @Override
            public void onResponse(SendMessage sendMessage, SendResponse sendResponse) {
            }

            @Override
            public void onFailure(SendMessage sendMessage, IOException e) {
                logger.error(e.getMessage());
            }
        });
    }

    private SmashggTelegramBot() {}
}
