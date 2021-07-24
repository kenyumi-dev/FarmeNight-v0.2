package fr.shadou.farmenight.init;

import fr.shadou.farmenight.Main;
import fr.shadou.farmenight.production.alcool.BarrilBlock;
import fr.shadou.farmenight.production.arme.Tabledasemblage;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModBlock {

    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, Main.MODID);
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Main.MODID);

    public static final RegistryObject<Block> BARRIL_VIN = register("barril_vin", new BarrilBlock(AbstractBlock.Properties.of(Material.DECORATION, MaterialColor.COLOR_BLACK)));
    public static final RegistryObject<Block> TABLE_D_ASSEMBLAGE = register("table_d_assemblage", new Tabledasemblage(AbstractBlock.Properties.of(Material.METAL, MaterialColor.COLOR_GRAY)));

    public static RegistryObject<Block> register(String id, Block block){

        RegistryObject<Block> BLOCK = BLOCKS.register(id, ()-> block);
        RegistryObject<Item> ITEM = ITEMS.register(id, ()-> new BlockItem(block, (new Item.Properties())));

        return BLOCK;

    }

}
