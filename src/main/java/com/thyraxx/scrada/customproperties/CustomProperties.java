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

    @Autowired
    public CustomProperties(
            @Value("${smashggapikey}") String smashggApiKey,
            @Value("${telegram_chat_id}") Long telegramChatId)
    {
        CustomProperties.smashggApiKey = smashggApiKey;
        CustomProperties.telegramChatId = telegramChatId;
    }
}
