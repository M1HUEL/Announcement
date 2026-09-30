package com.itson.announcement;

import org.bukkit.plugin.java.JavaPlugin;

public final class AnnouncementPlugin extends JavaPlugin {

  @Override
  public void onEnable() {
    getLogger().info("Announcement v" + getPluginMeta().getVersion() + " enabled.");
  }

  @Override
  public void onDisable() {
    getLogger().info("Announcement disabled.");
  }
}
