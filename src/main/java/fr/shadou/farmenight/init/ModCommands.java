package fr.shadou.farmenight.init;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.tree.LiteralCommandNode;
import fr.shadou.farmenight.Main;
import fr.shadou.farmenight.commands.CommandClaim;
import fr.shadou.farmenight.commands.CommandClaimtest;
import fr.shadou.farmenight.commands.CommandMoney;
import fr.shadou.farmenight.commands.CommandTaxe;
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;

public class ModCommands {

    public static void register(CommandDispatcher<CommandSource> dispatcher){
        LiteralCommandNode<CommandSource> cmdTut = dispatcher.register(
                Commands.literal(Main.MODID)
                        .then(CommandMoney.register(dispatcher))
                        .then(CommandTaxe.register(dispatcher))
                        .then(CommandClaim.register(dispatcher))
                        .then(CommandClaimtest.register(dispatcher))
        );

        dispatcher.register(Commands.literal("tut").redirect(cmdTut));
    }

}
