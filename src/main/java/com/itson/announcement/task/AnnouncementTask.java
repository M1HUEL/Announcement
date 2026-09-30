package com.itson.announcement.task;

import com.itson.announcement.AnnouncementPlugin;
import com.itson.announcement.config.AnnouncementConfig;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;

public final class AnnouncementTask implements Runnable {

  private static final MiniMessage MINI_MESSAGE = MiniMessage.miniMessage();

  private final AnnouncementPlugin plugin;

  public AnnouncementTask(AnnouncementPlugin plugin) {
    this.plugin = plugin;
  }

  @Override
  public void run() {
    AnnouncementConfig config = plugin.getAnnouncementConfig();
    List<String> messages = config.getMessages().stream()
      .filter(message -> !message.isBlank())
      .toList();
    if (messages.isEmpty()) {
      return;
    }
    String message = messages.get(ThreadLocalRandom.current().nextInt(messages.size()));
    Component component = MINI_MESSAGE.deserialize(config.getPrefix() + message);
    plugin.getServer().broadcast(component);
  }
}
