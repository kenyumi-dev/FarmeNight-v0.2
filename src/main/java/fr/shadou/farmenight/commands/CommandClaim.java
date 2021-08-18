package fr.shadou.farmenight.commands;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import fr.shadou.farmenight.claim.claimworld.ClaimStrokage;
import fr.shadou.farmenight.production.server.Server;
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

import java.util.UUID;

public class CommandClaim {

    public static void register(CommandDispatcher<CommandSource> dispatcher){
        dispatcher.register(Commands.literal("Claim").executes((ctx)->{
            return getClaimList(ctx);
        })
                .then());
    }

    private static int getClaimList(CommandContext<CommandSource> ctx) throws CommandSyntaxException {
        int ID = 0;
        String playerClaim= "";
        ServerPlayerEntity player = ctx.getSource().getPlayerOrException();
        ServerWorld world = player.getLevel();
        ClaimStrokage strokage = ClaimStrokage.get(world,ID,playerClaim);
        

        return 0;
    }

}
