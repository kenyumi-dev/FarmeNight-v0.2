package fr.shadou.farmenight.init;

import fr.shadou.farmenight.production.alcool.BarrilContainer;
import fr.shadou.farmenight.Main;
import net.minecraft.inventory.container.ContainerType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModContrainer {

    public static final DeferredRegister<ContainerType<?>> CONTAINERS = DeferredRegister.create(ForgeRegistries.CONTAINERS, Main.MODID);

    public static final RegistryObject<ContainerType<BarrilContainer>> BARRIL_CONTRAINER = CONTAINERS.register("test_container", ()-> new ContainerType<>(BarrilContainer::new));
}
