package cz.martinbrom.slimybees.listeners;

import java.util.Locale;
import java.util.Set;
import java.util.function.Consumer;

import javax.annotation.ParametersAreNonnullByDefault;

import org.bukkit.World;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.world.WorldInitEvent;
import org.bukkit.event.world.WorldLoadEvent;

import cz.martinbrom.slimybees.SlimyBeesPlugin;
import cz.martinbrom.slimybees.worldgen.NestPopulator;

/**
 * Registers a {@link NestPopulator} on worlds that did not exist yet when SlimyBees was enabled.
 * <p>
 * DrakesCraft loads the BentoBox game mode worlds (bskyblock_world, aoneblock_world, acid_world
 * and their nether/end counterparts) about a minute after the Slimefun addons are enabled, so the
 * one-shot registration in {@link SlimyBeesPlugin#onEnable()} silently skipped them forever and no
 * wild bee nest could ever generate in those modalities.
 */
@ParametersAreNonnullByDefault
public class PendingNestPopulatorListener implements Listener {

    private final Set<String> pendingWorldNames;
    private final Consumer<World> populatorApplier;

    /**
     * @param plugin            The {@link SlimyBeesPlugin} instance used to register this {@link Listener}
     * @param pendingWorldNames Mutable set of lowercase world names still waiting for a populator
     * @param populatorApplier  Callback that attaches the right populator to a freshly loaded {@link World}
     */
    public PendingNestPopulatorListener(SlimyBeesPlugin plugin, Set<String> pendingWorldNames, Consumer<World> populatorApplier) {
        this.pendingWorldNames = pendingWorldNames;
        this.populatorApplier = populatorApplier;

        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void onWorldInit(WorldInitEvent e) {
        attachIfPending(e.getWorld());
    }

    /**
     * Safety net for worlds that are already initialized by the time the plugin manager reaches us
     * (some world providers create their worlds outside the normal init flow).
     */
    @EventHandler(priority = EventPriority.LOWEST)
    public void onWorldLoad(WorldLoadEvent e) {
        attachIfPending(e.getWorld());
    }

    private void attachIfPending(World world) {
        // remove() is the guard against attaching the same populator twice
        // when both WorldInitEvent and WorldLoadEvent fire for the same world
        if (pendingWorldNames.remove(world.getName().toLowerCase(Locale.ROOT))) {
            populatorApplier.accept(world);
        }
    }

}
