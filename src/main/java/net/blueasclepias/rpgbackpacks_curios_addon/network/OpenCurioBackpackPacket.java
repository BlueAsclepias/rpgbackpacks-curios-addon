package net.blueasclepias.rpgbackpacks_curios_addon.network;

import com.anchorstudios.rpgbackpacks.items.BackpackItem;
import com.anchorstudios.rpgbackpacks.screen.BackpackMenu;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.network.NetworkEvent;
import top.theillusivec4.curios.api.CuriosApi;

import java.util.function.Supplier;

public class OpenCurioBackpackPacket {

    public OpenCurioBackpackPacket() {
    }

    public OpenCurioBackpackPacket(FriendlyByteBuf buffer) {
    }

    public void encode(FriendlyByteBuf buffer) {
    }

    public void handle(Supplier<NetworkEvent.Context> supplier) {
        NetworkEvent.Context context = supplier.get();

        context.enqueueWork(() -> {
            ServerPlayer player = context.getSender();

            if (player == null) {
                return;
            }

            if (player.containerMenu instanceof BackpackMenu) {
                player.closeContainer();
                return;
            }

            if (player.containerMenu != player.inventoryMenu) {
                return;
            }

            CuriosApi.getCuriosInventory(player).ifPresent(curios -> {
                curios.getStacksHandler("back").ifPresent(stacksHandler -> {

                    for (int i = 0; i < stacksHandler.getSlots(); i++) {
                        ItemStack stack =
                                stacksHandler.getStacks().getStackInSlot(i);

                        if (stack.getItem() instanceof BackpackItem) {
                            BackpackItem.openScreen(player, stack);
                            return;
                        }
                    }
                });
            });
        });

        context.setPacketHandled(true);
    }
}
