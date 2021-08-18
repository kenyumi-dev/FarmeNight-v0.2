package fr.shadou.farmenight;

import fr.shadou.farmenight.money.capabilite.CapaMoney;
import fr.shadou.farmenight.production.server.TileEntityServer;
import net.minecraft.client.world.DimensionRenderInfo;
import net.minecraft.command.arguments.DimensionArgument;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.management.PlayerList;
import net.minecraft.world.Dimension;
import net.minecraft.world.DimensionType;
import net.minecraft.world.World;
import net.minecraft.world.gen.DimensionSettings;
import net.minecraft.world.gen.settings.DimensionGeneratorSettings;
import net.minecraft.world.server.ServerWorld;

import net.minecraft.util.registry.Registry;
import java.util.List;
import java.util.UUID;

public class Gestion {

    public int TickTime(int seconde){
        return seconde*20;
    }
    public int TickTime(int seconde, int minute){
        return TickTime(seconde+minute*60);
    }
    public int TickTime(int seconde,int minute, int hour){
        return TickTime(seconde, minute+hour*60);
    }

    public int Pourcen(int unity, int total){
        return unity*100/total;
    }

    public int PourcentUnity(int total){
        return total/100;
    }

    public static void onServer(PlayerEntity player){

    }

}
