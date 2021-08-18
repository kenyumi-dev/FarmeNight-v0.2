package fr.shadou.farmenight.pnj.vente;

import fr.shadou.farmenight.Instas;
import fr.shadou.farmenight.init.ModBlock;
import fr.shadou.farmenight.init.ModItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraftforge.fml.RegistryObject;

import java.util.ArrayList;
import java.util.List;

public class Price {

    public static List<List> finalPrice = new ArrayList();

    public static final int Raisin = 5;
    public static final int RaisinVent = 2;

    public static final int Barril = 50;
    public static final int BarrilVent = 20;

    public static final int Bottle = 10;
    public static final int BottleVent = 3;

    public static final int Perseur = 15;
    public static final int PerseurVent = 5;

    public static final int Bouchon = 15;
    public static final int BouchonVent = 5;

    public static List<List> raisin = new ArrayList();
    public static List<List> barril = new ArrayList();
    public static List<List> perseur = new ArrayList();
    public static List<List> bouchon = new ArrayList();
    public static List<List> bottle = new ArrayList();

    private static List<Integer> ListRaisin = new ArrayList();
    private static List<Integer> ListBarril = new ArrayList();
    private static List<Integer> ListPerseur = new ArrayList();
    private static List<Integer> ListBouchon = new ArrayList();
    private static List<Integer> ListBottle = new ArrayList();

    private static List<String> ListRaisinString = new ArrayList();
    private static List<String> ListBarrilString = new ArrayList();
    private static List<String> ListPerseurString = new ArrayList();
    private static List<String> ListBouchonString = new ArrayList();
    private static List<String> ListBottleString = new ArrayList();

    private static List<ItemStack> ListRaisinItem = new ArrayList();
    private static List<ItemStack> ListBarrilItem = new ArrayList();
    private static List<ItemStack> ListPerseurItem = new ArrayList();
    private static List<ItemStack> ListBouchonItem = new ArrayList();
    private static List<ItemStack> ListBottleItem = new ArrayList();

    public void Price(){

        ItemStack botle = new ItemStack(Items.GLASS_BOTTLE.getItem());

        //List Add
        ListRaisin.add(Raisin);
        ListRaisin.add(RaisinVent);
        ListRaisinString.add("Raisin");
        ListRaisinItem.add(Instas.ItemStackCustom(ModItem.RAISIN));

        ListBarril.add(Barril);
        ListBarril.add(BarrilVent);
        ListBarrilString.add("Barril");
        ListBarrilItem.add(Instas.ItemStackCustomBlock(ModBlock.BARRIL_VIN));

        ListBottle.add(Bottle);
        ListBottle.add(BottleVent);
        ListBottleString.add("Bottle");
        ListBottleItem.add(botle);

        ListPerseur.add(Perseur);
        ListPerseur.add(PerseurVent);
        ListPerseurString.add("Perseur");
        ListPerseurItem.add(Instas.ItemStackCustom(ModItem.PERCEUR));

        ListBouchon.add(Bouchon);
        ListBouchon.add(BouchonVent);
        ListBouchonString.add("Bouchon");
        ListBouchonItem.add(Instas.ItemStackCustom(ModItem.BOUCHON));

        //List Final
        raisin.add(ListRaisin);
        raisin.add(ListRaisinString);
        raisin.add(ListRaisinItem);

        barril.add(ListBarril);
        barril.add(ListBarrilString);
        barril.add(ListBarrilItem);

        bottle.add(ListBottle);
        bottle.add(ListBottleString);
        bottle.add(ListBottleItem);

        perseur.add(ListPerseur);
        perseur.add(ListPerseurString);
        perseur.add(ListPerseurItem);

        bouchon.add(ListBouchon);
        bouchon.add(ListBouchonString);
        bouchon.add(ListBouchonItem);

        finalPrice.add(raisin);
        finalPrice.add(barril);
        finalPrice.add(perseur);
        finalPrice.add(bouchon);
        finalPrice.add(bottle);
    }
}
