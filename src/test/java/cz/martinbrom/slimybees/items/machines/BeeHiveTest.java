package cz.martinbrom.slimybees.items.machines;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.inventory.ItemStack;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;

import io.github.thebusybiscuit.slimefun4.implementation.Slimefun;

class BeeHiveTest {

    @Test
    void schedulesLeftoverDropsOnTheServerThread() {
        World world = mock(World.class);
        Location location = mock(Location.class);
        ItemStack original = mock(ItemStack.class);
        ItemStack cloned = mock(ItemStack.class);
        when(original.clone()).thenReturn(cloned);
        List<Runnable> scheduled = new ArrayList<>();

        try (MockedStatic<Slimefun> slimefun = mockStatic(Slimefun.class)) {
            slimefun.when(() -> Slimefun.runSync(any(Runnable.class))).thenAnswer(invocation -> {
                scheduled.add(invocation.getArgument(0));
                return null;
            });

            BeeHive.dropLeftoversSafely(world, location, List.of(original));
        }

        assertEquals(1, scheduled.size());
        scheduled.get(0).run();

        verify(world).dropItemNaturally(location, cloned);
    }
}
