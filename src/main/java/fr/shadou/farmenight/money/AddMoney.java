package fr.shadou.farmenight.money;

import fr.shadou.farmenight.money.capabilite.CapaMoney;
import fr.shadou.farmenight.money.capabilite.Money;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.Util;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;

import java.io.File;

public class AddMoney extends Item {
    public AddMoney(Properties properties) {
        super(properties);
    }

    @Override
    public ActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand) {
        CapaMoney.getMoney(player).ifPresent((money) -> {money.addMoney(1);System.out.println(money.getMoney());});
        return ActionResult.success(player.getItemInHand(hand));
    }
}
