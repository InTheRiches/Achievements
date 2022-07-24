package net.projektcontingency.achievements.achievement;

import net.projektcontingency.achievements.Achievements;
import net.projektcontingency.achievements.module.AchievementModule;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.inventory.ItemStack;

public abstract class CustomAchievement {
    abstract public String getName();

    abstract public AchievementModule getModule();

    abstract public String getId();

    abstract public String[] getDescription();

    abstract public ItemStack getItem();

    abstract public boolean isHiddenAchievement();

    abstract public Requirement getRequirement();

    /**
     * This method gets called every time a player
     * does the task needed to reach the requirement.
     */
    abstract public void trigger(Event e, Player p);

    /**
     * This is called when the player completes
     * the requirement
     */
    abstract public void onComplete(Player p);

    abstract public CustomAchievement getPrerequisite();

    public boolean hasPrerequisite() {
        return getPrerequisite() == null;
    }
}
