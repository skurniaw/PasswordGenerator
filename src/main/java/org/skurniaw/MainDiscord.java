package org.skurniaw;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;

public class MainDiscord {

    public static void main(String[] args) {
        try {
            JDA jda = JDABuilder.createDefault("MTAwMDI1NTQwOTY4MjEzMzAyMg.GfxWTw.-J9C1IfT8bmug145XKlUeXiywQiiT_At8u09sY")
                    .addEventListeners(new PassGenListener())
                    .build();
        } catch (Exception e) {
            System.out.println("MainDiscord error: " + e.toString());
        }

    }

}