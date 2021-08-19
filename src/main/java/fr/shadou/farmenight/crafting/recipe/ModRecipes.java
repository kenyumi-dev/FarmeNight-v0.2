package fr.shadou.farmenight.crafting.recipe;

import fr.shadou.farmenight.Main;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModRecipes {

    public static final DeferredRegister<IRecipeSerializer<?>> RECIPE_SERIALIZERS
            = DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, Main.MODID);


    public static final RegistryObject<AreRecipe.Serializer> RECIPE_ARME = RECIPE_SERIALIZERS.register("arming", AreRecipe.Serializer::new);

    public static final IRecipeType<AreRecipe> ARME = IRecipeType.register(Main.MODID + "arme");

    public static void init() { }
}
