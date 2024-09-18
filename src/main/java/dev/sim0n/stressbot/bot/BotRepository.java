package dev.sim0n.stressbot.bot;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * @author sim0n
 */
@Getter
public class BotRepository {

    private final List<Bot> bots = new ArrayList<>();
    private final Queue<Bot> queuedBots = new ConcurrentLinkedQueue<>();

}
