package net.projektcontingency.achievements;

import net.projektcontingency.achievements.events.AchievementEvents;
import org.bukkit.plugin.java.JavaPlugin;

public final class Achievements extends JavaPlugin {

    private static Achievements instance;
    private AchievementHandler achievementHandler;
    private PlayerHandler playerHandler;
    private DatabaseUtils database;

    @Override
    public void onEnable() {
        // Plugin startup logic
        instance = this;

        initialize();
        registerEvents();
    }

    private void initialize() {
        this.achievementHandler = new AchievementHandler();
        this.playerHandler = new PlayerHandler();
        this.database = new DatabaseUtils();
    }

    private void registerEvents() {
        this.getServer().getPluginManager().registerEvents(new AchievementEvents(), this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public static Achievements getInstance() {
        return instance;
    }

    public AchievementHandler getAchievementHandler() {
        return achievementHandler;
    }

    public PlayerHandler getPlayerHandler() {
        return playerHandler;
    }

    public DatabaseUtils getDatabase() {
        return database;
    }
}
