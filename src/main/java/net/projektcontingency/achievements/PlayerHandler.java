package net.projektcontingency.achievements;

import net.projektcontingency.achievements.achievement.Achievement;
import net.projektcontingency.achievements.achievement.CustomAchievement;
import org.bukkit.entity.Player;

import java.util.*;

public class PlayerHandler {
    private final Map<UUID, ArrayList<CustomAchievement>> players;

    public PlayerHandler() {
        this.players = new HashMap<>();
    }

    public ArrayList<CustomAchievement> getAchievements(Player player) {
        return this.players.get(player.getUniqueId());
    }

    public List<CustomAchievement> getPossibleAchievements(Player player) {
        List<CustomAchievement> achievements = new ArrayList<>(Achievements.getInstance().getAchievementHandler().getAchievements());

        getAchievements(player).forEach(achievements::remove);

        return achievements;
    }

    public void playerSetup(Player p) {
        if (players.containsKey(p.getUniqueId())) return;

        ArrayList<CustomAchievement> achievements = Achievements.getInstance().getDatabase().getAchievements(p);
        players.put(p.getUniqueId(), achievements);
    }

    public void completeAchievement(Player player, CustomAchievement ca) {
        ArrayList<CustomAchievement> achievements = players.get(player.getUniqueId());
        achievements.remove(ca);
        players.replace(player.getUniqueId(), achievements);
    }
}
