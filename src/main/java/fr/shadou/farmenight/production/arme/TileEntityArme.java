package fr.shadou.farmenight.production.arme;

import fr.shadou.farmenight.init.ModBlock;
import fr.shadou.farmenight.init.ModTileEntity;
import net.minecraft.block.BlockState;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;

public class TileEntityArme extends TileEntity {

    private int ResultArme = 0;

    public TileEntityArme() {
        super(ModTileEntity.TILEENTITY_ARME.get());
    }

    @Override
    public void load(BlockState p_230337_1_, CompoundNBT p_230337_2_) {
        super.load(p_230337_1_, p_230337_2_);

        this.setResultArme(p_230337_2_.getInt("ResultArme"));

    }

    @Override
    public CompoundNBT save(CompoundNBT p_189515_1_) {

        p_189515_1_.putInt("ResultArme", this.getResultArme());

        return p_189515_1_;
    }

    public void setResultArme(int resultArme) {
        ResultArme = resultArme;
    }

    public int getResultArme() {
        return ResultArme;
    }

    public void addResultArme(int addresularme){
        this.ResultArme = this.ResultArme + addresularme;
    }


}

