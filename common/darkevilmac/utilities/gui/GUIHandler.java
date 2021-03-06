package darkevilmac.utilities.gui;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import cpw.mods.fml.common.network.IGuiHandler;
import darkevilmac.utilities.lib.GuiIDS;
import darkevilmac.utilities.tile.TileEntityEnergySolidifier;
import darkevilmac.utilities.tile.TileEntityFluidNetworkBridge;
import darkevilmac.utilities.tile.TileEntityItemNetworkBridge;

public class GUIHandler implements IGuiHandler {

    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        TileEntity tile = world.getTileEntity(x, y, z);

        switch (ID) {
            case GuiIDS.FLUID_NETWORK_BRIDGE_GUIID:
                if (tile != null && tile instanceof TileEntityFluidNetworkBridge) {
                    return new ContainerFluidNetworkBridge(player.inventory, (TileEntityFluidNetworkBridge) tile);
                } else {
                    return null;
                }

            case GuiIDS.ITEM_NETWORK_BRIDGE_GUIID:
                if (tile != null && tile instanceof TileEntityItemNetworkBridge) {
                    return new ContainerItemNetworkBridge(player.inventory, (TileEntityItemNetworkBridge) tile);
                } else {
                    return null;
                }

            case GuiIDS.ENERGY_SOLIDIFIER_GUIID:
                if (tile != null && tile instanceof TileEntityEnergySolidifier) {
                    return new ContainerEnergySolidifier(player.inventory, (TileEntityEnergySolidifier) tile);
                } else {
                    return null;
                }

            default:
                return null;
        }
    }

    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        TileEntity tile = world.getTileEntity(x, y, z);

        switch (ID) {
            case GuiIDS.FLUID_NETWORK_BRIDGE_GUIID:
                if (tile != null && tile instanceof TileEntityFluidNetworkBridge) {
                    return new GuiFluidNetworkBridge(player.inventory, (TileEntityFluidNetworkBridge) tile);
                } else {
                    return null;
                }

            case GuiIDS.ITEM_NETWORK_BRIDGE_GUIID:
                if (tile != null && tile instanceof TileEntityItemNetworkBridge) {
                    return new GuiItemNetworkBridge(player.inventory, (TileEntityItemNetworkBridge) tile);
                } else {
                    return null;
                }

            case GuiIDS.ENERGY_SOLIDIFIER_GUIID:
                if (tile != null && tile instanceof TileEntityEnergySolidifier) {
                    return new GuiEnergySolidifier(player.inventory, (TileEntityEnergySolidifier) tile);
                } else {
                    return null;
                }

            default:
                return null;
        }
    }

}
