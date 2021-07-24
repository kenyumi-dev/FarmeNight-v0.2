package fr.shadou.farmenight.init;

import fr.shadou.farmenight.Main;
import fr.shadou.farmenight.money.capabilite.CapaMoney;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

@Mod.EventBusSubscriber(modid = Main.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModCapabilitilies {

    @SubscribeEvent
    public static void registerCapabilities(final FMLCommonSetupEvent event){
        MinecraftForge.EVENT_BUS.register(CapaMoney.class);
        CapaMoney.register();
    }

}
