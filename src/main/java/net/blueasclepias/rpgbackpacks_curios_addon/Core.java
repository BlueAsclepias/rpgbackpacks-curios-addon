package net.blueasclepias.rpgbackpacks_curios_addon;

import com.mojang.logging.LogUtils;
import net.blueasclepias.rpgbackpacks_curios_addon.curio.BackpackCurio;
import net.blueasclepias.rpgbackpacks_curios_addon.network.ModMessages;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.ForgeRegistries;
import org.slf4j.Logger;
import top.theillusivec4.curios.api.CuriosApi;

import static net.minecraft.resources.ResourceLocation.fromNamespaceAndPath;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(Core.MODID)
public class Core
{
    public static final String MODID = "rpgbackpacks_curios_addon";
    private static final Logger LOGGER = LogUtils.getLogger();

    public Core(FMLJavaModLoadingContext context)
    {
        IEventBus modEventBus = context.getModEventBus();
        modEventBus.addListener(this::commonSetup);
        ModMessages.register();
        MinecraftForge.EVENT_BUS.register(this);
    }

    private void commonSetup(final FMLCommonSetupEvent event)
    {
        event.enqueueWork(() -> {
            registerBackpack("leather_backpack");
            registerBackpack("iron_backpack");
            registerBackpack("golden_backpack");
            registerBackpack("diamond_backpack");
            registerBackpack("netherite_backpack");
        });
    }

    private void registerBackpack(String name) {
        ResourceLocation id = fromNamespaceAndPath("rpgbackpacks", name);
        Item item = ForgeRegistries.ITEMS.getValue(id);

        if (item == null) {
            throw new IllegalStateException("Could not find backpack: " + id);
        }

        CuriosApi.registerCurio(item, new BackpackCurio());
    }
}
