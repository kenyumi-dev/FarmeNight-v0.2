package fr.shadou.farmenight.init;

import fr.shadou.farmenight.Main;
import fr.shadou.farmenight.claim.TileEntityGod;
import fr.shadou.farmenight.production.alcool.FileEntityVin;

import fr.shadou.farmenight.production.arme.TileEntityArme;
import fr.shadou.farmenight.production.server.TileEntityServer;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModTileEntity {

    public static final DeferredRegister<TileEntityType<?>> TILE_ENTITY = DeferredRegister.create(ForgeRegistries.TILE_ENTITIES, Main.MODID);

    public static final RegistryObject<TileEntityType<?>> LEVEL_VIN = TILE_ENTITY.register("level_vin", () -> TileEntityType.Builder.of(FileEntityVin::new, ModBlock.BARRIL_VIN.get()).build(null));
    public static final RegistryObject<TileEntityType<?>> TILEENTITY_ARME = TILE_ENTITY.register("tileentity_arme", () -> TileEntityType.Builder.of(TileEntityArme::new, ModBlock.TABLE_D_ASSEMBLAGE.get()).build(null));
    public static final RegistryObject<TileEntityType<?>> TILE_ENTITY_SERVER = TILE_ENTITY.register("tile_entity_server",() -> TileEntityType.Builder.of(TileEntityServer::new, ModBlock.SERVER.get()).build(null));
    public static final RegistryObject<TileEntityType<?>> TILE_ENTITY_GOD = TILE_ENTITY.register("tile_entity_god",() -> TileEntityType.Builder.of(TileEntityGod::new, ModBlock.NEWGOD.get()).build(null));
}
