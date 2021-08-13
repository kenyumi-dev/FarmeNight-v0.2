package fr.shadou.farmenight.money.phone.appli;

import com.mojang.blaze3d.matrix.MatrixStack;
import fr.shadou.farmenight.Main;
import fr.shadou.farmenight.money.capabilite.CapaMoney;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.TextFieldWidget;
import net.minecraft.client.gui.widget.button.Button;
import net.minecraft.client.gui.widget.button.ImageButton;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;

public class BankGui extends Screen {

    private static PlayerEntity player = null;

    private static final int WIDTH = 134;
    private static final int HEIGHT = 160;
    private static int MONEY = 0;
    private static final ResourceLocation GUI_TEXTURES = new ResourceLocation(Main.MODID, "textures/gui/container/fond_ecran.png");
    private static final ITextComponent NAME = new TranslationTextComponent("Bank");

    public BankGui(ITextComponent name, PlayerEntity player) {
        super(name);
        this.player = player;
    }

    @Override
    protected void init() {

        CapaMoney.getMoney(this.player).ifPresent(money -> MONEY = money.getMoney());
        ITextComponent text = new TranslationTextComponent("Money:  "+MONEY);
        int relX = (this.width - WIDTH - 50) / 2;
        int relY = (this.height - HEIGHT) / 2;

        addButton(new Button(relX + 50, relY + 7, 76,20,text,button -> Minecraft.getInstance().setScreen(new BankGui(NAME,player))));

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
