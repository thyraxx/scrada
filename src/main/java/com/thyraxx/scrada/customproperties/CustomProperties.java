package com.thyraxx.scrada.customproperties;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class CustomProperties {

    private static final Logger logger = Logger.getLogger(CustomProperties.class);

    private static String smashggApiKey;
    private static Long telegramChatId;
    private static String telegramBotKey;

    public static String getSmashggApiKey()
    {
        logger.debug("getSmashggApiKey: " + smashggApiKey);
        return smashggApiKey;
    }

    public static Long getTelegramChatId()
    {
        logger.debug("getTelegramChatId: " + telegramChatId);
        return telegramChatId;
    }

    public static String getTelegramBotKey()
    {
        logger.debug("getTelegramBotKey: " + telegramBotKey);
        return telegramBotKey;
    }

    @Autowired
    public CustomProperties(
            @Value("${smashggapikey}") String smashggApiKey,
            @Value("${telegram_chat_id}") Long telegramChatId,
            @Value("${telegram_bot_key}") String telegramBotKey)
    {
        CustomProperties.smashggApiKey = smashggApiKey;
        CustomProperties.telegramChatId = telegramChatId;
        CustomProperties.telegramBotKey = telegramBotKey;
    }
}
