package fr.shadou.farmenight.init;

import fr.shadou.farmenight.Main;
import fr.shadou.farmenight.money.AddMoney;
import fr.shadou.farmenight.money.phone.Phone;
import fr.shadou.farmenight.production.alcool.AlcoolProperties;
import net.minecraft.item.Food;
import net.minecraft.item.Item;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModItem {

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Main.MODID);

    public static final Item.Properties groupItems = new Item.Properties().tab(ModItemGroup.CSMM_TAG);

    //Vin
    public static final RegistryObject<Item> BOTTLE_WINE = ITEMS.register("bottle_wine_open",() -> new AlcoolProperties(new Item.Properties()));
    public static final RegistryObject<Item> BOTTLE_WINE_CLOSE = ITEMS.register("bottle_wine_close",() -> new AlcoolProperties(new Item.Properties()));
    public static final RegistryObject<Item> PERCEUR = ITEMS.register("perceur", () -> new Item(new Item.Properties().stacksTo(1).durability(20)));
    public static final RegistryObject<Item> RAISIN = ITEMS.register("raisin", () -> new Item(new Item.Properties()
    .food(new Food.Builder().nutrition(2).saturationMod(1).build())
    ));
    public static final RegistryObject<Item> BOUCHON = ITEMS.register("bouchon", () -> new Item(groupItems));

    //Test Money
    public static final RegistryObject<Item> ADD_MONEY = ITEMS.register("add_money", () -> new AddMoney(groupItems));
    public static final RegistryObject<Item> CARTE = ITEMS.register("carte", () -> new Item(new Item.Properties()));

    //Armes
    public static final RegistryObject<Item> CROSSE = ITEMS.register("crosse", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> GARDEMAIN = ITEMS.register("gardemain", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> CHARGEUR = ITEMS.register("chargeur", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> CANON = ITEMS.register("canon", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> AK_47 = ITEMS.register("ak_47",() -> new Item(new Item.Properties()));

    //Money Officiel
    public static final RegistryObject<Item> IPHONE = ITEMS.register("iphone", () -> new Phone(groupItems));
}
