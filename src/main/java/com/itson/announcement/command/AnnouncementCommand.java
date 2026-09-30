package com.itson.announcement.command;

import com.itson.announcement.AnnouncementPlugin;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

public final class AnnouncementCommand implements CommandExecutor {

  private static final MiniMessage MINI_MESSAGE = MiniMessage.miniMessage();

  private final AnnouncementPlugin plugin;

  public AnnouncementCommand(AnnouncementPlugin plugin) {
    this.plugin = plugin;
  }

  @Override
  public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
    if (args.length != 1 || !args[0].equalsIgnoreCase("reload")) {
      sender.sendMessage(MINI_MESSAGE.deserialize("<red>Usage: /announcement reload"));
      return true;
    }
    plugin.reloadAnnouncements();
    sender.sendMessage(MINI_MESSAGE.deserialize("<green>Announcement configuration reloaded."));
    return true;
  }
}
