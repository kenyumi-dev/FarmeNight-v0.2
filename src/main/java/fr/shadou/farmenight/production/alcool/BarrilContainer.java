package fr.shadou.farmenight.production.alcool;

import fr.shadou.farmenight.init.ModBlock;
import fr.shadou.farmenight.init.ModContrainer;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IWorldPosCallable;

public class BarrilContainer extends Container {

    private final IInventory inputInventory = new Inventory(2);
    private final IWorldPosCallable worldPosCallable;

    public BarrilContainer(int id, PlayerInventory inventory) {
        this(id, inventory, IWorldPosCallable.NULL);
    }

    public BarrilContainer(int windowIdIn, PlayerInventory playerInventoryIn, final IWorldPosCallable worldPosCallableIn) {
        super(ModContrainer.BARRIL_CONTRAINER.get(), windowIdIn);
        this.worldPosCallable = worldPosCallableIn;

        //80 19 correspond a la position de l'un des slots se trouvant sur la texture de l'interface
        this.addSlot(new Slot(this.inputInventory, 0, 80, 19) {

        });

        //80 48 correspond a la position du deuxième slot se trouvant sur la texture de l'interface
        this.addSlot(new Slot(this.inputInventory, 1, 80, 48) {

        });

        for(int i = 0; i < 3; ++i) {
            for(int j = 0; j < 9; ++j) {
                this.addSlot(new Slot(playerInventoryIn, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
            }
        }

        for(int k = 0; k < 9; ++k) {
            this.addSlot(new Slot(playerInventoryIn, k, 8 + k * 18, 142));
        }
    }

    public boolean stillValid(PlayerEntity playerIn) {
        return stillValid(this.worldPosCallable, playerIn, ModBlock.BARRIL_VIN.get());
    }

    public void onContainerClosed(PlayerEntity playerIn) {
        super.removed(playerIn);
        this.worldPosCallable.execute((world, pos) -> {
            this.clearContainer(playerIn, world, this.inputInventory);
        });
    }

    public ItemStack transferStackInSlot(PlayerEntity playerIn, int index) {
        ItemStack itemstack = ItemStack.EMPTY;
        Slot slot = this.slots.get(index);
        if (slot != null && slot.hasItem()) {
            ItemStack itemstack1 = slot.getItem();
            itemstack = itemstack1.copy();
            if (index != 0 && index != 1) {
                if (index >= 2 && index < 38) {
                    if (!this.moveItemStackTo(itemstack1, 0, 2, false)) {
                        return ItemStack.EMPTY;
                    }
                }
            } else if (!this.moveItemStackTo(itemstack1, 2, 38, false)) {
                return ItemStack.EMPTY;
            }

            if (itemstack1.isEmpty()) {
                slot.set(ItemStack.EMPTY);
            } else {
                slot.setChanged();
            }

            if (itemstack1.getCount() == itemstack.getCount()) {
                return ItemStack.EMPTY;
            }

            slot.onTake(playerIn, itemstack1);
        }

        return itemstack;


    }
}