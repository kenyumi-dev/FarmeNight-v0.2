package fr.shadou.farmenight.production.arme;

import fr.shadou.farmenight.Gestion;
import fr.shadou.farmenight.Instas;
import fr.shadou.farmenight.init.ModBlock;
import fr.shadou.farmenight.init.ModContainer;
import fr.shadou.farmenight.init.ModItem;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.button.Button;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.IWorldPosCallable;
import net.minecraft.util.text.TranslationTextComponent;

import javax.annotation.Nullable;

public class TableContainer extends Container {

    private final IInventory inputInventory;
    private final IWorldPosCallable worldPosCallable;

    public TableContainer(int windowIdIn, PlayerInventory playerInventoryIn) {
        this(windowIdIn, playerInventoryIn, new TileEntityArme(), IWorldPosCallable.NULL);
    }

    protected TableContainer(int windowIdIn, PlayerInventory playerInventoryIn, IInventory inventory, IWorldPosCallable worldPosCallable) {
        super(ModContainer.TABLE_CONTAINER.get(), windowIdIn);

        this.inputInventory = inventory;
        this.worldPosCallable = worldPosCallable;

        //Crosse
        this.addSlot(new Slot(this.inputInventory, 0, 10, 33) {
            @Override
            public boolean mayPlace(ItemStack p_75214_1_) {
                return super.mayPlace(Instas.ItemStackCustom(ModItem.CROSSE));
            }
        });
        //GardeMain
        this.addSlot(new Slot(this.inputInventory, 1, 57, 26) {
            @Override
            public boolean mayPlace(ItemStack p_75214_1_) {
                return super.mayPlace(Instas.ItemStackCustom(ModItem.GARDEMAIN));
            }
        });
        //Charguer
        this.addSlot(new Slot(this.inputInventory, 2,104,23){
            @Override
            public boolean mayPlace(ItemStack p_75214_1_) {
                return super.mayPlace(Instas.ItemStackCustom(ModItem.CHARGEUR));
            }
        });
        //Canon
        this.addSlot(new Slot(this.inputInventory, 3,84,49){
            @Override
            public boolean mayPlace(ItemStack p_75214_1_) {
                return super.mayPlace(Instas.ItemStackCustom(ModItem.CANON));
            }
        });
        //ArmeResult
        this.addSlot(new Slot(this.inputInventory, 4,151,63){
            @Override
            public boolean mayPlace(ItemStack p_75214_1_) {
                return false;
            }

            @Override
            public ItemStack onTake(PlayerEntity p_190901_1_, ItemStack p_190901_2_) {
                return super.onTake(p_190901_1_, p_190901_2_);
            }
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

    @Override
    public boolean stillValid(PlayerEntity p_75145_1_) {
        return this.inputInventory.stillValid(p_75145_1_);
    }

    

    @Override
    public boolean clickMenuButton(PlayerEntity p_75140_1_, int p_75140_2_) {
        return super.clickMenuButton(p_75140_1_, p_75140_2_);
    }

    @Override
    public void setItem(int p_75141_1_, ItemStack p_75141_2_) {
        super.setItem(p_75141_1_, p_75141_2_);
    }

    @Override
    public ItemStack quickMoveStack(PlayerEntity playerIn, int index) {
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
