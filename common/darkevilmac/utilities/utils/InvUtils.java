package darkevilmac.utilities.utils;

/**
 * Class from https://github.com/shadowmage45/AncientWarfare2/blob/master/src/main/java/net/shadowmage/ancientwarfare/core/util/InventoryTools.java
 * 
 * With some modifications. I do not claim ownership of all code in this class.
 */

import net.minecraft.entity.item.EntityItem;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class InvUtils {

    /**
     * Attempt to merge stack into inventory via the given side, or general
     * all-sides merge if side <0<br>
     * Resorts to default general merge if inventory is not a sided inventory.<br>
     * Double-pass merging. First pass attempts to merge with partial stacks.
     * Second pass will place into empty slots if available.
     * 
     * @param inventory
     *            the inventory to merge into, must not be null
     * @param stack
     *            the stack to merge, must not be null
     * @param side
     *            or <0 for none
     * @return any remaining un-merged item, or null if completely merged
     */
    public static ItemStack mergeItemStack(IInventory inventory, ItemStack stack, int side) {
        if (inventory instanceof ISidedInventory) {
            int[] slotIndices = ((ISidedInventory) inventory).getAccessibleSlotsFromSide(side);
            if (slotIndices == null) {
                return null;
            }
            int index;
            int toMove;
            ItemStack slotStack;
            for (int i = 0; i < slotIndices.length; i++) {
                if (((ISidedInventory) inventory).canInsertItem(slotIndices[i], stack, side)) {
                    toMove = stack.stackSize;
                    index = slotIndices[i];
                    slotStack = inventory.getStackInSlot(index);
                    if (doItemStacksMatch(stack, slotStack)) {
                        if (toMove > slotStack.getMaxStackSize() - slotStack.stackSize) {
                            toMove = slotStack.getMaxStackSize() - slotStack.stackSize;
                        }
                        stack.stackSize -= toMove;
                        slotStack.stackSize += toMove;
                        inventory.setInventorySlotContents(index, slotStack);
                        inventory.markDirty();
                    }
                    if (stack.stackSize <= 0)// merged stack fully;
                    {
                        return null;
                    }
                }
            }
            if (stack.stackSize > 0) {
                for (int i = 0; i < slotIndices.length; i++) {
                    if (((ISidedInventory) inventory).canInsertItem(slotIndices[i], stack, side)) {
                        index = slotIndices[i];
                        slotStack = inventory.getStackInSlot(index);
                        if (slotStack == null) {
                            inventory.setInventorySlotContents(index, stack);
                            inventory.markDirty();
                            return null;// successful merge
                        }
                    }
                }
            } else {
                return null;// successful merge
            }
        } else {
            int index;
            int toMove;
            ItemStack slotStack;
            for (int i = 0; i < inventory.getSizeInventory(); i++) {
                if (inventory.isItemValidForSlot(i, stack)) {
                    toMove = stack.stackSize;
                    index = i;
                    slotStack = inventory.getStackInSlot(index);
                    if (doItemStacksMatch(stack, slotStack)) {
                        if (toMove > slotStack.getMaxStackSize() - slotStack.stackSize) {
                            toMove = slotStack.getMaxStackSize() - slotStack.stackSize;
                        }
                        stack.stackSize -= toMove;
                        slotStack.stackSize += toMove;
                        inventory.setInventorySlotContents(index, slotStack);
                        inventory.markDirty();
                    }
                    if (stack.stackSize <= 0)// merged stack fully;
                    {
                        return null;
                    }
                }
            }
            if (stack.stackSize > 0) {
                for (int i = 0; i < inventory.getSizeInventory(); i++) {
                    if (inventory.isItemValidForSlot(i, stack)) {
                        index = i;
                        slotStack = inventory.getStackInSlot(index);
                        if (slotStack == null) {
                            inventory.setInventorySlotContents(index, stack);
                            inventory.markDirty();
                            return null;// successful merge
                        }
                    }
                }
            } else {
                return null;// successful merge
            }
        }
        return stack;// partial or unsuccessful merge
    }

    /**
     * Attempts to remove filter * quantity from inventory. Returns removed item
     * in return stack, or null if no items were removed.
     * 
     * @param inventory
     * @param side
     * @param toRemove
     * @return the removed item.
     */
    public static ItemStack removeItems(IInventory inventory, int side, ItemStack filter, int quantity) {
        ItemStack returnStack = null;
        if (inventory instanceof ISidedInventory) {
            int[] slotIndices = ((ISidedInventory) inventory).getAccessibleSlotsFromSide(side);
            if (slotIndices == null) {
                return null;
            }
            if (filter == null) {
                for (int i = 0; i < slotIndices.length; i++) {
                    if (((ISidedInventory) inventory).getStackInSlot(slotIndices[i]) != null) {
                        if (((ISidedInventory) inventory).getStackInSlot(slotIndices[i]).stackSize == 1) {
                            if (((ISidedInventory) inventory).canExtractItem(slotIndices[i], ((ISidedInventory) inventory).getStackInSlot(slotIndices[i]), side)) {
                                returnStack = ((ISidedInventory) inventory).getStackInSlot(slotIndices[i]);
                                ((ISidedInventory) inventory).setInventorySlotContents(slotIndices[i], null);
                                break;
                            }
                        } else {
                            if (((ISidedInventory) inventory).canExtractItem(slotIndices[i], new ItemStack(((ISidedInventory) inventory).getStackInSlot(slotIndices[i]).getItem(),
                                    1), side)) {
                                returnStack = new ItemStack(((ISidedInventory) inventory).getStackInSlot(slotIndices[i]).getItem(), 1);
                                ((ISidedInventory) inventory).setInventorySlotContents(slotIndices[i], new ItemStack(((ISidedInventory) inventory).getStackInSlot(slotIndices[i])
                                        .getItem(), ((ISidedInventory) inventory).getStackInSlot(slotIndices[i]).stackSize - 1));
                                break;
                            }
                        }
                    }
                }
            } else {

                int index;
                int toMove;
                ItemStack slotStack;
                for (int i = 0; i < slotIndices.length; i++) {

                    index = slotIndices[i];
                    slotStack = inventory.getStackInSlot(index);
                    if (!doItemStacksMatch(slotStack, filter)) {
                        continue;
                    }
                    if (returnStack == null) {
                        returnStack = new ItemStack(filter.getItem());
                        returnStack.stackSize = 0;
                    }
                    toMove = slotStack.stackSize;
                    if (toMove > quantity) {
                        toMove = quantity;
                    }
                    if (toMove + returnStack.stackSize > returnStack.getMaxStackSize()) {
                        toMove = returnStack.getMaxStackSize() - returnStack.stackSize;
                    }

                    returnStack.stackSize += toMove;
                    slotStack.stackSize -= toMove;
                    quantity -= toMove;
                    if (slotStack.stackSize <= 0) {
                        inventory.setInventorySlotContents(index, null);
                    }
                    inventory.markDirty();
                    if (quantity <= 0) {
                        break;
                    }
                }
            }
        } else {
            if (filter == null) {
                for (int i = 0; i < inventory.getSizeInventory(); i++) {
                    if (((IInventory) inventory).getStackInSlot(i) != null) {
                        if (((IInventory) inventory).getStackInSlot(i).stackSize == 1) {
                            returnStack = ((IInventory) inventory).getStackInSlot(i);
                            ((IInventory) inventory).setInventorySlotContents(i, null);
                            break;
                        } else {
                            returnStack = new ItemStack(((IInventory) inventory).getStackInSlot(i).getItem(), 1);
                            ((IInventory) inventory).setInventorySlotContents(i,
                                    new ItemStack(((IInventory) inventory).getStackInSlot(i).getItem(), ((IInventory) inventory).getStackInSlot(i).stackSize - 1));
                            break;
                        }
                    }
                }
            } else {
                int index;
                int toMove;
                ItemStack slotStack;
                for (int i = 0; i < inventory.getSizeInventory(); i++) {
                    index = i;
                    slotStack = inventory.getStackInSlot(index);
                    if (!doItemStacksMatch(slotStack, filter)) {
                        continue;
                    }
                    if (returnStack == null) {
                        returnStack = new ItemStack(filter.getItem());
                        returnStack.stackSize = 0;
                    }
                    toMove = slotStack.stackSize;
                    if (toMove > quantity) {
                        toMove = quantity;
                    }
                    if (toMove + returnStack.stackSize > returnStack.getMaxStackSize()) {
                        toMove = returnStack.getMaxStackSize() - returnStack.stackSize;
                    }

                    returnStack.stackSize += toMove;
                    slotStack.stackSize -= toMove;
                    quantity -= toMove;
                    if (slotStack.stackSize <= 0) {
                        inventory.setInventorySlotContents(index, null);
                    }
                    inventory.markDirty();
                    if (quantity <= 0) {
                        break;
                    }
                }
            }
        }
        return returnStack;
    }

    /**
     * validates that stacks are the same item / damage / tag, ignores quantity
     * 
     * @param stack1
     * @param stack2
     * @return
     */
    public static boolean doItemStacksMatch(ItemStack stack1, ItemStack stack2) {
        if (stack1 == null) {
            return stack2 == null;
        }
        if (stack2 == null) {
            return stack1 == null;
        }
        if (stack1.getItem() == stack2.getItem() && stack1.getItemDamage() == stack2.getItemDamage() && ItemStack.areItemStackTagsEqual(stack1, stack2)) {
            return true;
        }
        return false;
    }

    public static void dropItemInWorld(World world, ItemStack item, double x, double y, double z) {
        if (item == null || world == null || world.isRemote) {
            return;
        }
        EntityItem entityToSpawn;
        x += world.rand.nextFloat() * 0.6f - 0.3f;
        y += world.rand.nextFloat() * 0.6f + 1 - 0.3f;
        z += world.rand.nextFloat() * 0.6f - 0.3f;
        entityToSpawn = new EntityItem(world, x, y, z, item);
        entityToSpawn.setPosition(x, y, z);
        world.spawnEntityInWorld(entityToSpawn);
    }

    public static void dropInventoryInWorld(World world, IInventory localInventory, double x, double y, double z) {
        if (world.isRemote) {
            return;
        }
        if (localInventory != null) {
            ItemStack stack;
            for (int i = 0; i < localInventory.getSizeInventory(); i++) {
                stack = localInventory.getStackInSlotOnClosing(i);
                if (stack == null) {
                    continue;
                }
                dropItemInWorld(world, stack, x, y, z);
            }
        }
    }

}