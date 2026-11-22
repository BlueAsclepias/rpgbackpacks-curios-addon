package net.blueasclepias.rpgbackpacks_curios_addon.handler;

import com.anchorstudios.rpgbackpacks.keybinds.OpenBackpack;
import com.anchorstudios.rpgbackpacks.screen.BackpackMenu;
import net.blueasclepias.rpgbackpacks_curios_addon.Core;
import net.blueasclepias.rpgbackpacks_curios_addon.network.ModMessages;
import net.blueasclepias.rpgbackpacks_curios_addon.network.OpenCurioBackpackPacket;
import net.minecraft.client.Minecraft;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.lwjgl.glfw.GLFW;

@Mod.EventBusSubscriber(
        modid = Core.MODID,
        value = Dist.CLIENT,
        bus = Mod.EventBusSubscriber.Bus.FORGE
)
public class BackpackKeyHandler {
    @SubscribeEvent
    public static void onKeyInput(InputEvent.Key event) {
        if (event.getAction() != GLFW.GLFW_PRESS) {
            return;
        }

        Minecraft minecraft = Minecraft.getInstance();

        if (minecraft.player == null) return;

        boolean backpackOpen = minecraft.player.containerMenu instanceof BackpackMenu;

        if (minecraft.screen != null && !backpackOpen) return;

        if (event.getKey() == OpenBackpack.OPEN_BACKPACK.getKey().getValue()
                && event.getAction() == GLFW.GLFW_PRESS) {
            ModMessages.sendToServer(new OpenCurioBackpackPacket());
        }
    }
}
