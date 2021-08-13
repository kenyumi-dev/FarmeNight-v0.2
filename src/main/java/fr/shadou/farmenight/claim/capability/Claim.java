package fr.shadou.farmenight.claim.capability;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class Claim implements IClaim{

    private int testint;

    private BlockPos coord0;
    private BlockPos coord1;
    private String player;

    private World world;

    public Claim(@Nullable final World world){
        this.world =world;
    }

    @Override
    public int gettest() {
        return testint;
    }

    @Override
    public void settest(int testint) {
        this.testint = testint;
    }

    @Override
    public BlockPos getcoord0() {
        return coord0;
    }

    @Override
    public BlockPos getcoord1() {
        return coord1;
    }

    @Override
    public String getplayer() {
        return player;
    }

    @Override
    public void setcoord0(BlockPos area) {
        this.coord0 = area;
    }

    @Override
    public void setcoord1(BlockPos area) {
        this.coord1 = area;
    }

    @Override
    public void setplayer(String newplayer) {
        this.player = newplayer;
    }
}
