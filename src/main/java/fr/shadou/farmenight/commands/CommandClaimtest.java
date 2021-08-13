package fr.shadou.farmenight.commands;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.builder.ArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import fr.shadou.farmenight.claim.capability.CapaClaim;
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.world.World;

import java.util.concurrent.atomic.AtomicInteger;

public class CommandClaimtest implements Command<CommandSource> {

    private int claimtestint = 0;

    private static final CommandClaimtest CMD = new CommandClaimtest();

    public static ArgumentBuilder<CommandSource, ?> register(CommandDispatcher<CommandSource> dispatcher) {
        return Commands.literal("claimtest")
                .requires(cs -> cs.hasPermission(0)).then(Commands.literal("set").then(Commands.argument("nbt", IntegerArgumentType.integer(1)).executes(CMD)));
    }

    @Override
    public int run(CommandContext<CommandSource> context) throws CommandSyntaxException {
        int nbt = IntegerArgumentType.getInteger(context,"nbt");

        System.out.println(nbt);
        System.out.println("coucou");

        ServerPlayerEntity player = context.getSource().getPlayerOrException();
        World world = player.getCommandSenderWorld();

        CapaClaim.getWord(world).ifPresent(claimtest -> {claimtest.settest(nbt);
            claimtestint = claimtest.gettest();
        });


        System.out.println(claimtestint);

        return 0;
    }
}
