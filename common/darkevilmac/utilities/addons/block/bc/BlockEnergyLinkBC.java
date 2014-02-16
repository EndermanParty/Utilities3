package darkevilmac.utilities.addons.block.bc;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import darkevilmac.utilities.addons.tile.bc.TileEntityEnergyLinkBC;
import darkevilmac.utilities.block.base.BlockEnergyLinkBase;
import darkevilmac.utilities.lib.Strings;

public class BlockEnergyLinkBC extends BlockEnergyLinkBase {

    protected BlockEnergyLinkBC(int id) {
        super(id, Strings.BC_LINK_UNLOCALIZEDNAME);
    }

    @Override
    public TileEntity createNewTileEntity(World world) {
        return new TileEntityEnergyLinkBC();
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister register) {
        blockIcon = register.registerIcon("utilities:" + Strings.BC_LINK_UNLOCALIZEDNAME);
    }

}