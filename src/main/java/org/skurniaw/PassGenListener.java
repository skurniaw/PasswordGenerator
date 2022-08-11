package org.skurniaw;

import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class PassGenListener extends ListenerAdapter {

    private static final String DISCORD_COMMAND_TOKEN = "!";

    private GreeterDiscord greeter;

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {

        Message msg = event.getMessage();
        String msgString = msg.getContentRaw();
        MessageChannel msgChannel = event.getChannel();

        if (!msgString.startsWith(DISCORD_COMMAND_TOKEN)) {
            System.out.println("command entered that didn't start with " + DISCORD_COMMAND_TOKEN);
            return;
        }

        msgString = msgString.substring(1, msgString.length());
        System.out.println("trimmed string: " + msgString);

        if (msgString.equalsIgnoreCase("passgen")) {
            this.greeter = new GreeterDiscord();
            System.out.println("New Password Generator instantiated!");
            msgChannel.sendMessage(greeter.getResponse()).queue();
        } else {
            if (greeter != null) {
                greeter.takeCommand(msgString);
                System.out.println(greeter.getResponse() + "\n \n");
                try {
                    msgChannel.sendMessage(greeter.getResponse()).queue();
                } catch (IllegalArgumentException e) {
                    msgChannel.sendMessage("``` Message was over 2000 characters, restarting... ```").queue();
                }

            }
        }


    }
}
