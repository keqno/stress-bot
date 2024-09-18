package dev.sim0n.stressbot.runnable;

import dev.sim0n.stressbot.StressBot;
import dev.sim0n.stressbot.bot.action.ConnectAction;
import dev.sim0n.stressbot.bot.action.DisconnectAction;
import io.netty.channel.ChannelHandlerContext;

import java.util.function.Consumer;

/**
 * Copyright (c) 2024. Keano
 * Use or redistribution of source or file is
 * only permitted if given explicit permission.
 */
public class JoinRunnable implements Runnable {

    private final StressBot app = StressBot.getInstance();
    private int i = 0;

    @Override
    public void run() {
        while (i < app.getBotCount()) {
            try {

                String name = app.getUsernamePrefix() + "_" + i;
                Consumer<ChannelHandlerContext> connectAction = new ConnectAction(app.getAddress(), app.getPort(), name);
                Consumer<ChannelHandlerContext> disconnectAction = new DisconnectAction(name);

                app.getBotController().makeBot(connectAction, disconnectAction, i++);
                Thread.sleep(app.getLoginDelay());

            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}