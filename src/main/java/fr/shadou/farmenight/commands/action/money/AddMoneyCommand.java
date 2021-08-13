package fr.shadou.farmenight.commands.action.money;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import fr.shadou.farmenight.money.capabilite.CapaMoney;
import net.minecraft.command.CommandSource;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.text.TranslationTextComponent;

public class AddMoneyCommand implements Command<CommandSource> {

    private int money;

    @Override
    public int run(CommandContext<CommandSource> context) throws CommandSyntaxException {
        ServerPlayerEntity player = context.getSource().getPlayerOrException();
        CapaMoney.getMoney(player).ifPresent(money -> {money.addMoney(IntegerArgumentType.getInteger(context, "nbt_money"));this.money=money.getMoney();});
        context.getSource().sendSuccess(new TranslationTextComponent("Vous avez gagné "+this.money+"$"),false);
        return 0;
    }
}
