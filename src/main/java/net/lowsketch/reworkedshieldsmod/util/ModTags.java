package net.lowsketch.reworkedshieldsmod.util;

import net.lowsketch.reworkedshieldsmod.ReworkedShieldsMod;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

public class ModTags {
    public static class Items{

        public static final TagKey<Item> IS_SHIELD_ITEM =
                    createTag("is_shield_item");
        private static TagKey<Item> createTag(String name){
            return TagKey.of(RegistryKeys.ITEM, new Identifier(ReworkedShieldsMod.MOD_ID, name));
        }
    }
}
