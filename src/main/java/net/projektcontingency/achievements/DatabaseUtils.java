package net.projektcontingency.achievements;

import net.projektcontingency.achievements.achievement.CustomAchievement;
import net.projektcontingency.titanium.Titanium;
import net.projektcontingency.titanium.internal.Pair;
import org.bson.Document;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class DatabaseUtils {

    public ArrayList<CustomAchievement> getAchievements(Player p) {
        ArrayList<CustomAchievement> achievements = new ArrayList<>();

        List<CustomAchievement> as = Achievements.getInstance().getAchievementHandler().getAchievements();

        for (String str : (List<String>) Titanium.getInstance().getDatabase().getFirstDocumentValue(new Pair<>("id", p.getUniqueId()), "main", "achievements"))
            achievements.addAll(as.stream().filter(a -> a.getId().equals(str)).toList());

        return achievements;
    }
}
