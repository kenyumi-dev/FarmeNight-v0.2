package fr.shadou.farmenight.pnj.gui;

import com.mojang.blaze3d.matrix.MatrixStack;
import fr.shadou.farmenight.Main;
import fr.shadou.farmenight.money.capabilite.CapaMoney;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.button.Button;
import net.minecraft.client.gui.widget.button.ImageButton;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;

public class ValidationGuiVent extends Screen {

    private ItemStack itemchoisi = null;
    private int nbtItem = 0;
    private int amount = 0;
    private int price = 0;
    private static PlayerEntity player = null;

    private static int MONEY = 0;
    private static int CLEANITEM = 0;

    private static final int WIDTH = 176;
    private static final int HEIGHT = 67;
    private static final ResourceLocation GUI_TEXTURES = new ResourceLocation(Main.MODID, "textures/gui/interface/validation.png");
    private static final ResourceLocation ADD_TEXTURES = new ResourceLocation(Main.MODID,"textures/gui/boutton/add_button.png");
    private static final ResourceLocation SOUS_TEXTURES = new ResourceLocation(Main.MODID,"textures/gui/boutton/sous_button.png");
    private static final ResourceLocation VALIDE_TEXTURES = new ResourceLocation(Main.MODID, "textures/gui/boutton/valide.png");
    private static final ResourceLocation REFUT_TEXTURES = new ResourceLocation(Main.MODID, "textures/gui/boutton/refut.png");
    private static final ITextComponent NAME = new TranslationTextComponent("");


    protected ValidationGuiVent(ITextComponent p_i51108_1_, ItemStack itemStack, int nbtItem,int amount, int price, PlayerEntity player) {
        super(p_i51108_1_);

        CapaMoney.getMoney(player).ifPresent(money -> this.MONEY = money.getMoney());

        this.nbtItem = nbtItem;
        this.itemchoisi = itemStack;
        this.price = price;
        this.amount = amount;
        this.player = player;

    }

    @Override
    protected void init() {
        int taille = 20;
        ITextComponent textmoney = new TranslationTextComponent("Money:  " + MONEY);


        ITextComponent produittext = new TranslationTextComponent("Produit "+ this.itemchoisi.toString());
        ITextComponent nbt = new TranslationTextComponent(""+this.nbtItem);
        ITextComponent amount = new TranslationTextComponent(""+this.amount);

        int relX = (this.width - WIDTH) / 2;
        int relY = (this.height - HEIGHT) / 2;

        addButton(new Button(relX+140,relY+3,30,10,textmoney, button -> System.out.println("")));
        addButton(new Button(relX+5,relY+12,110,20,produittext,button -> System.out.println(nbtItem)));
        //NbtItem
        addButton(new Button(relX+114,relY+12,26,20,nbt,null));
        //Amount
        addButton(new Button(relX+114,relY+40,58,20,amount,null));

        addButton(new ImageButton(relX+140,relY+12,33,10,0,0,0,ADD_TEXTURES,33,10, button-> {
            addNum();
            createinitnbt();
            reset(relX,relY,taille);
        }));
        addButton(new ImageButton(relX+140,relY+22,33,10,0,0,0,SOUS_TEXTURES,33,10,button-> {
            sousNum();
            createinitnbt();
            reset(relX,relY,taille);
        }));



        addButton(new ImageButton(relX+89,relY+37,taille,taille,0,0,0,REFUT_TEXTURES,taille,taille,button ->{
            Minecraft.getInstance().setScreen(new PNJVinGui(NAME,this.player));
        }));

    }
    public void reset(int relX,int relY,int taille){
        for (int i =0; i<=35; ++i){
            ItemStack iteem = this.player.inventory.getItem(i);
            System.out.println(iteem);
            if (iteem==this.itemchoisi){
                CLEANITEM = i;
                buttonvalide(relX,relY,taille);
            }
        }
    }

    public void addNum(){
        this.nbtItem++;
        this.amount = this.amount+this.price;
    }
    public void sousNum(){
        this.nbtItem--;
        this.amount = this.amount-this.price;
    }

    public void createinitnbt(){
        Minecraft.getInstance().setScreen(new ValidationGuiVent(NAME,this.itemchoisi,this.nbtItem,this.amount,this.price,this.player));
    }

    public void buttonvalide(int relX, int relY, int taille){
        ItemStack iteem = this.player.inventory.getItem(this.CLEANITEM);
        addButton(new ImageButton(relX+26,relY+37,taille,taille,0,0,0,VALIDE_TEXTURES,taille,taille,button ->{
            Minecraft.getInstance().setScreen(new PNJVinGui(NAME,this.player));
            for (int i = 0; i < this.nbtItem;++i){
                this.player.inventory.removeItem(this.CLEANITEM,1);
            }
        }));
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
