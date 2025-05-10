package com.cassius.tutorialmod.entity.client;

import com.cassius.tutorialmod.entity.custom.PegasusEntity;
import com.google.common.collect.Maps;
import java.util.Map;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.equipment.EquipmentModel;
import net.minecraft.client.render.entity.feature.HorseMarkingFeatureRenderer;
import net.minecraft.client.render.entity.feature.SaddleFeatureRenderer;
import net.minecraft.client.render.entity.model.EntityModelLayers;
import net.minecraft.client.render.entity.model.HorseEntityModel;
import net.minecraft.client.render.entity.model.HorseSaddleEntityModel;
import net.minecraft.entity.passive.HorseColor;
import net.minecraft.util.Identifier;

@Environment(EnvType.CLIENT)
public final class PegasusEntityRenderer extends AbstractPegasusEntityRenderer<PegasusEntity, PegasusEntityRenderState, PegasusEntityModel> {
    private static final Map<HorseColor, Identifier> TEXTURES = Maps.newEnumMap(
            Map.of(
                    HorseColor.WHITE,
                    Identifier.ofVanilla("textures/entity/horse/horse_white.png"),
                    HorseColor.CREAMY,
                    Identifier.ofVanilla("textures/entity/horse/horse_creamy.png"),
                    HorseColor.CHESTNUT,
                    Identifier.ofVanilla("textures/entity/horse/horse_chestnut.png"),
                    HorseColor.BROWN,
                    Identifier.ofVanilla("textures/entity/horse/horse_brown.png"),
                    HorseColor.BLACK,
                    Identifier.ofVanilla("textures/entity/horse/horse_black.png"),
                    HorseColor.GRAY,
                    Identifier.ofVanilla("textures/entity/horse/horse_gray.png"),
                    HorseColor.DARK_BROWN,
                    Identifier.ofVanilla("textures/entity/horse/horse_darkbrown.png")
            )
    );

    public PegasusEntityRenderer(EntityRendererFactory.Context context) {
        super(context, new PegasusEntityModel(context.getPart(EntityModelLayers.HORSE)), new PegasusEntityModel(context.getPart(EntityModelLayers.HORSE_BABY)));
//        this.addFeature(new HorseMarkingFeatureRenderer(this));
        this.addFeature(
                new SaddleFeatureRenderer<>(
                        this,
                        context.getEquipmentRenderer(),
                        EquipmentModel.LayerType.HORSE_BODY,
                        horseEntityRenderState -> horseEntityRenderState.armor,
                        new HorseEntityModel(context.getPart(EntityModelLayers.HORSE_ARMOR)),
                        new HorseEntityModel(context.getPart(EntityModelLayers.HORSE_ARMOR_BABY))
                )
        );
        this.addFeature(
                new SaddleFeatureRenderer<>(
                        this,
                        context.getEquipmentRenderer(),
                        EquipmentModel.LayerType.HORSE_SADDLE,
                        horseEntityRenderState -> horseEntityRenderState.saddleStack,
                        new HorseSaddleEntityModel(context.getPart(EntityModelLayers.HORSE_SADDLE)),
                        new HorseSaddleEntityModel(context.getPart(EntityModelLayers.HORSE_BABY_SADDLE))
                )
        );
    }

    public Identifier getTexture(PegasusEntityRenderState pegasusEntityRenderState) {
        return (Identifier)TEXTURES.get(pegasusEntityRenderState.color);
    }

    public PegasusEntityRenderState createRenderState() {
        return new PegasusEntityRenderState();
    }

    public void updateRenderState(PegasusEntity pegasusEntity, PegasusEntityRenderState pegasusEntityRenderState, float f) {
        super.updateRenderState(pegasusEntity, pegasusEntityRenderState, f);
//        pegasusEntityRenderState.color = pegasusEntity.getHorseColor();
//        pegasusEntityRenderState.marking = pegasusEntity.getMarking();
        pegasusEntityRenderState.armor = pegasusEntity.getBodyArmor().copy();
    }
}
