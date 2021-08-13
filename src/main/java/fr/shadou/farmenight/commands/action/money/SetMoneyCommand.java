package fr.shadou.farmenight.commands.action.money;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import fr.shadou.farmenight.money.capabilite.CapaMoney;
import net.minecraft.command.CommandSource;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.text.TranslationTextComponent;

public class SetMoneyCommand implements Command<CommandSource> {

    private int money;

    @Override
    public int run(CommandContext<CommandSource> context) throws CommandSyntaxException {
        ServerPlayerEntity player = context.getSource().getPlayerOrException();
        CapaMoney.getMoney(player).ifPresent(money -> {money.setMoney(IntegerArgumentType.getInteger(context, "nbt_initial"));this.money=money.getMoney();});
        context.getSource().sendSuccess(new TranslationTextComponent("Vous etes à; "+this.money+"$"),false);
        return 0;
    }
}
