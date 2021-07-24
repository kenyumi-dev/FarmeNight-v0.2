package fr.shadou.farmenight.production.alcool;


import fr.shadou.farmenight.Instas;
import fr.shadou.farmenight.init.ModItem;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.inventory.container.SimpleNamedContainerProvider;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;

public class BarrilBlock extends Block {

    public BarrilBlock(Properties properties) {
        super(properties);
    }

    @Override
    public boolean hasTileEntity(BlockState state) {
        return true;
    }

    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return new FileEntityVin();
    }

    @Override
    public ActionResultType use(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
        ItemStack itemStack = player.getItemInHand(handIn);
        if (worldIn.getBlockEntity(pos) instanceof FileEntityVin) {
            FileEntityVin te = (FileEntityVin) worldIn.getBlockEntity(pos);
            Item item = itemStack.getItem();
            ItemStack itemStack1 = new ItemStack(item.getItem());

            if (player.isShiftKeyDown()){
                player.displayClientMessage(new StringTextComponent("Raisin Stock : " + te.getReserveRAISIN()), true);
                player.sendMessage(new StringTextComponent("Vin Stock : " + te.getLEVEL_VIN()), Util.NIL_UUID);
            }

            if (item == Items.GLASS_BOTTLE & te.getLEVEL_VIN() >= 0 & te.isPercer() == 1) {
                te.sousLEVEL_VIN(1);
                itemStack.shrink(1);
                if (itemStack.isEmpty()) {
                    player.setItemInHand(handIn, Instas.ItemStackCustom(ModItem.BOTTLE_WINE_OPEN));
                    return ActionResultType.SUCCESS;
                } else if (!player.inventory.add(Instas.ItemStackCustom(ModItem.BOTTLE_WINE_OPEN))) {
                    player.drop(Instas.ItemStackCustom(ModItem.BOTTLE_WINE_OPEN), false);
                    return ActionResultType.SUCCESS;
                }
            }

            if (item == Instas.ItemCustom(ModItem.BOUCHON) & te.isPercer() == 1){
                te.falsepercer(0);
                itemStack.shrink(1);
                return ActionResultType.SUCCESS;
            }

            if (item == Instas.ItemCustom(ModItem.PERCEUR) & te.isPercer() == 0){
                te.truepercer(1);
                return ActionResultType.SUCCESS;
            }else if(te.isPercer() == 1){
                return ActionResultType.PASS;
            }

            if (item == Instas.ItemCustom(ModItem.RAISIN) & te.getReserveRAISIN() <= 25){
                te.addReserveRAISIN(1);
                itemStack.shrink(1);
                te.fermentation();
                return ActionResultType.SUCCESS;
            }
            return ActionResultType.PASS;
        }return ActionResultType.FAIL;
    }

}