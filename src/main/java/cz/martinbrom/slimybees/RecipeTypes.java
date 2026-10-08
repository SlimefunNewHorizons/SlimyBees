package cz.martinbrom.slimybees;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.libraries.dough.items.CustomItemStack;

/**
 * This class holds a static reference to every {@link RecipeType} found in SlimyBees.
 */
public class RecipeTypes {

    // prevent instantiation
    private RecipeTypes() {}

    public static final RecipeType WILDERNESS = new RecipeType(
            SlimyBeesPlugin.getKey("wilderness"),
            new ItemStack(Material.SPRUCE_SAPLING));

    public static final RecipeType BREEDING = new RecipeType(
            SlimyBeesPlugin.getKey("breeding"),
            new CustomItemStack(Material.BEE_SPAWN_EGG,
                    "&cCría",
                    "",
                    "&fdentro de la colmena",
                    "&fCruza dos abejas concretas",
                    "&fSe obtiene"));

    public static final RecipeType BEE_PRODUCT = new RecipeType(
            SlimyBeesPlugin.getKey("bee_product"),
            new CustomItemStack(Material.HONEYCOMB,
                    "&cProducto de la abeja",
                    "",
                    "&fLas abejas trabajan dentro de la colmena",
                    "&fLo que produce"));

    public static final RecipeType SBEE_CENTRIFUGE = new RecipeType(
            SlimyBeesPlugin.getKey("sbee_centrifuge"),
            new CustomItemStack(Material.GRINDSTONE,
                    "&fCentrifugadora",
                    "",
                    "&7Se obtiene metiéndolo en la centrifugadora"));

}
