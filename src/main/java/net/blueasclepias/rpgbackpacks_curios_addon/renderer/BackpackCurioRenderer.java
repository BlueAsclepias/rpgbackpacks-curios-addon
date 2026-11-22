package net.blueasclepias.rpgbackpacks_curios_addon.renderer;

import com.anchorstudios.rpgbackpacks.items.models.backpack;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.registries.ForgeRegistries;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.client.ICurioRenderer;

public class BackpackCurioRenderer implements ICurioRenderer {

    private final backpack<LivingEntity> model;

    public BackpackCurioRenderer() {
        this.model = new backpack<>(
                Minecraft.getInstance()
                        .getEntityModels()
                        .bakeLayer(backpack.LAYER_LOCATION)
        );
    }

    @Override
    public <T extends LivingEntity, M extends net.minecraft.client.model.EntityModel<T>>
    void render(
            ItemStack stack,
            SlotContext slotContext,
            PoseStack poseStack,
            net.minecraft.client.renderer.entity.RenderLayerParent<T, M> renderLayerParent,
            MultiBufferSource buffer,
            int packedLight,
            float limbSwing,
            float limbSwingAmount,
            float partialTicks,
            float ageInTicks,
            float netHeadYaw,
            float headPitch
    ) {
        LivingEntity entity = slotContext.entity();

        model.setupAnim(
                entity,
                limbSwing,
                limbSwingAmount,
                ageInTicks,
                netHeadYaw,
                headPitch
        );

        ResourceLocation itemId =
                ForgeRegistries.ITEMS.getKey(stack.getItem());

        if (itemId == null) {
            return;
        }

        ResourceLocation texture = ResourceLocation.fromNamespaceAndPath(
                itemId.getNamespace(),
                "textures/item/" + itemId.getPath() + ".png"
        );

        RenderType renderType = RenderType.entityCutoutNoCull(texture);
        VertexConsumer vertexConsumer = buffer.getBuffer(renderType);

        poseStack.pushPose();

        model.renderToBuffer(
                poseStack,
                vertexConsumer,
                packedLight,
                OverlayTexture.NO_OVERLAY,
                1.0F,
                1.0F,
                1.0F,
                1.0F
        );

        poseStack.popPose();
    }
}
