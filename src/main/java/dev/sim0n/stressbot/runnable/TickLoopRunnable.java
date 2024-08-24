package dev.sim0n.stressbot.runnable;

import dev.sim0n.stressbot.StressBot;
import dev.sim0n.stressbot.bot.Bot;
import dev.sim0n.stressbot.bot.controller.BotController;
import dev.sim0n.stressbot.util.PacketBuffer;
import lombok.RequiredArgsConstructor;

/**
 * @author sim0n
 * <p>
 * This is a simulated minecraft tick loop
 */
@RequiredArgsConstructor
public class TickLoopRunnable implements Runnable {
    private static final int TPS = 20;
    private static final long SEC_IN_NANO = 1000000000;
    private static final long TICK_TIME = SEC_IN_NANO / TPS;

    private long lastTickTime;

    private final StressBot app = StressBot.getInstance();

    @Override
    public void run() {
        while (true) {
            try {
                BotController<PacketBuffer> botController = this.app.getBotController();
                long now = System.nanoTime();

                if (now - this.lastTickTime >= TICK_TIME && botController != null) {
                    botController.getBots().forEach(Bot::tick);
                    this.lastTickTime = now;
                }

                Thread.sleep(1L);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
