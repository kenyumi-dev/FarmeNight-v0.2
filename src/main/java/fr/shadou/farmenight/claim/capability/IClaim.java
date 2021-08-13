package fr.shadou.farmenight.claim.capability;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MutableBoundingBox;

public interface IClaim {

    int gettest();
    void settest(final int test);

    BlockPos getcoord0();
    BlockPos getcoord1();
    void setcoord0(final BlockPos area);
    void setcoord1(final BlockPos area);

    String getplayer();
    void setplayer(final String newplayer);

}
