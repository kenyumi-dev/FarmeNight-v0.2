package fr.shadou.farmenight.commands;

import com.google.common.collect.Lists;
import com.mojang.brigadier.Command;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.builder.ArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.exceptions.Dynamic2CommandExceptionType;
import com.mojang.brigadier.exceptions.SimpleCommandExceptionType;
import fr.shadou.farmenight.money.capabilite.CapaMoney;
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.command.arguments.BlockPosArgument;
import net.minecraft.command.arguments.BlockStateInput;
import net.minecraft.command.arguments.EntityArgument;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.IClearable;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.CachedBlockInfo;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.server.ServerWorld;

import javax.annotation.Nullable;
import java.util.List;
import java.util.function.Predicate;

public class CommandClaim implements Command<CommandSource> {

    private static int z0;
    private static int z1;
    private static int x0;
    private static int x1;
    private static int y0;
    private static int y1;
    private static ServerPlayerEntity player;

    public static final CommandClaim CMD = new CommandClaim();

    public static ArgumentBuilder<CommandSource, ?> register(CommandDispatcher<CommandSource> dispatcher) {
        return Commands.literal("claim")
                .requires(cs -> cs.hasPermission(0))
                .then(Commands.argument("from", BlockPosArgument.blockPos()).then(Commands.argument("to", BlockPosArgument.blockPos()).then(Commands.argument("player", EntityArgument.player())))).executes((p_208897_0_)->{
                    return registerClaim(p_208897_0_.getSource(), new MutableBoundingBox(BlockPosArgument.getLoadedBlockPos(p_208897_0_,"from"), BlockPosArgument.getLoadedBlockPos(p_208897_0_,"to")), EntityArgument.getPlayer(p_208897_0_,"player"));

                });
    }

    public static int registerClaim(CommandSource source, MutableBoundingBox area, ServerPlayerEntity playerEntity){
        x0 = area.x0;
        x1 = area.x1;
        y0 = area.y0;
        y1 = area.y1;
        z0 = area.z0;
        z1 = area.z1;
        player = playerEntity;
        return 0;
    }

    @Override
    public int run(CommandContext<CommandSource> context) throws CommandSyntaxException {
        context.getSource().sendSuccess(new TranslationTextComponent(""+x0+"   "+ x0+"   "+ z0),false);
        context.getSource().sendSuccess(new TranslationTextComponent(""+x1+"   "+ x1+"   "+ z1),false);
        context.getSource().sendSuccess(new TranslationTextComponent(""+player),false);
        return 0;
    }
}
