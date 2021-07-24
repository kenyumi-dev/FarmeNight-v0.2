package fr.shadou.farmenight.production.arme;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;
import fr.shadou.farmenight.Main;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;

public class TableContainerScreen extends ContainerScreen<TableContainer> {

    private static final ResourceLocation TEST_CONTAINER_GUI_TEXTURES = new ResourceLocation(Main.MODID, "textures/gui/container/table_container.png");

    public TableContainerScreen(TableContainer p_i51105_1_, PlayerInventory p_i51105_2_, ITextComponent p_i51105_3_) {
        super(p_i51105_1_, p_i51105_2_, p_i51105_3_);
    }

    @Override
    public void render(MatrixStack p_230430_1_, int p_230430_2_, int p_230430_3_, float p_230430_4_) {
        this.renderBg(p_230430_1_,p_230430_4_,p_230430_2_,p_230430_3_);
        super.render(p_230430_1_, p_230430_2_, p_230430_3_, p_230430_4_);
    }

    @Override
    protected void renderBg(MatrixStack p_230450_1_, float p_230450_2_, int mouseX, int mouseY) {
        this.renderBackground(p_230450_1_);
        this.drawGuiContainerBackgroundLayer(p_230450_1_,p_230450_2_,mouseX,mouseY);
        this.renderTooltip(p_230450_1_, mouseX, mouseY);
    }

    protected void drawGuiContainerBackgroundLayer(MatrixStack matrixStack, float partialTicks, int x, int y) {
        RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.minecraft.getTextureManager().bind(TEST_CONTAINER_GUI_TEXTURES);
        int i = (this.width - this.imageWidth) / 2;
        int j = (this.height - this.imageHeight) / 2;
        this.blit(matrixStack, i, j, 0, 0, this.imageWidth, this.imageHeight);

    }

}
