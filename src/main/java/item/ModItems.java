package item;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroupEntries;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.lowsketch.reworkedshieldsmod.ReworkedShieldsMod;
import net.lowsketch.reworkedshieldsmod.item.ModShieldItem;
import net.minecraft.item.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModItems {
    //This creates the items using the function what we created called registerItem

    //332
    public static final Item NETHERITE_SHIELD = registerItem("netherite_shield",
            new ShieldItem(new Item.Settings().maxDamage(1079)));
    public static final Item DIAMOND_SHIELD = registerItem("diamond_shield",
            new ShieldItem(new Item.Settings().maxDamage(664)));
    public static final Item GOLD_SHIELD = registerItem("gold_shield",
            new ShieldItem(new Item.Settings().maxDamage(83)));
    public static final Item WOODEN_SHIELD = registerItem("wooden_shield",
            new ShieldItem(new Item.Settings().maxDamage(166)));

    //Returns an Item, using the java registry(important identifier stuff)
    private static Item registerItem(String name, Item item){
        return Registry.register(Registries.ITEM, Identifier.of(ReworkedShieldsMod.MOD_ID, name), item);
    }

    public static void registerModItems(){ //Called on initial events (TutorialMod.onInitialize)
        ReworkedShieldsMod.LOGGER.info("Registering Mod Items for " + ReworkedShieldsMod.MOD_ID);

        //ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register(ModItems::addItemsToIngredientItemGroup);
    }



}
