package fr.shadou.farmenight.production.server;

import fr.shadou.farmenight.init.ModTileEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.NonNullList;

public class TileEntityServer extends TileEntity implements ITickableTileEntity {

    private PlayerEntity player = null;
    private int money = 0;
    private int facture = 0;
    private int durability = 0;

    public TileEntityServer() {
        super(ModTileEntity.TILE_ENTITY_SERVER.get());
    }

    @Override
    public void tick() {

    }

}
