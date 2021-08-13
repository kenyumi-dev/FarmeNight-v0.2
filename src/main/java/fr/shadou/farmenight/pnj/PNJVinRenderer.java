package fr.shadou.farmenight.pnj;

import fr.shadou.farmenight.Main;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;

public class PNJVinRenderer extends MobRenderer<PNJVin, PNJVinModel<PNJVin>> {

    public static final ResourceLocation PNJ_TEXTURE = new ResourceLocation(Main.MODID,"textures/entity/pnj/steve.png");

    public PNJVinRenderer(EntityRendererManager p_i50961_1_) {
        super(p_i50961_1_, new PNJVinModel<>(), 0.7F);
    }

    @Override
    public ResourceLocation getTextureLocation(PNJVin p_110775_1_) {
        return PNJ_TEXTURE;
    }
}
