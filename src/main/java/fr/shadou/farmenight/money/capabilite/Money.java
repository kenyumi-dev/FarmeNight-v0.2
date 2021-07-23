package fr.shadou.farmenight.money.capabilite;

import net.minecraft.entity.LivingEntity;

import javax.annotation.Nullable;

public class Money implements IMoney{

    private int money;

    private final LivingEntity entity;

    public Money(@Nullable final LivingEntity entity) {
        this.entity = entity;
    }

    @Override
    public int getMoney() {
        return money;
    }

    @Override
    public void setMoney(int money) {
        this.money = money;
    }

    @Override
    public void addMoney(int addMoney) {
        this.money = this.money + addMoney;
    }
}
