package fr.shadou.farmenight.init;

import fr.shadou.farmenight.Main;
import fr.shadou.farmenight.money.AddMoney;
import fr.shadou.farmenight.production.alcool.AlcoolPropertiesClose;
import fr.shadou.farmenight.production.alcool.AlcoolPropertiesOpen;
import net.minecraft.item.Food;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.UseAction;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModItem {

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Main.MODID);

    //Vin
    public static final RegistryObject<Item> BOTTLE_WINE_OPEN = ITEMS.register("bottle_wine_open",() -> new AlcoolPropertiesOpen(new Item.Properties()));
    public static final RegistryObject<Item> BOTTLE_WINE_CLOSE = ITEMS.register("bottle_wine_close",() -> new AlcoolPropertiesClose(new Item.Properties()));
    public static final RegistryObject<Item> PERCEUR = ITEMS.register("perceur", () -> new Item(new Item.Properties().stacksTo(1).durability(20)));
    public static final RegistryObject<Item> RAISIN = ITEMS.register("raisin", () -> new Item(new Item.Properties()
    .food(new Food.Builder().nutrition(2).saturationMod(1).build())
    ));
    public static final RegistryObject<Item> BOUCHON = ITEMS.register("bouchon", () -> new Item(new Item.Properties()));

    //Test Money
    public static final RegistryObject<Item> ADD_MONEY = ITEMS.register("add_money", () -> new AddMoney(new Item.Properties()));

}
