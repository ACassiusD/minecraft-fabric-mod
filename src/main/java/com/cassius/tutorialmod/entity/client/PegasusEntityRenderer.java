package com.cassius.tutorialmod.entity.client;

import com.cassius.tutorialmod.TutorialMod;
import com.cassius.tutorialmod.entity.custom.PegasusEntity;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.equipment.EquipmentModel;
import net.minecraft.client.render.entity.feature.SaddleFeatureRenderer;
import net.minecraft.client.render.entity.model.EntityModelLayers;
import net.minecraft.client.render.entity.model.HorseEntityModel;
import net.minecraft.client.render.entity.model.HorseSaddleEntityModel;
import net.minecraft.util.Identifier;

@Environment(EnvType.CLIENT)
public final class PegasusEntityRenderer extends AbstractPegasusEntityRenderer<PegasusEntity, PegasusEntityRenderState, PegasusEntityModel> {

    private static final Identifier TEXTURE = Identifier.of(TutorialMod.MOD_ID, "textures/entity/pegasus/horse_gray.png");


    public PegasusEntityRenderer(EntityRendererFactory.Context context) {

        //Define the model to use for the entity
        super(
                context, //context: gives access to baked model parts, render layers, etc.
                new PegasusEntityModel(context.getPart(PegasusEntityModel.PEGASUS_LAYER)), //the adult model
                new PegasusEntityModel(context.getPart(PegasusEntityModel.PEGASUS_LAYER)) //the baby model
        );

        //this.addFeature(new HorseMarkingFeatureRenderer(this));

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
        return TEXTURE;
    }

    public PegasusEntityRenderState createRenderState() {
        return new PegasusEntityRenderState();
    }

    @Override
    public void updateRenderState(PegasusEntity pegasusEntity, PegasusEntityRenderState pegasusEntityRenderState, float f) {
        super.updateRenderState(pegasusEntity, pegasusEntityRenderState, f);
        pegasusEntityRenderState.armor = pegasusEntity.getBodyArmor().copy();
        pegasusEntityRenderState.flapEnabled = pegasusEntity.isFlightMode();
    }
}
