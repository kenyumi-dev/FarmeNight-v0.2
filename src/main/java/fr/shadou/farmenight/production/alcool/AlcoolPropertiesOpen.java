package fr.shadou.farmenight.production.alcool;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

public class AlcoolPropertiesOpen extends Item {
    public AlcoolPropertiesOpen(Properties properties) {
        super(properties
                .stacksTo(1)
                .food(new Food.Builder().nutrition(3).saturationMod(0.3f).effect(() -> new EffectInstance(Effects.CONFUSION, 20*10,0), 0.8f).build())
        );
    }

    @Override
    public ActionResult<ItemStack> use(World word, PlayerEntity player, Hand hand) {

        return super.use(word, player, hand);
    }

    @Override
    public UseAction getUseAnimation(ItemStack p_77661_1_) {
        return UseAction.DRINK;
    }
}


