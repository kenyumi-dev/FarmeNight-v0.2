package fr.shadou.farmenight;

import fr.shadou.farmenight.init.ModItem;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IItemProvider;
import net.minecraftforge.fml.RegistryObject;

public class Instas {


    public static Item ItemCustom(RegistryObject<Item> o){
        ItemStack a = new ItemStack(o.get());
        return a.getItem();
    }
    public  static  ItemStack ItemStackCustom(RegistryObject<Item> o){
        return new ItemStack(o.get());
    }

    public static Item ItemCustomBlock(RegistryObject<Block> o){
        ItemStack a = new ItemStack(o.get());
        return a.getItem();
    }
    public  static  ItemStack ItemStackCustomBlock(RegistryObject<Block> o){
        return new ItemStack(o.get());
    }

}
