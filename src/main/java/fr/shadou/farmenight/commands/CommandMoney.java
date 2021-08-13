package fr.shadou.farmenight.commands;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.builder.ArgumentBuilder;
import fr.shadou.farmenight.commands.action.money.AddMoneyCommand;
import fr.shadou.farmenight.commands.action.money.GetMoneyCommand;
import fr.shadou.farmenight.commands.action.money.RestarMoney;
import fr.shadou.farmenight.commands.action.money.SetMoneyCommand;
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;

public class CommandMoney{

    public static final RestarMoney REST = new RestarMoney();
    public static final AddMoneyCommand ADD = new AddMoneyCommand();
    public static final SetMoneyCommand SET = new SetMoneyCommand();
    public static final GetMoneyCommand GET = new GetMoneyCommand();


    public static ArgumentBuilder<CommandSource, ?>register(CommandDispatcher<CommandSource> dispatcher) {
        return Commands.literal("money")
                .requires(cs -> cs.hasPermission(0))
                .then(Commands.literal("restartmoney").executes(REST))
                .then(Commands.literal("get").executes(GET))
                .then(Commands.literal("add").then(Commands.argument("nbt_money", IntegerArgumentType.integer(1))).executes(ADD))
                .then(Commands.literal("set").then(Commands.argument("nbt_initial", IntegerArgumentType.integer())).executes(SET));
    }
}
