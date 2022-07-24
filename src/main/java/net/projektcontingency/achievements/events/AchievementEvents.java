package net.projektcontingency.achievements.events;

import net.projektcontingency.achievements.Achievements;
import net.projektcontingency.achievements.achievement.Achievement;
import net.projektcontingency.achievements.achievement.CustomAchievement;
import net.projektcontingency.achievements.achievement.Requirement;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.event.inventory.PrepareItemCraftEvent;
import org.bukkit.event.player.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class AchievementEvents implements Listener {
    @EventHandler
    public void onBlockBroken(BlockBreakEvent e) {
        List<CustomAchievement> playerAchievements = Achievements.getInstance().getPlayerHandler().getPossibleAchievements(e.getPlayer());

        playerAchievements.stream().filter(a -> a.getRequirement() == Requirement.BLOCKS_BROKEN && canTrigger(e.getPlayer(), a)).forEach((a) -> a.trigger(e, e.getPlayer()));
    }
    @EventHandler
    public void onBlockPlaced(BlockPlaceEvent e) {
        List<CustomAchievement> achievements = Achievements.getInstance().getPlayerHandler().getPossibleAchievements(e.getPlayer());
        achievements.stream().filter(a -> a.getRequirement() == Requirement.BLOCKS_PLACED && canTrigger(e.getPlayer(), a)).forEach((a) -> a.trigger(e, e.getPlayer()));
    }
    @EventHandler
    public void onContainerOpened(InventoryOpenEvent e) {
        List<CustomAchievement> achievements = Achievements.getInstance().getPlayerHandler().getPossibleAchievements((Player) e.getPlayer());
        achievements.stream().filter(a -> a.getRequirement() == Requirement.CONTAINER_OPENS && canTrigger((Player) e.getPlayer(), a)).forEach((a) -> a.trigger(e, (Player) e.getPlayer()));
    }
    @EventHandler
    public void onContainerClosed(InventoryCloseEvent e) {
        List<CustomAchievement> achievements = Achievements.getInstance().getPlayerHandler().getPossibleAchievements((Player) e.getPlayer());
        achievements.stream().filter(a -> a.getRequirement() == Requirement.CONTAINER_CLOSES && canTrigger((Player) e.getPlayer(), a)).forEach((a) -> a.trigger(e, (Player) e.getPlayer()));
    }
    @EventHandler
    public void onItemInteract(PlayerInteractEvent e) {
        List<CustomAchievement> achievements = Achievements.getInstance().getPlayerHandler().getPossibleAchievements(e.getPlayer());
        achievements.stream().filter(a -> a.getRequirement() == Requirement.ITEM_INTERACTS && canTrigger(e.getPlayer(), a)).forEach((a) -> a.trigger(e, e.getPlayer()));
    }
    @EventHandler
    public void onItemCraft(CraftItemEvent e) {
        List<CustomAchievement> achievements = Achievements.getInstance().getPlayerHandler().getPossibleAchievements((Player) e.getWhoClicked());
        achievements.stream().filter(a -> a.getRequirement() == Requirement.ITEM_CRAFTS && canTrigger((Player) e.getWhoClicked(), a)).forEach((a) -> a.trigger(e, (Player) e.getWhoClicked()));
    }
    @EventHandler
    public void onItemDrop(PlayerDropItemEvent e) {
        List<CustomAchievement> achievements = Achievements.getInstance().getPlayerHandler().getPossibleAchievements(e.getPlayer());
        achievements.stream().filter(a -> a.getRequirement() == Requirement.ITEM_DROPS && canTrigger(e.getPlayer(), a)).forEach((a) -> a.trigger(e, e.getPlayer()));
    }
    @EventHandler
    public void onItemInteract(PlayerPickupItemEvent e) {
        List<CustomAchievement> achievements = Achievements.getInstance().getPlayerHandler().getPossibleAchievements(e.getPlayer());
        achievements.stream().filter(a -> a.getRequirement() == Requirement.ITEM_PICKUPS && canTrigger(e.getPlayer(), a)).forEach((a) -> a.trigger(e, e.getPlayer()));
    }
    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent e) {
        if (!(e.getEntity().getLastDamageCause().getEntity() instanceof Player killer)) {
            List<CustomAchievement> achievements = Achievements.getInstance().getPlayerHandler().getPossibleAchievements(e.getEntity());
            achievements.stream().filter(a -> (a.getRequirement() == Requirement.DEATHS) && canTrigger(e.getEntity(), a)).forEach((a) -> a.trigger(e, e.getEntity()));
            return;
        }

        List<CustomAchievement> achievements = Achievements.getInstance().getPlayerHandler().getPossibleAchievements(killer);
        achievements.stream().filter(a -> (a.getRequirement() == Requirement.PLAYER_KILLS | a.getRequirement() == Requirement.OVERALL_KILLS) && canTrigger(killer, a)).forEach((a) -> a.trigger(e, killer));
    }

    @EventHandler
    public void onEntityDeath(EntityDeathEvent e) {
        if (!(e.getEntity().getLastDamageCause().getEntity() instanceof Player killer)) return;

        List<CustomAchievement> achievements = Achievements.getInstance().getPlayerHandler().getPossibleAchievements(killer);
        achievements.stream().filter(a -> (a.getRequirement() == Requirement.ENTITY_KILLS | a.getRequirement() == Requirement.OVERALL_KILLS) && canTrigger(killer, a)).forEach(a -> a.trigger(e, killer));
    }
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {
        Achievements.getInstance().getPlayerHandler().playerSetup(e.getPlayer());

        List<CustomAchievement> achievements = Achievements.getInstance().getPlayerHandler().getPossibleAchievements(e.getPlayer());
        achievements.stream().filter(a -> a.getRequirement() == Requirement.JOINS && canTrigger(e.getPlayer(), a)).forEach((a) -> a.trigger(e, e.getPlayer()));
    }

    @EventHandler
    public void onPlayerLeave(PlayerQuitEvent e) {
        List<CustomAchievement> achievements = Achievements.getInstance().getPlayerHandler().getPossibleAchievements(e.getPlayer());
        achievements.stream().filter(a -> (a.getRequirement() == Requirement.LEAVES) && canTrigger(e.getPlayer(), a)).forEach(a -> a.trigger(e, e.getPlayer()));
    }

    private boolean canTrigger(Player p, CustomAchievement a) {
        if (a.hasPrerequisite()) {
            return (Achievements.getInstance().getPlayerHandler().getAchievements(p).contains(a.getPrerequisite()));
        }
        return true;
    }
}
