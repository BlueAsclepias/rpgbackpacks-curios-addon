package net.blueasclepias.rpgbackpacks_curios_addon.mixin;

import com.anchorstudios.rpgbackpacks.items.BackpackItem;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(BackpackItem.class)
public abstract class BackpackItemMixin {

    public boolean canEquip(
            ItemStack stack,
            EquipmentSlot armorType,
            Entity entity
    ) {
        return false;
    }
}

