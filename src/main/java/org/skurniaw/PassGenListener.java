package org.skurniaw;

import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class PassGenListener extends ListenerAdapter {

    private static final String DISCORD_COMMAND_TOKEN = "?";

    private GreeterDiscord greeter;

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        System.out.println("onMessageReceived triggered!");
        Message msg = event.getMessage();
        String msgString = msg.getContentRaw();
        System.out.println("msgString: " + msgString);
        MessageChannel msgChannel = event.getChannel();

        if (!msgString.startsWith(DISCORD_COMMAND_TOKEN)) {
            System.out.println("command entered that didn't start with ?");
            return;
        }

        msgString = msgString.substring(1, msgString.length() - 1);

        if (msgString.equalsIgnoreCase("passgen")) {
            this.greeter = new GreeterDiscord();
        } else {
            greeter.takeCommand(msgString);
        }


    }
}
