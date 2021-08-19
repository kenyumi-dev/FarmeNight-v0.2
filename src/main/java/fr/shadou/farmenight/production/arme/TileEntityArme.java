package fr.shadou.farmenight.production.arme;

import fr.shadou.farmenight.crafting.recipe.AreRecipe;
import fr.shadou.farmenight.crafting.recipe.ModRecipes;
import fr.shadou.farmenight.init.ModBlock;
import fr.shadou.farmenight.init.ModTileEntity;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.inventory.container.Container;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.PacketBuffer;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.LockableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.Direction;
import net.minecraft.util.IIntArray;
import net.minecraft.util.NonNullList;
import net.minecraft.util.text.ITextComponent;
import net.minecraftforge.fluids.FluidStack;

import javax.annotation.Nullable;

public class TileEntityArme extends LockableTileEntity implements ISidedInventory,ITickableTileEntity {

    private static final int[] SLOTS_FOR_UP = new int[]{0, 1, 2, 3, 4, 5}; // Ingredients
    private NonNullList<ItemStack> items = NonNullList.withSize(9, ItemStack.EMPTY);
    protected final IRecipeType<AreRecipe> recipeAre;

    public TileEntityArme() {
        super(ModTileEntity.TILEENTITY_ARME.get());
        this.recipeAre = ModRecipes.ARME;
    }

    @Override
    public void load(BlockState state, CompoundNBT tag) {
        super.load(state, tag);
        this.items = NonNullList.withSize(this.getContainerSize(), ItemStack.EMPTY);
        ItemStackHelper.loadAllItems(tag, this.items);
    }

    @Override
    public CompoundNBT save(CompoundNBT tag) {
        tag = super.save(tag);
        ItemStackHelper.saveAllItems(tag, this.items);
        return tag;
    }

    @Override
    public void tick() {
            NonNullList<ItemStack> stacks = NonNullList.withSize(6, ItemStack.EMPTY);
            for (int i = 0; i < 4; i++) {
                stacks.set(i, this.items.get(i));
            }
            if (!stacks.isEmpty()) {
                IRecipe<IInventory> recipe = this.level.getRecipeManager().getRecipeFor(this.recipeAre, this, this.level).orElse(null);
                /*if (this.canAssemblage(recipe)) {
                        this.doAssemblage(recipe);
                }*/
            }
    }

    protected boolean canAssemblage(@Nullable IRecipe<IInventory> recipe){
        NonNullList<ItemStack> stacks = NonNullList.withSize(4, ItemStack.EMPTY);
        for (int i = 0; i < 4; i++) {
            stacks.set(i, this.items.get(i));
        }
        if ((!stacks.isEmpty() && recipe != null) ) {
            ItemStack itemstack = recipe.assemble(this);
            if (itemstack.isEmpty()) {
                return false;
            } else {
                ItemStack itemstack1 = this.items.get(4);
                if (itemstack1.isEmpty()) {
                    return true;
                } else if (!itemstack1.sameItem(itemstack)) {
                    return false;
                } else if (itemstack1.getCount() + itemstack.getCount() <= this.getMaxStackSize() && itemstack1.getCount() + itemstack.getCount() <= itemstack1.getMaxStackSize()) { // Forge fix: make furnace respect stack sizes in furnace recipes
                    return true;
                } else {
                    return itemstack1.getCount() + itemstack.getCount() <= itemstack.getMaxStackSize(); // Forge fix: make furnace respect stack sizes in furnace recipes
                }
            }
        } else {
            return false;
        }
    }

    protected void doAssemblage(@Nullable IRecipe<IInventory> recipe){
        if (recipe != null && this.canAssemblage(recipe)) {
            NonNullList<ItemStack> stacks = NonNullList.withSize(4, ItemStack.EMPTY);
            for (int i = 0; i < 4; i++) {
                stacks.set(i, this.items.get(i));
            }
            ItemStack itemstack1 = recipe.assemble(this);
            ItemStack itemstack2 = this.items.get(5);
            if (itemstack2.isEmpty()) {
                this.items.set(5, itemstack1.copy());
            } else if (itemstack2.getItem() == itemstack1.getItem()) {
                itemstack2.grow(itemstack1.getCount());
            }
            for (ItemStack stack : stacks) {
                if(!stack.isEmpty()) {
                    stack.shrink(1);
                }
            }
        }
    }

    @Override
    protected ITextComponent getDefaultName() {
        return null;
    }

    @Override
    protected Container createMenu(int p_213906_1_, PlayerInventory p_213906_2_) {
        return new TableContainer(p_213906_1_, p_213906_2_);
    }

    @Override
    public int getContainerSize() {
        return this.items.size();
    }

    @Override
    public boolean isEmpty() {
        for(ItemStack itemstack : this.items) {
            if (!itemstack.isEmpty()) {
                return false;
            }
        }
        return true;
    }

    @Override
    public ItemStack getItem(int slot) {
        return slot >= 0 && slot < this.items.size() ? this.items.get(slot) : ItemStack.EMPTY;
    }

    @Override
    public ItemStack removeItem(int slot, int amount) {
        return ItemStackHelper.removeItem(this.items, slot, amount);
    }

    @Override
    public ItemStack removeItemNoUpdate(int slot) {
        return ItemStackHelper.takeItem(this.items, slot);
    }

    @Override
    public void setItem(int slot, ItemStack stack) {
        if (slot >= 0 && slot < this.items.size()) {
            this.items.set(slot, stack);
        }
    }

    @Override
    public boolean stillValid(PlayerEntity player) {
        assert this.level != null;
        if (this.level.getBlockEntity(this.worldPosition) != this) {
            return false;
        } else {
            return !(player.distanceToSqr((double)this.worldPosition.getX() + 0.5D, (double)this.worldPosition.getY() + 0.5D, (double)this.worldPosition.getZ() + 0.5D) > 64.0D);
        }
    }

    @Override
    public void clearContent() {
        this.items.clear();
    }

    @Override
    public int[] getSlotsForFace(Direction p_180463_1_) {
        return new int[0];
    }

    @Override
    public boolean canPlaceItemThroughFace(int p_180462_1_, ItemStack p_180462_2_, @Nullable Direction p_180462_3_) {
        return false;
    }

    @Override
    public boolean canTakeItemThroughFace(int p_180461_1_, ItemStack p_180461_2_, Direction p_180461_3_) {
        return false;
    }
}

