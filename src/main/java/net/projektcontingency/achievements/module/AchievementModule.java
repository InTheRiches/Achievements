package net.projektcontingency.achievements.module;

import net.projektcontingency.achievements.achievement.CustomAchievement;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public abstract class AchievementModule {
    abstract public String getName();

    abstract public ItemStack getIcon();

    abstract public List<CustomAchievement> getAchievements();
}
