package net.blueasclepias.rpgbackpacks_curios_addon.network;

import net.blueasclepias.rpgbackpacks_curios_addon.Core;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.simple.SimpleChannel;

public class ModMessages {

    private static final String PROTOCOL_VERSION = "1";

    private static final SimpleChannel CHANNEL =
            NetworkRegistry.newSimpleChannel(
                    ResourceLocation.fromNamespaceAndPath(
                            Core.MODID,
                            "main"
                    ),
                    () -> PROTOCOL_VERSION,
                    PROTOCOL_VERSION::equals,
                    PROTOCOL_VERSION::equals
            );

    private static int packetId = 0;

    public static void register() {
        CHANNEL.registerMessage(
                packetId++,
                OpenCurioBackpackPacket.class,
                OpenCurioBackpackPacket::encode,
                OpenCurioBackpackPacket::new,
                OpenCurioBackpackPacket::handle
        );
    }

    public static void sendToServer(Object message) {
        CHANNEL.sendToServer(message);
    }
}
