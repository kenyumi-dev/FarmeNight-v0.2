package fr.shadou.farmenight.init;

import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;

public class ModItemGroup {

    public static final ItemGroup CSMM_TAG = new ItemGroup("cssm_tag") {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(ModItem.CARTE.get());
        }
    };

}
