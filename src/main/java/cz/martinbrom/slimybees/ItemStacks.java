package cz.martinbrom.slimybees;

import javax.annotation.Nonnull;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import cz.martinbrom.slimybees.utils.SlimyBeesHeadTexture;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.core.attributes.MachineTier;
import io.github.thebusybiscuit.slimefun4.core.attributes.MachineType;
import io.github.thebusybiscuit.slimefun4.libraries.dough.items.CustomItemStack;
import io.github.thebusybiscuit.slimefun4.utils.LoreBuilder;

/**
 * This class holds a static reference to every {@link SlimefunItemStack} found in SlimyBees.
 */
public class ItemStacks {

    // prevent instantiation
    private ItemStacks() {}

    // <editor-fold desc="Bee Products" defaultstate="collapsed">
    public static final SlimefunItemStack BEESWAX = new SlimefunItemStack(
            "BEESWAX",
            Material.GLOWSTONE_DUST,
            "&eAceite de cera");
    public static final SlimefunItemStack HONEY_DROP = new SlimefunItemStack(
            "HONEY_DROP",
            Material.GOLD_NUGGET,
            "&6Herencia de la abeja");

    public static final SlimefunItemStack HONEY_COMB = createHoneycomb("HONEY", "&6Miel");
    public static final SlimefunItemStack DRY_COMB = createHoneycomb("DRY", "&eSeco");
    public static final SlimefunItemStack SWEET_COMB = createHoneycomb("SWEET", "&fDulce");
    // </editor-fold>

    // <editor-fold desc="Specialty Products" defaultstate="collapsed">
    // TODO: 03.06.21 Prevent coloring
    public static final SlimefunItemStack ROYAL_JELLY = new SlimefunItemStack(
            "ROYAL_JELLY",
            Material.LIGHT_GRAY_DYE,
            "&6&lJalea real",
            meta -> meta.addItemFlags(ItemFlag.HIDE_ENCHANTS));
    // TODO: 03.06.21 Prevent placing
    public static final SlimefunItemStack POLLEN = new SlimefunItemStack(
            "POLLEN",
            Material.PUMPKIN_SEEDS,
            "&6&lPolen",
            meta -> meta.addItemFlags(ItemFlag.HIDE_ENCHANTS));

    static {
        ROYAL_JELLY.addUnsafeEnchantment(Enchantment.MENDING, 1);
        POLLEN.addUnsafeEnchantment(Enchantment.MENDING, 1);
    }
    // </editor-fold>

    // <editor-fold desc="Frames" defaultstate="collapsed">
    public static final SlimefunItemStack BASIC_FRAME = new SlimefunItemStack(
            "BASIC_FRAME",
            Material.BIRCH_SIGN,
            "&fMarco básico",
            "",
            loreProductionModifier(1.25));

    // TODO: 01.07.21 Better name
    public static final SlimefunItemStack ADVANCED_FRAME = new SlimefunItemStack(
            "ADVANCED_FRAME",
            Material.BIRCH_SIGN,
            "&fMarco avanzado",
            "",
            loreProductionModifier(2));

    public static final SlimefunItemStack SWEET_FRAME = new SlimefunItemStack(
            "SWEET_FRAME",
            Material.DARK_OAK_SIGN,
            "&4Marco dulce",
            "",
            loreLifespanModifier(0.5));

    public static final SlimefunItemStack DEADLY_FRAME = new SlimefunItemStack(
            "DEADLY_FRAME",
            Material.DARK_OAK_SIGN,
            "&4Marco letal",
            "",
            loreLifespanModifier(0.1));
    // </editor-fold>

    // <editor-fold desc="Machines" defaultstate="collapsed">
    public static final SlimefunItemStack HIVE_CASING_PLANK = new SlimefunItemStack(
            "HIVE_CASING_PLANK",
            Material.SPRUCE_SLAB,
            "&6Placa de panal",
            "",
            "&fSe puede montar en un bloque de panal");

    public static final SlimefunItemStack HIVE_CASING = new SlimefunItemStack(
            "HIVE_CASING",
            Material.SPRUCE_PLANKS,
            "&6Carcasa de panal",
            "",
            "&fUna pieza clave de la colmena",
            "&fPara la colmena industrial");

    public static final SlimefunItemStack BEE_HIVE = new SlimefunItemStack(
            "BEE_HIVE",
            Material.OAK_PLANKS,
            "&6Colmena básica",
            "",
            "&fUn hogar sencillo para tus abejas",
            "&f¡Hay que manejarla a mano!",
            "",
            getTieredHiveString(MachineTier.BASIC));

    public static final SlimefunItemStack AUTO_BEE_HIVE = new SlimefunItemStack(
            "AUTO_BEE_HIVE",
            Material.STRIPPED_OAK_LOG,
            "&6Colmena totalmente automática",
            "",
            "&fUna colmena automática",
            "&fPon un zángano y una reina en las casillas de entrada",
            "&fy criará por su cuenta",
            "",
            getTieredHiveString(MachineTier.GOOD));

    public static final SlimefunItemStack INDUSTRIAL_BEE_HIVE = new SlimefunItemStack(
            "INDUSTRIAL_BEE_HIVE",
            Material.STRIPPED_CRIMSON_STEM,
            "&6&lColmena industrial",
            "",
            "&fLa mejor colmena que hay",
            "&fEsta colmena",
            "&fAumenta mucho la producción",
            "",
            getTieredHiveString(MachineTier.END_GAME));

    @Nonnull
    private static String getTieredHiveString(MachineTier basic) {
        return basic + " Hive";
    }

    public static final String EXTRACTS_MATERIALS_FROM_COMBS = "&fExtracts materials from combs";
    public static final SlimefunItemStack SBEE_CENTRIFUGE = new SlimefunItemStack(
            "SBEE_CENTRIFUGE",
            Material.GRINDSTONE,
            "&7Centrifugadora",
            "",
            EXTRACTS_MATERIALS_FROM_COMBS);

    public static final SlimefunItemStack ELECTRIC_CENTRIFUGE = new SlimefunItemStack(
            "ELECTRIC_CENTRIFUGE",
            Material.IRON_BLOCK,
            "&7Centrifugadora eléctrica &eI",
            "",
            EXTRACTS_MATERIALS_FROM_COMBS,
            "",
            LoreBuilder.machine(MachineTier.BASIC, MachineType.MACHINE),
            LoreBuilder.speed(1),
            LoreBuilder.powerPerSecond(12));

    public static final SlimefunItemStack ELECTRIC_CENTRIFUGE_2 = new SlimefunItemStack(
            "ELECTRIC_CENTRIFUGE_2",
            Material.IRON_BLOCK,
            "&7Centrifugadora eléctrica &eII",
            "",
            EXTRACTS_MATERIALS_FROM_COMBS,
            "",
            LoreBuilder.machine(MachineTier.ADVANCED, MachineType.MACHINE),
            LoreBuilder.speed(4),
            LoreBuilder.powerPerSecond(36));
    // </editor-fold>

    // <editor-fold desc="Various" defaultstate="collapsed">
    public static final SlimefunItemStack BEEALYZER = new SlimefunItemStack(
            "BEEALYZER",
            Material.ITEM_FRAME,
            "&7Analizador de abejas",
            "",
            LoreBuilder.powerCharged(0, 50),
            "",
            "&fLa herramienta de confianza del apicultor",
            "&fSirve para identificar las distintas",
            "&fRasgos de la abeja",
            "",
            LoreBuilder.RIGHT_CLICK_TO_USE);

    public static final SlimefunItemStack TOME_OF_DISCOVERY_SHARING = new SlimefunItemStack(
            "TOME_OF_DISCOVERY_SHARING",
            Material.ENCHANTED_BOOK,
            "&6Gran tratado de apicultura",
            loreOwner("None"),
            "",
            "&eClic derecho&7 para vincular tus investigaciones de abejas",
            "",
            "&eClic derecho&7 para heredar todas las investigaciones de quien lo vinculó");

    public static final CustomItemStack CONSULT_BEE_ATLAS_RECIPE_ITEM = new CustomItemStack(
            Material.ENCHANTED_BOOK,
            ChatColor.WHITE + "Catálogo de abejas",
            "",
            ChatColor.GRAY + "Consulta el catálogo de abejas o la wiki",
            ChatColor.GRAY + "para más información");

    public static final ItemStack[] CONSULT_BEE_ATLAS_RECIPE = new ItemStack[] {
            null, null, null,
            null, ItemStacks.CONSULT_BEE_ATLAS_RECIPE_ITEM, null,
            null, null, null };

    public static final CustomItemStack CENTRIFUGE_COMB_RECIPE_ITEM = new CustomItemStack(
            Material.HONEYCOMB,
            ChatColor.YELLOW + "Cualquier cera",
            "",
            ChatColor.GRAY + "Mete cualquier cera en la centrifugadora");

    public static final ItemStack[] CENTRIFUGE_COMB_RECIPE = new ItemStack[] {
            null, null, null,
            null, ItemStacks.CENTRIFUGE_COMB_RECIPE_ITEM, null,
            null, null, null };

    public static final SlimefunItemStack BEE_BREEDING_STACK = new SlimefunItemStack(
            "_RECIPE_BEE",
            SlimyBeesHeadTexture.PRINCESS.getAsItemStack(),
            "&eCualquier zángano + reina");
    public static final SlimefunItemStack BEE_OFFSPRING_STACK = new SlimefunItemStack(
            "_RECIPE_BEE_OFFSPRING",
            Material.HONEYCOMB,
            "&6Cría de abeja");
    public static final SlimefunItemStack BEE_PRODUCT_STACK = new SlimefunItemStack(
            "_RECIPE_BEE_PRODUCT",
            Material.HONEYCOMB,
            "&6Producto de la abeja");
    // </editor-fold>

    public static SlimefunItemStack createDrone(String id, String name, boolean enchanted, String... lore) {
        return createBee(
                id + "_DRONE",
                SlimyBeesHeadTexture.DRONE.getAsItemStack(),
                name + "Zángano",
                enchanted,
                lore);
    }

    public static SlimefunItemStack createPrincess(String id, String name, boolean enchanted, String... lore) {
        return createBee(id + "_PRINCESS",
                SlimyBeesHeadTexture.PRINCESS.getAsItemStack(),
                name + "Reina",
                enchanted,
                lore);
    }

    private static SlimefunItemStack createBee(String id, ItemStack itemStack, String name, boolean enchanted, String... lore) {
        SlimefunItemStack item = new SlimefunItemStack(id, itemStack, name, lore);

        if (enchanted) {
            item.addUnsafeEnchantment(Enchantment.MENDING, 1);

            ItemMeta meta = item.getItemMeta();
            if (meta != null) {
                meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
                item.setItemMeta(meta);
            }
        }

        return item;
    }

    public static SlimefunItemStack createHoneycomb(String id, String name) {
        return new SlimefunItemStack(
                id + "_COMB",
                Material.HONEYCOMB,
                name + "Cera");
    }

    public static String loreProductionModifier(double ratio) {
        return "&7Producción: &8&lx" + String.format("%.1f", ratio);
    }

    public static String loreLifespanModifier(double ratio) {
        return "&7Longevidad: &8&lx" + String.format("%.1f", ratio);
    }

    public static String loreOwner(String owner) {
        return "&7Dueño: &b" + owner;
    }

}
