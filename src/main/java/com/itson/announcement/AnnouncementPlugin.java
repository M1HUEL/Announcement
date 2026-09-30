package com.itson.announcement;

import com.itson.announcement.command.AnnouncementCommand;
import com.itson.announcement.config.AnnouncementConfig;
import com.itson.announcement.task.AnnouncementTask;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitTask;

public final class AnnouncementPlugin extends JavaPlugin {

  private AnnouncementConfig announcementConfig;
  private BukkitTask announcementTask;

  @Override
  public void onEnable() {
    saveDefaultConfig();
    announcementConfig = new AnnouncementConfig(this);
    getCommand("announcement").setExecutor(new AnnouncementCommand(this));
    startAnnouncementTask();
    getLogger().info("Announcement v" + getPluginMeta().getVersion() + " enabled.");
  }

  @Override
  public void onDisable() {
    if (announcementTask != null) {
      announcementTask.cancel();
    }
    getLogger().info("Announcement disabled.");
  }

  private void startAnnouncementTask() {
    long delay = announcementConfig.getIntervalSeconds() * 20L;
    announcementTask = getServer().getScheduler()
      .runTaskTimer(this, new AnnouncementTask(this), delay, delay);
  }

  public void reloadAnnouncements() {
    if (announcementTask != null) {
      announcementTask.cancel();
      announcementTask = null;
    }
    announcementConfig.reload();
    startAnnouncementTask();
  }

  public AnnouncementConfig getAnnouncementConfig() {
    return announcementConfig;
  }
}
