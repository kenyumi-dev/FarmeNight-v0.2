package fr.shadou.farmenight;

import fr.shadou.farmenight.init.ModBlock;
import fr.shadou.farmenight.init.ModContainer;
import fr.shadou.farmenight.init.ModItem;
import fr.shadou.farmenight.init.ModTileEntity;
import fr.shadou.farmenight.production.arme.TableContainerScreen;
import net.minecraft.client.gui.ScreenManager;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(Main.MODID)
public class Main {

    public static final String MODID = "farmenight";

    public Main() {

        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::clientSetup);

        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();

        ModBlock.BLOCKS.register(bus);
        ModBlock.ITEMS.register(bus);
        ModItem.ITEMS.register(bus);
        ModTileEntity.TILE_ENTITY.register(bus);
        ModContainer.CONTAINERS.register(bus);

    }

    private void setup(FMLCommonSetupEvent e) {
        ScreenManager.register(ModContainer.TABLE_CONTAINER.get(), TableContainerScreen::new);
    }

    private void clientSetup(FMLClientSetupEvent e) {

    }

}