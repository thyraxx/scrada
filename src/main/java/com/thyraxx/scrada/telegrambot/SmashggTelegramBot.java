package com.thyraxx.scrada.telegrambot;

import com.pengrad.telegrambot.Callback;
import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.request.SendMessage;
import com.pengrad.telegrambot.response.SendResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

import static com.pengrad.telegrambot.model.request.ParseMode.HTML;

public class SmashggTelegramBot {

    private static final Logger logger = LoggerFactory.getLogger(SmashggTelegramBot.class);

    //TODO: Seperate token into config
    private static final TelegramBot smashTelegramBot = new TelegramBot("");

    public static TelegramBot smashTelegramBotInstance() {
        return smashTelegramBot;
    }

    private void registerUpdateListener()
    {
        smashTelegramBot.setUpdatesListener(updates -> {
            return UpdatesListener.CONFIRMED_UPDATES_ALL;
        });
    }

    public static void sendMessage(long chatId, String message)
    {
        // Async call
        SendMessage sendMessage = new SendMessage(chatId, message).parseMode(HTML);
        smashTelegramBot.execute(sendMessage, new Callback<SendMessage, SendResponse>() {
            @Override
            public void onResponse(SendMessage sendMessage, SendResponse sendResponse) {
//                if(sendResponse.isOk())
//                {
                    logger.debug("Send to chatId: " + chatId + "\n"
                    + "with message: \n" + message);
//                }
            }

            @Override
            public void onFailure(SendMessage sendMessage, IOException e) {
                logger.error(e.getMessage());
            }
        });
    }
}
