package com.thyraxx.scrada.customproperties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class CustomProperties {

    private static final Logger logger = Logger.getLogger(CustomProperties.class);
    private static String smashggApiKey;

    public static String getSmashggApiKey()
    {
        return smashggApiKey;
    }

    @Autowired
    public CustomProperties(@Value("${smashggapikey}") String smashggApiKey)
    {
        CustomProperties.smashggApiKey = smashggApiKey;
    }
}
