package fr.shadou.farmenight.money.capabilite;

import net.minecraft.entity.LivingEntity;

import javax.annotation.Nullable;

public class Taxe implements ITaxe {

    private int taux;

    private final LivingEntity entity;

    public Taxe(@Nullable final LivingEntity entity){
        this.entity = entity;
    }

    @Override
    public int getTaxe() {
        return taux;
    }

    @Override
    public void setTaxe(int amount) {
        this.taux = amount;

    }
}
