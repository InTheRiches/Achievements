package net.projektcontingency.achievements;

import net.projektcontingency.achievements.achievement.CustomAchievement;
import net.projektcontingency.achievements.module.AchievementModule;

import java.util.ArrayList;
import java.util.List;

public class AchievementHandler {
    public List<AchievementModule> achievements;

    public AchievementHandler() {
        this.achievements = new ArrayList<>();
    }

    public List<CustomAchievement> getAchievements() {
        List<CustomAchievement> as = new ArrayList<>();
        for (AchievementModule module : achievements) {
            as.addAll(module.getAchievements());
        }
        return as;
    }
}
