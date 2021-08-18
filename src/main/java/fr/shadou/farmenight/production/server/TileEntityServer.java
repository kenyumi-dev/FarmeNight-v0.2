package fr.shadou.farmenight.production.server;

import fr.shadou.farmenight.Gestion;
import fr.shadou.farmenight.init.ModTileEntity;
import fr.shadou.farmenight.money.capabilite.CapaMoney;
import net.minecraft.block.BlockState;
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
    private int init = 0;
    private int time = 0;
    private int time1 = 0;

    public TileEntityServer() {
        super(ModTileEntity.TILE_ENTITY_SERVER.get());
    }

    /**
     * init
      **/

    @Override
    public CompoundNBT save(CompoundNBT compoundNBT) {
        compoundNBT.putInt("Money", this.getMoney());
        compoundNBT.putInt("Facture", this.getFacture());
        compoundNBT.putInt("Init", this.getInit());
        return compoundNBT;
    }

    @Override
    public void load(BlockState state, CompoundNBT nbt) {
        super.load(state, nbt);
        this.setMoney(nbt.getInt("Money"));
        this.setFacture(nbt.getInt("Facture"));
        this.setInit(nbt.getInt("Init"));
    }

    @Override
    public void tick() {
        Gestion ges = new Gestion();
        this.addTime(1);
        this.addTime1(1);
        if (this.init==1){
            if (this.time1== ges.TickTime(0,1)){
                this.addMoney(3);
                this.addFacture(5);
                this.setTime1(0);
            }
            if (this.time>=18000){
            CapaMoney.getMoney(this.player).ifPresent(money ->{
                money.sousMoney(this.facture);
                this.setFacture(0);});
            }
        }
    }
    /**
    *  setter, getter valeur
    **/

    public void setMoney(int money){
        this.money = money;
    }

    public int getMoney() {
        return money;
    }

    public void setFacture(int facture) {
        this.facture = facture;
    }

    public int getFacture() {
        return facture;
    }

    public void setInit(int init) {
        this.init = init;
    }

    public int getInit() {
        return init;
    }
    public void setTime(int time) {
        this.time = time;
    }

    public int getTime() {
        return time;
    }

    public void setTime1(int time1) {
        this.time1 = time1;
    }

    public int getTime1() {
        return time1;
    }

    /**
     *
     * **/

    public void addMoney(int add){this.money = this.money+add;}
    public void addFacture(int add){this.facture = this.facture+add;}
    public void addTime(int add){this.time = this.time+add;}
    public void addTime1(int add){this.time1 = this.time1+add;}

    /**
    *
     **/

    public void onInit(PlayerEntity player){
        this.init = 1;
        this.player = player;
    }

}
