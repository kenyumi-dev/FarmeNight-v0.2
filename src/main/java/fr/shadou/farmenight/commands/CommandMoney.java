package fr.shadou.farmenight.commands;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.builder.ArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import fr.shadou.farmenight.RessourceInt;
import fr.shadou.farmenight.money.capabilite.CapaMoney;
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.text.TranslationTextComponent;

public class CommandMoney{

    private  int money=0;

    public static void register(CommandDispatcher<CommandSource> dispatcher){
        dispatcher.register(Commands.literal("money")
                .executes((ctx)->{
                    return getmoneycommand(ctx);
                })
                .then(Commands.literal("restart").executes((ctx)->{
                    return restartmoneycommand(ctx);
                }))
                .then(Commands.literal("mode")
                        .then(Commands.literal("set")
                                .then(Commands.argument("nbt", IntegerArgumentType.integer(1)).executes((ctx)->{
                                    return modeMoney(ctx, IntegerArgumentType.getInteger(ctx, "nbt"),1);
                        })
                                )
                        )
                        .then(Commands.literal("add")
                                .then(Commands.argument("nbt", IntegerArgumentType.integer(1)).executes((ctx)->{
                                    return modeMoney(ctx, IntegerArgumentType.getInteger(ctx, "nbt"),1);
                                }))
                        )
                )
        );

    }

    public static int getmoneycommand(CommandContext<CommandSource> context) throws CommandSyntaxException {
        RessourceInt ressourceInt = new RessourceInt();
        ServerPlayerEntity player = context.getSource().getPlayerOrException();
        CapaMoney.getMoney(player).ifPresent(money -> {
            ressourceInt.money=money.getMoney();});
        context.getSource().sendSuccess(new TranslationTextComponent("Money :      "+ressourceInt.money),false);
        return 0;
    }

    public static int restartmoneycommand(CommandContext<CommandSource> context) throws CommandSyntaxException {
        RessourceInt ressourceInt = new RessourceInt();
        ServerPlayerEntity player = context.getSource().getPlayerOrException();
        CapaMoney.getMoney(player).ifPresent(money -> {money.setMoney(0);System.out.println(money.getMoney());
        ressourceInt.moneyrest=money.getMoney();});
        context.getSource().sendSuccess(new TranslationTextComponent("Restart Money!!!"),false);
        return 0;
    }

    public static int modeMoney(CommandContext<CommandSource> context ,int nbt,int modeactive) throws CommandSyntaxException{
        RessourceInt ressourceInt = new RessourceInt();
        ServerPlayerEntity player = context.getSource().getPlayerOrException();
        if (modeactive==0) {
            CapaMoney.getMoney(player).ifPresent(money -> {
                money.addMoney(nbt);
                System.out.println(money.getMoney());
                ressourceInt.moneyadd = money.getMoney();
            });
            context.getSource().sendSuccess(new TranslationTextComponent("Votre argent a augmenté de "+nbt), false);
        }
        if (modeactive==1){
            CapaMoney.getMoney(player).ifPresent(money -> {
                money.setMoney(nbt);
                System.out.println(money.getMoney());
                ressourceInt.moneyset = money.getMoney();
            });
            context.getSource().sendSuccess(new TranslationTextComponent("Votre argent est à "+nbt), false);
        }
        return 0;
    }

}
