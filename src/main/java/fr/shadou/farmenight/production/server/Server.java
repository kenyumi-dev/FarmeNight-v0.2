package fr.shadou.farmenight.production.server;

import fr.shadou.farmenight.Gestion;
import fr.shadou.farmenight.money.capabilite.CapaMoney;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.Util;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;


public class Server extends Block {
    public Server(Properties p_i48440_1_) {
        super(p_i48440_1_);
    }

    @Override
    public boolean hasTileEntity(BlockState state) {
        return true;
    }

    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return new TileEntityServer();
    }

    @Override
    public ActionResultType use(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand hand, BlockRayTraceResult hit) {
        if (worldIn.getBlockEntity(pos) instanceof TileEntityServer){
            TileEntityServer tes =(TileEntityServer) worldIn.getBlockEntity(pos);
            if (tes.getInit()==1){
                if (player.isShiftKeyDown()){
                    player.displayClientMessage(new StringTextComponent("Facture: " + tes.getFacture()),true);
                    return ActionResultType.SUCCESS;
                }
                if (tes.getMoney()>0){
                    CapaMoney.getMoney(player).ifPresent(money->{
                    money.addMoney(tes.getMoney());
                    player.displayClientMessage(new StringTextComponent("Money:  " + tes.getMoney()), true);
                    });
                }
                tes.setMoney(0);
                return ActionResultType.SUCCESS;
            }else if (tes.getInit()==0){
                tes.onInit(player);
                player.displayClientMessage(new StringTextComponent("Activited Server"),true);
                return ActionResultType.CONSUME;
            }

        }
        return ActionResultType.FAIL;
    }
}
