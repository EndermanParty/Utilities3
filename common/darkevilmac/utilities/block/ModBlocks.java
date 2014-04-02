package darkevilmac.utilities.block;

import net.minecraft.block.Block;
import cpw.mods.fml.common.registry.GameRegistry;
import darkevilmac.utilities.lib.Strings;
import darkevilmac.utilities.tile.TileEntityEnergyNetworkBridge;
import darkevilmac.utilities.tile.TileEntityEnergyNetworkManager;
import darkevilmac.utilities.tile.TileEntityFluidNetworkBridge;
import darkevilmac.utilities.tile.TileEntityFluidNetworkManager;
import darkevilmac.utilities.tile.TileEntityItemNetworkBridge;
import darkevilmac.utilities.tile.TileEntityItemNetworkManager;
import darkevilmac.utilities.tile.TileEntityMultiNetworkBridge;
import darkevilmac.utilities.tile.TileEntityMultiNetworkManager;

public class ModBlocks {

    public static Block energyNetworkBridge;
    public static Block fluidNetworkBridge;
    public static Block itemNetworkBridge;
    public static Block multiNetworkBridge;
    public static Block energyNetworkManager;
    public static Block fluidNetworkManager;
    public static Block itemNetworkManager;
    public static Block multiNetworkManager;

    public static Block discBurner;
    public static Block discPlayer;

    public static void init() {

        // GameRegistry.registerTileEntity(TileEntityEnergyNetworkBridge.class,
        // "tile" + Strings.ENERGYNETWORK_BRIDGE_BLOCKNAME);
        // GameRegistry.registerTileEntity(TileEntityFluidNetworkBridge.class,
        // "tile" + Strings.FLUIDNETWORK_BRIDGE_BLOCKNAME);
        // GameRegistry.registerTileEntity(TileEntityItemNetworkBridge.class,
        // "tile" + Strings.ITEMNETWORK_BRIDGE_BLOCKNAME);
        // GameRegistry.registerTileEntity(TileEntityMultiNetworkBridge.class,
        // "tile" + Strings.MULTINETWORK_BRIDGE_BLOCKNAME);
        // GameRegistry.registerTileEntity(TileEntityEnergyNetworkManager.class,
        // "tile" + Strings.ENERGYNETWORK_MANAGER_BLOCKNAME);
        // GameRegistry.registerTileEntity(TileEntityFluidNetworkManager.class,
        // "tile" + Strings.FLUIDNETWORK_MANAGER_BLOCKNAME);
        // GameRegistry.registerTileEntity(TileEntityItemNetworkManager.class,
        // "tile" + Strings.ITEMNETWORK_MANAGER_BLOCKNAME);
        // GameRegistry.registerTileEntity(TileEntityMultiNetworkManager.class,
        // "tile" + Strings.MULTINETWORK_MANAGER_BLOCKNAME);

        // energyNetworkBridge = new BlockEnergyNetworkBridge();
        // fluidNetworkBridge = new BlockFluidNetworkBridge();
        // itemNetworkBridge = new BlockItemNetworkBridge();
        // multiNetworkBridge = new BlockMultiNetworkBridge();
        // energyNetworkManager = new BlockEnergyNetworkManager();
        // fluidNetworkManager = new BlockFluidNetworkManager();
        // itemNetworkManager = new BlockItemNetworkManager();
        // multiNetworkManager = new BlockMultiNetworkManager();

        // discBurner = new BlockDiscBurner();
        // discPlayer = new BlockDiscPlayer();

        // GameRegistry.registerBlock(energyNetworkBridge,
        // Strings.ENERGYNETWORK_BRIDGE_BLOCKNAME);
        // GameRegistry.registerBlock(fluidNetworkBridge,
        // Strings.FLUIDNETWORK_BRIDGE_BLOCKNAME);
        // GameRegistry.registerBlock(itemNetworkBridge,
        // Strings.ITEMNETWORK_BRIDGE_BLOCKNAME);
        // GameRegistry.registerBlock(multiNetworkBridge,
        // Strings.MULTINETWORK_BRIDGE_BLOCKNAME);
        // GameRegistry.registerBlock(energyNetworkManager,
        // Strings.ENERGYNETWORK_MANAGER_BLOCKNAME);
        // GameRegistry.registerBlock(fluidNetworkManager,
        // Strings.FLUIDNETWORK_MANAGER_BLOCKNAME);
        // GameRegistry.registerBlock(itemNetworkManager,
        // Strings.ITEMNETWORK_MANAGER_BLOCKNAME);
        // GameRegistry.registerBlock(multiNetworkManager,
        // Strings.MULTINETWORK_MANAGER_BLOCKNAME);

        // GameRegistry.registerBlock(discBurner,
        // Strings.DISC_BURNER_BLOCKNAME);
        // GameRegistry.registerBlock(discPlayer,
        // Strings.DISC_PLAYER_BLOCKNAME);

    }

}
