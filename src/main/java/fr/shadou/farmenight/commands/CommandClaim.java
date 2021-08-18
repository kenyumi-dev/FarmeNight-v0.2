package fr.shadou.farmenight.commands;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import fr.shadou.farmenight.claim.claimworld.ClaimStrokage;
import fr.shadou.farmenight.production.server.Server;
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.command.arguments.BlockPosArgument;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.server.ServerWorld;


public class CommandClaim {

    public static void register(CommandDispatcher<CommandSource> dispatcher){
        dispatcher.register(Commands.literal("claim").executes((ctx)->{
            return getClaimList(ctx);
        })
                .then(Commands.literal("createClaim").then(Commands.argument("from", BlockPosArgument.blockPos()).then(Commands.argument("to", BlockPosArgument.blockPos()).executes((ctx)->{
                    return createClaim(ctx, BlockPosArgument.getLoadedBlockPos(ctx,"from"), BlockPosArgument.getLoadedBlockPos(ctx,"to"));
                }))))
                .then(Commands.literal("removeClaim").executes((ctx)->{
                    return 0;
                })));
    }

    private static int createClaim(CommandContext<CommandSource> context, BlockPos from, BlockPos to) throws CommandSyntaxException{
        CompoundNBT tag = new CompoundNBT();
        ServerPlayerEntity player = context.getSource().getPlayerOrException();
        ServerWorld world = player.getLevel();
        ClaimStrokage strokage = ClaimStrokage.get(world);
        strokage.generatClaim(player.getName().getString(),from,to);
        strokage.save(tag);
        player.displayClientMessage(new StringTextComponent("vous donnée on etait bien sauvegarder"),true);
        player.displayClientMessage(new StringTextComponent("Id: "+strokage.getID()+"player: "+strokage.getPlayerClaim()+"pos1: "+strokage.getZone1()+"pos2:"+strokage.getZone2()),true);
        return 0;
    }

    private static int getClaimList(CommandContext<CommandSource> ctx) throws CommandSyntaxException {
        CompoundNBT tag = new CompoundNBT();
        ServerPlayerEntity player = ctx.getSource().getPlayerOrException();
        ServerWorld world = player.getLevel();
        ClaimStrokage strokage = ClaimStrokage.get(world);
        strokage.load(tag);
        player.displayClientMessage(new StringTextComponent("Id: "+strokage.getID()+"player: "+strokage.getPlayerClaim()+"pos1: "+strokage.getZone1()+"pos2:"+strokage.getZone2()),true);
        return 0;
    }

}
