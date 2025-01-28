package item;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.lowsketch.reworkedshieldsmod.ReworkedShieldsMod;
import net.lowsketch.reworkedshieldsmod.item.ModShieldItem;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;


public class ModItemsGroups {
    public static final ItemGroup RUBY_GROUP = Registry.register(Registries.ITEM_GROUP,
            new Identifier(ReworkedShieldsMod.MOD_ID, "ruby"),
            FabricItemGroup.builder().displayName(Text.translatable("itemgroup.ruby"))
                .icon(() -> new ItemStack(Items.SHIELD)).entries((displayContext, entries) -> {
                    //From here just add the custom items

                    entries.add(Items.DIAMOND);
                    entries.add(ModItems.NETHERITE_SHIELD);

            }).build());
    public static void registerItemGroups(){
        ReworkedShieldsMod.LOGGER.info("Registering item groups for "+ ReworkedShieldsMod.MOD_ID);
    }
}
