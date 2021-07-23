package fr.shadou.farmenight.production.alcool;

import net.minecraft.item.Food;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.UseAction;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;

public class AlcoolPropertiesClose extends Item {
    public AlcoolPropertiesClose(Item.Properties properties) {
        super(properties
                .stacksTo(1)
                .food(new Food.Builder().nutrition(3).saturationMod(0.3f).effect(() -> new EffectInstance(Effects.CONFUSION, 20*10,0), 0.8f).build())
        );
    }
    @Override
    public UseAction getUseAnimation(ItemStack p_77661_1_) {
        return UseAction.DRINK;
    }
}

