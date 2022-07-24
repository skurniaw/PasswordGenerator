package org.skurniaw;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;

public class MainDiscord {

    public static void main(String[] args) {
        try {
            JSONObject cfg = (JSONObject) new JSONParser().parse(new FileReader("cfg.json"));

            JDA jda = JDABuilder.createDefault((String) cfg.get("botToken"))
                    .addEventListeners(new PassGenListener())
                    .build();
        } catch (Exception e) {
            System.out.println("MainDiscord error: " + e.toString());
        }

    }

}