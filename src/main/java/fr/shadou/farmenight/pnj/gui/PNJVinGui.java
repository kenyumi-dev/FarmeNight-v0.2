package fr.shadou.farmenight.pnj.gui;

import com.mojang.blaze3d.matrix.MatrixStack;
import fr.shadou.farmenight.Instas;
import fr.shadou.farmenight.Main;
import fr.shadou.farmenight.init.ModBlock;
import fr.shadou.farmenight.init.ModItem;
import fr.shadou.farmenight.money.capabilite.CapaMoney;
import fr.shadou.farmenight.pnj.vente.Price;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.button.Button;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.fml.RegistryObject;

import java.util.ArrayList;
import java.util.List;

public class PNJVinGui extends Screen {

    private static PlayerEntity player = null;

    private static final int WIDTH = 176;
    private static final int HEIGHT = 166;
    private static int MONEY = 0;
    private static final ResourceLocation GUI_TEXTURES = new ResourceLocation(Main.MODID, "textures/gui/interface/gui_pnj.png");
    private static final ITextComponent NAME = new TranslationTextComponent("");
    private static ITextComponent textmoney = new TranslationTextComponent("Money:  "+MONEY);
    private static ITextComponent textvent1 = new TranslationTextComponent("Raisin"+"        "+ Price.Raisin+"$");
    private static ITextComponent texteachat = new TranslationTextComponent("vente");

    public PNJVinGui(ITextComponent p_i51108_1_, PlayerEntity player) {
        super(p_i51108_1_);
        this.player = player;
    }

    @Override
    protected void init() {



        CapaMoney.getMoney(player).ifPresent(money -> MONEY = money.getMoney());


        int relX = (this.width - WIDTH) / 2;
        int relY = (this.height - HEIGHT) / 2;

        //CapaMoney
        addButton(new Button(relX + 4,relY + 4, 120,20,textmoney,null));

        //Vente
        addButton(new Button(relX + 13,relY + 33, 148,20,textvent1,button -> Minecraft.getInstance().setScreen(new ValidationGui(NAME, Instas.ItemStackCustom(ModItem.RAISIN),0,0, Price.Raisin, this.player))));
        newoffre(1,relX,relY, Instas.ItemStackCustomBlock(ModBlock.BARRIL_VIN),Price.Barril, "Barril de Vin",this.player);
        newoffre(2,relX,relY, new ItemStack(Items.GLASS_BOTTLE),Price.Bottle,"Bottle",this.player);
        newoffre(3,relX,relY, Instas.ItemStackCustom(ModItem.PERCEUR),Price.Perseur,"Perceur",this.player);
        newoffre(4,relX,relY,Instas.ItemStackCustom(ModItem.BOUCHON),Price.Bouchon,"Bouchon",this.player);



        addButton(new Button(relX+125,relY+155,45,8,texteachat,button -> Minecraft.getInstance().setScreen(new PNJVinbGuiVent(NAME,player))));
    }

    public void newoffre(int index, int relX, int relY, ItemStack item, int price, String nametexte, PlayerEntity player){
        int yadd = index*20;
        ITextComponent textvent2 = new TranslationTextComponent(""+nametexte+"        "+price+"$");
        addButton(new Button(relX + 13,relY + 33+yadd, 148,20,textvent2,button -> Minecraft.getInstance().setScreen(new ValidationGui(NAME, item,0,0, price, player))));
    }

    public void newOffre(int relX, int relY,PlayerEntity player){
        Price pri = new Price();
        pri.Price();int i=Price.finalPrice.size();
        int j = 0;
        while (j>=5){
            j++;
            List<List> listtotal = new ArrayList();
            listtotal.add(Price.finalPrice.get(j));
            System.out.println(listtotal);
            List<Integer> inter = listtotal.get(0);
            List<String> strin = listtotal.get(1);
            List<ItemStack> itemList = listtotal.get(2);
            System.out.println(inter+"     "+strin+"      "+itemList);
            newoffre(j,relX,relY, itemList.get(0), inter.get(0), strin.get(0), player);
        }
    }


    @Override
    public void render(MatrixStack p_230430_1_, int mouseX, int mouseY, float p_230430_4_) {
        renderBackground(p_230430_1_);//Fond gris
        this.minecraft.getTextureManager().bind(GUI_TEXTURES);
        int relX = (this.width - WIDTH)/2;
        int relY = (this.height - HEIGHT)/2;
        this.blit(p_230430_1_,relX,relY,0,0,WIDTH,HEIGHT);
        super.render(p_230430_1_, mouseX, mouseY, p_230430_4_);
    }

}
