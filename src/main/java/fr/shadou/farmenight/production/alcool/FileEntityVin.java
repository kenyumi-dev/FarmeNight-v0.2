package fr.shadou.farmenight.production.alcool;

import fr.shadou.farmenight.init.ModTileEntity;
import net.minecraft.block.BlockState;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;

public class FileEntityVin extends TileEntity implements ITickableTileEntity {

    private int LEVEL_VIN = 2;
    private int percer = 0;
    private int reserveRAISIN = 0;
    private int tick = 0;

    public FileEntityVin() {
        super(ModTileEntity.LEVEL_VIN.get());
    }

    @Override
    public void load(BlockState state, CompoundNBT nbt) {
        super.load(state, nbt);
        this.setLEVEL_VIN(nbt.getInt("LEVEL_VIN"));
        this.setPercer(nbt.getInt("Percer"));
    }

    @Override
    public CompoundNBT save(CompoundNBT compound) {
        compound.putInt("LEVEL_VIN", this.getLEVEL_VIN());
        compound.putInt("Percer", this.isPercer());

        return compound;
    }

    @Override
    public void tick() {
        this.setTick(tick+1);
    }

    /*


     */

    public void setLEVEL_VIN(int leveling) {
        this.LEVEL_VIN = leveling;
    }
    public int getLEVEL_VIN() {
        return LEVEL_VIN;
    }
    public void sousLEVEL_VIN(int sous){
        this.LEVEL_VIN = LEVEL_VIN-sous;
    }
    public void addLEVEL_VIN(int add){
        this.LEVEL_VIN = LEVEL_VIN+add;
    }
    public void setPercer(int percer){
        this.percer = percer;
    }
    public int isPercer() {
        return percer;
    }
    public void truepercer(int true1){
        this.percer = true1;
    }
    public void falsepercer(int false0){
        this.percer = false0;
    }

    public void setReserveRAISIN(int reserveRAISIN) {
        this.reserveRAISIN = reserveRAISIN;
    }
    public int getReserveRAISIN() {
        return reserveRAISIN;
    }
    public void addReserveRAISIN(int raisin){
        this.reserveRAISIN = reserveRAISIN+raisin;
    }
    public void sousReserveRAISIN(int raisin){
        this.reserveRAISIN = reserveRAISIN-raisin;
    }
    public void setTick(int tick) {
        this.tick = tick;
    }
    public int getTick() {
        return tick;
    }

    /*


  */

    public void fermentation(){
        this.setTick(0);
        int j = 100;
        if (this.getReserveRAISIN() >= 5){
            while (this.getTick() >= j){}
            this.addLEVEL_VIN(1);
            this.sousReserveRAISIN(5);
        }
    }


}
