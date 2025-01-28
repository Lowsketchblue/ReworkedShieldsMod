package item;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
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
    public static final Item RUBY = registerItem("ruby", new Item(new FabricItemSettings()));

    public static final Item NETHERITE_SHIELD = registerItem("netherite_shield",
            new ShieldItem(new FabricItemSettings().maxDamage(10)));

    //Returns an Item, using the java registry(important identifier stuff)
    private static Item registerItem(String name, Item item){
        return Registry.register(Registries.ITEM, new Identifier(ReworkedShieldsMod.MOD_ID, name), item);
    }


    //This two functions are used to add the created items to a Group Entrie
    private static void addItemsToIngredientItemGroup(FabricItemGroupEntries entries){
        entries.add(RUBY);
    }
    public static void registerModItems(){ //Called on initial events (TutorialMod.onInitialize)
        ReworkedShieldsMod.LOGGER.info("Registering Mod Items for " + ReworkedShieldsMod.MOD_ID);

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register(ModItems::addItemsToIngredientItemGroup);
    }



}
