package fr.shadou.farmenight.money.phone;

import com.google.common.collect.Lists;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;
import fr.shadou.farmenight.Main;
import fr.shadou.farmenight.money.phone.appli.BankGui;
import fr.shadou.farmenight.production.arme.TableContainer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.client.gui.widget.button.Button;
import net.minecraft.client.gui.widget.button.ImageButton;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextComponent;
import net.minecraft.util.text.TranslationTextComponent;

import java.util.List;

public class PhoneContainerScreen extends Screen {

    private static PlayerEntity player = null;

    private static final int WIDTH = 134;
    private static final int HEIGHT = 160;
    private static final ITextComponent NAME = new TranslationTextComponent("Bank");
    private static final ResourceLocation bt = new ResourceLocation(Main.MODID, "textures/gui/boutton/button.png");
    private static final ResourceLocation GUI_TEXTURES = new ResourceLocation(Main.MODID, "textures/gui/container/guiphone.png");

    protected PhoneContainerScreen(ITextComponent p_i51108_1_, PlayerEntity player) {
        super(p_i51108_1_);
        this.player = player;
    }

    @Override
    protected void init() {
        int relX = (this.width - WIDTH - 50) / 2;
        int relY = (this.height - HEIGHT) / 2;
        addButton(new ImageButton(relX + 53, relY + 7, 24, 24,0,0, 0,bt,24,24,button -> Minecraft.getInstance().setScreen(new BankGui(NAME,this.player))));

    }

    @Override
    public void render(MatrixStack p_230430_1_, int mouseX, int mouseY, float p_230430_4_) {
        renderBackground(p_230430_1_);//Fond gris
        this.minecraft.getTextureManager().bind(GUI_TEXTURES);
        int relX = (this.width - WIDTH - 50)/2;
        int relY = (this.height - HEIGHT)/2;
        this.blit(p_230430_1_,relX,relY,0,0,WIDTH,HEIGHT);
        super.render(p_230430_1_, mouseX, mouseY, p_230430_4_);
    }
}
