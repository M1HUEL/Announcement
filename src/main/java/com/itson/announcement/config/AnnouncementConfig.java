package com.itson.announcement.config;

import java.util.List;
import org.bukkit.plugin.java.JavaPlugin;

public final class AnnouncementConfig {

  private final JavaPlugin plugin;
  private String mode;
  private int intervalSeconds;
  private String prefix;
  private List<String> messages;

  public AnnouncementConfig(JavaPlugin plugin) {
    this.plugin = plugin;
    reload();
  }

  public void reload() {
    plugin.reloadConfig();
    mode = plugin.getConfig().getString("mode", "sequential");
    intervalSeconds = Math.max(1, plugin.getConfig().getInt("interval-seconds", 60));
    prefix = plugin.getConfig().getString("prefix", "");
    messages = plugin.getConfig().getStringList("messages");
  }

  public String getMode() {
    return mode;
  }

  public int getIntervalSeconds() {
    return intervalSeconds;
  }

  public String getPrefix() {
    return prefix;
  }

  public List<String> getMessages() {
    return messages;
  }
}
