package me.squid.eoncurrency.commands;

import me.squid.eoncurrency.Eoncurrency;
import me.squid.eoncurrency.managers.EconomyManager;
import me.squid.eoncurrency.menus.EcoMenu;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Objects;

public class BalanceCommand implements CommandExecutor {

    Eoncurrency plugin;
    EcoMenu ecoMenu = new EcoMenu();

    public BalanceCommand(Eoncurrency plugin) {
        this.plugin = plugin;
        Objects.requireNonNull(plugin.getCommand("balance")).setExecutor(this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (sender instanceof Player) {
            Player p = (Player) sender;
            if (args.length == 0) {
                p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_HARP, 1, 1);
                p.openInventory(ecoMenu.Main(p));
            } else if (args.length == 1) {
                Player target = Bukkit.getPlayer(args[0]);
                if (target != null) {
                    p.openInventory(ecoMenu.Others(target));
                    p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_HARP, 1, 1);
                }
            }
        }

        return true;
    }
}
