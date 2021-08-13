package fr.shadou.farmenight.production.server.capability;

import net.minecraft.block.Block;

import javax.annotation.Nullable;

public class Serveur implements IServeur{

    private int facture;
    private int durability;

    private Block block;

    public Serveur(@Nullable final Block block){
        this.block = block;
    }

    @Override
    public int getFacture() {
        return facture;
    }

    @Override
    public void setFacture(int facture) {
        this.facture = facture;
    }

    @Override
    public void addFacture(int add) {
        this.facture = this.facture + add;
    }

    @Override
    public int getDurability() {
        return durability;
    }

    @Override
    public void setDurability(int durability) {
        this.durability = durability;
    }

    @Override
    public void addDurability(int add) {
        this.durability = this.durability + add;
    }

    @Override
    public void sousDurability(int sous) {
        this.durability = this.durability - sous;
    }
}
