package net.projektcontingency.achievements.achievement;

import net.projektcontingency.achievements.Achievements;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.inventory.ItemStack;

public abstract class Achievement extends CustomAchievement {
    protected long count;

    public Achievement() {
        this.count = 0;
    }

    abstract public int getRequiredAmount();

    public void trigger(Event e, Player p) {
        this.count++;
        if (getRequiredAmount() > this.count) return;

        onComplete(p);
    }

    public void onComplete(Player p) {
        Achievements.getInstance().getPlayerHandler().completeAchievement(p, this);
    }
}
