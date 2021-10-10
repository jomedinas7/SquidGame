package dev._2lstudios.squidgame;

import java.util.logging.Level;

import dev._2lstudios.jelly.JellyPlugin;
import dev._2lstudios.jelly.config.Configuration;
import dev._2lstudios.squidgame.arena.ArenaManager;
import dev._2lstudios.squidgame.commands.SquidGameCommand;
import dev._2lstudios.squidgame.listeners.PlayerInteractListener;
import dev._2lstudios.squidgame.player.PlayerManager;

public class SquidGame extends JellyPlugin {

    private ArenaManager arenaManger;
    private PlayerManager playerManager;

    @Override
    public void onEnable() {
        // Save current plugin instance as static instance
        SquidGame.instance = this;

        // Instantiate managers
        this.arenaManger = new ArenaManager(this);
        this.playerManager = new PlayerManager();

        // Register commands
        this.addCommand(new SquidGameCommand());

        // Register listeners
        this.addEventListener(new PlayerInteractListener(this));

        // Register player manager
        this.setPluginPlayerManager(this.playerManager);

        // Enable inventory API
        this.useInventoryAPI();

        // Generate config files
        this.getMainConfig();

        // Banner
        this.getLogger().log(Level.INFO, "§7§m===================================================");
        this.getLogger().log(Level.INFO,
                "                §d&lSquid§f§lGame§r §a(v" + this.getDescription().getVersion() + ")");
        this.getLogger().log(Level.INFO, "§r");
        this.getLogger().log(Level.INFO, "§7- §dArena loaded: §7" + this.arenaManger.getArenas().size());
        this.getLogger().log(Level.INFO, "§r");
        this.getLogger().log(Level.INFO, "§7§m===================================================");

    }

    /* Configuration */
    public Configuration getMainConfig() {
        return this.getConfig("config.yml");
    }

    /* Managers */
    public ArenaManager getArenaManager() {
        return this.arenaManger;
    }

    public PlayerManager getPlayerManager() {
        return this.playerManager;
    }

    /* Static instance */
    private static SquidGame instance;

    public static SquidGame getInstance() {
        return instance;
    }
}