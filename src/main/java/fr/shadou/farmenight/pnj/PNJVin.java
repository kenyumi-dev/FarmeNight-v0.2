package fr.shadou.farmenight.pnj;

import fr.shadou.farmenight.Instas;
import fr.shadou.farmenight.init.ModItem;
import fr.shadou.farmenight.pnj.gui.PNJVinGui;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.ai.attributes.Attribute;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.attributes.ModifiableAttributeInstance;
import net.minecraft.entity.passive.AmbientEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class PNJVin extends AmbientEntity {
    private static final ITextComponent NAME = new TranslationTextComponent("");
    public PNJVin(EntityType<? extends AmbientEntity> p_i48570_1_, World p_i48570_2_) {
        super(p_i48570_1_, p_i48570_2_);
    }

    public static AttributeModifierMap.MutableAttribute createAttributes() {
        return MobEntity.createMobAttributes().add(Attributes.MAX_HEALTH, 10.0D).add(Attributes.MOVEMENT_SPEED, (double)0.0F);
    }

    @Override
    protected ActionResultType mobInteract(PlayerEntity p_230254_1_, Hand p_230254_2_) {
        ItemStack itemstack = p_230254_1_.getItemInHand(p_230254_2_);

        if (itemstack.getItem() == Instas.ItemCustom(ModItem.CARTE)){
            Minecraft.getInstance().setScreen(new PNJVinGui(NAME,p_230254_1_));
            return ActionResultType.SUCCESS;
        }return ActionResultType.FAIL;
    }
}
