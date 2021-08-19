package fr.shadou.farmenight;

import fr.shadou.farmenight.init.*;
import fr.shadou.farmenight.pnj.PNJVinRenderer;
import fr.shadou.farmenight.production.arme.TableContainerScreen;
import net.minecraft.client.gui.ScreenManager;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
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
        FMLJavaModLoadingContext.get().getModEventBus().addListener(ModSetup::init);

        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();

        ModBlock.BLOCKS.register(bus);
        ModBlock.ITEMS.register(bus);
        ModItem.ITEMS.register(bus);
        ModTileEntity.TILE_ENTITY.register(bus);
        ModContainer.CONTAINERS.register(bus);
        ModEntities.ENTITIES.register(bus);
    }

    private void setup(FMLCommonSetupEvent e) {

    }

    private void clientSetup(FMLClientSetupEvent e) {
        ScreenManager.register(ModContainer.TABLE_CONTAINER.get(), TableContainerScreen::new);
        RenderingRegistry.registerEntityRenderingHandler(ModEntities.PNJVIN.get(), PNJVinRenderer::new);
    }
}