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
  private int index;

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
    String message = pickMessage(config.getMode(), messages);
    String text = config.getPrefix() + message;
    text = text.replace("{players}", String.valueOf(plugin.getServer().getOnlinePlayers().size()))
      .replace("{max}", String.valueOf(plugin.getServer().getMaxPlayers()));
    Component component = MINI_MESSAGE.deserialize(text);
    plugin.getServer().broadcast(component);
  }

  private String pickMessage(String mode, List<String> messages) {
    if ("sequential".equalsIgnoreCase(mode)) {
      String message = messages.get(index % messages.size());
      index++;
      return message;
    }
    return messages.get(ThreadLocalRandom.current().nextInt(messages.size()));
  }
}
