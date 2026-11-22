package net.blueasclepias.rpgbackpacks_curios_addon.client;

import com.anchorstudios.rpgbackpacks.items.BackpackItems;
import net.blueasclepias.rpgbackpacks_curios_addon.Core;
import net.blueasclepias.rpgbackpacks_curios_addon.renderer.BackpackCurioRenderer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import top.theillusivec4.curios.api.client.CuriosRendererRegistry;

@Mod.EventBusSubscriber(
        modid = Core.MODID,
        bus = Mod.EventBusSubscriber.Bus.MOD,
        value = Dist.CLIENT
)
public class BackpackCurioClientEvents {

    @SubscribeEvent
    public static void onClientSetup(FMLClientSetupEvent event) {
        event.enqueueWork(() -> {
            CuriosRendererRegistry.register(
                    BackpackItems.LEATHER_BACKPACK.get(),
                    BackpackCurioRenderer::new
            );

            CuriosRendererRegistry.register(
                    BackpackItems.IRON_BACKPACK.get(),
                    BackpackCurioRenderer::new
            );

            CuriosRendererRegistry.register(
                    BackpackItems.GOLDEN_BACKPACK.get(),
                    BackpackCurioRenderer::new
            );

            CuriosRendererRegistry.register(
                    BackpackItems.DIAMOND_BACKPACK.get(),
                    BackpackCurioRenderer::new
            );

            CuriosRendererRegistry.register(
                    BackpackItems.NETHERITE_BACKPACK.get(),
                    BackpackCurioRenderer::new
            );
        });
    }
}