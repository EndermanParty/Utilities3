package darkevilmac.utilities.item;

import java.util.List;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraft.world.WorldProvider;
import darkevilmac.utilities.lib.Strings;

public class ItemNotALinkingBook extends ItemUtilities {

    public ItemNotALinkingBook(int id) {
        super(id);
        this.setCreativeTab(CreativeTabs.tabAllSearch);
        this.setUnlocalizedName(Strings.NOT_A_LINKINGBOOK_UNLOCALIZEDNAME);
        maxStackSize = 1;
    }

    @Override
    public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player) {
        if (!stack.hasTagCompound())
            stack.setTagCompound(new NBTTagCompound());

        NBTTagCompound nbt = stack.getTagCompound();
        if (nbt.getBoolean("HasInfo") == false) {
            nbt.setDouble("x", player.posX);
            nbt.setDouble("y", player.posY);
            nbt.setDouble("z", player.posZ);
            if (player.worldObj != null) {
                nbt.setInteger("WorldId", player.worldObj.provider.dimensionId);
            } else {

                nbt.setBoolean("HasInfo", true);
            }
            System.out.println(nbt.getDouble("x") + " " + nbt.getDouble("y") + " " + nbt.getDouble("z"));
            player.addChatMessage("Location has been set for the book.");
        } else {
            World tpWorld;
            WorldProvider tpWorldProvider = null;
            tpWorldProvider.setDimension(nbt.getInteger("WorldId"));
            tpWorld = tpWorldProvider.worldObj;
            player.setWorld(tpWorld);
            player.setPosition(nbt.getDouble("x"), nbt.getDouble("y"), nbt.getDouble("z"));
            player.addChatMessage("Teleporting you to the location set in the book.");
        }
        return stack;
    }

    @Override
    public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean par4) {

    }

}
