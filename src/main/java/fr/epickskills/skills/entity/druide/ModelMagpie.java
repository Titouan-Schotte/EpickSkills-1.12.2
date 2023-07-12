package fr.epickskills.skills.entity.druide;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;

public class ModelMagpie extends ModelBase {
  ModelRenderer Tail;
  
  ModelRenderer Body;
  
  ModelRenderer Head;
  
  ModelRenderer UpperBeak;
  
  ModelRenderer LeftWing;
  
  ModelRenderer RightWing;
  
  ModelRenderer LowerBeak;
  
  ModelRenderer LeftLeg;
  
  ModelRenderer RightFoot;
  
  ModelRenderer LeftAnkle;
  
  ModelRenderer RightLeg;
  
  ModelRenderer RightAnkle;
  
  ModelRenderer LeftFoot;
  
  public ModelMagpie() {
    this.textureHeight = 32;
    this.textureWidth = 32;
    this.Tail = new ModelRenderer(this, 0, 19);
    this.Tail.addBox(-1.5F, 0.0F, 0.0F, 3, 1, 8);
    this.Tail.setRotationPoint(0.0F, 20.7F, 5.0F);
    setRotation(this.Tail, -0.2094395F, 0.0F, 0.0F);
    this.Body = new ModelRenderer(this, 0, 0);
    this.Body.addBox(-2.0F, -2.0F, 0.0F, 4, 4, 7);
    this.Body.setRotationPoint(0.0F, 18.2F, 0.0F);
    setRotation(this.Body, -0.5235988F, 0.0F, 0.0F);
    this.Head = new ModelRenderer(this, 14, 11);
    this.Head.addBox(-1.5F, -2.8F, -1.0F, 3, 4, 3);
    this.Head.setRotationPoint(0.0F, 18.2F, 0.0F);
    setRotation(this.Head, 0.2617994F, 0.0F, 0.0F);
    this.UpperBeak = new ModelRenderer(this, 22, 0);
    this.UpperBeak.addBox(-1.0F, -2.5F, -2.5F, 2, 1, 3);
    this.UpperBeak.setRotationPoint(0.0F, 18.2F, 0.0F);
    setRotation(this.UpperBeak, 0.4014257F, 0.0F, 0.0F);
    this.LeftWing = new ModelRenderer(this, 0, 4);
    this.LeftWing.addBox(0.0F, 0.0F, 0.0F, 0, 3, 7);
    this.LeftWing.setRotationPoint(2.0F, 17.2F, 1.0F);
    setRotation(this.LeftWing, -0.2094395F, -0.0349066F, -0.2617994F);
    this.RightWing = new ModelRenderer(this, 0, 8);
    this.RightWing.addBox(0.0F, 0.0F, 0.0F, 0, 3, 7);
    this.RightWing.setRotationPoint(-2.0F, 17.2F, 1.0F);
    setRotation(this.RightWing, -0.2094395F, 0.0349066F, 0.2617994F);
    this.LowerBeak = new ModelRenderer(this, 22, 4);
    this.LowerBeak.addBox(-1.0F, -1.5F, -3.2F, 2, 1, 3);
    this.LowerBeak.setRotationPoint(0.0F, 18.2F, 0.0F);
    setRotation(this.LowerBeak, 0.0523599F, 0.0F, 0.0F);
    this.LeftLeg = new ModelRenderer(this, 26, 8);
    this.LeftLeg.addBox(-0.5F, 0.3F, -0.7F, 1, 2, 1);
    this.LeftLeg.setRotationPoint(1.0F, 20.8F, 2.0F);
    setRotation(this.LeftLeg, 0.6108652F, 0.0F, 0.0F);
    this.RightFoot = new ModelRenderer(this, 17, 22);
    this.RightFoot.addBox(-1.0F, 3.2F, -1.6F, 2, 0, 3);
    this.RightFoot.setRotationPoint(-1.0F, 20.8F, 2.0F);
    setRotation(this.RightFoot, 0.0F, 0.0F, 0.0F);
    this.LeftAnkle = new ModelRenderer(this, 16, 3);
    this.LeftAnkle.addBox(-0.5F, 1.2F, 0.8F, 1, 2, 1);
    this.LeftAnkle.setRotationPoint(1.0F, 20.8F, 2.0F);
    setRotation(this.LeftAnkle, -0.4461433F, 0.0F, 0.0F);
    this.RightLeg = new ModelRenderer(this, 22, 8);
    this.RightLeg.addBox(-0.5F, 0.3F, -0.7F, 1, 2, 1);
    this.RightLeg.setRotationPoint(-1.0F, 20.8F, 2.0F);
    setRotation(this.RightLeg, 0.6108652F, 0.0F, 0.0F);
    this.RightAnkle = new ModelRenderer(this, 16, 0);
    this.RightAnkle.addBox(-0.5F, 1.2F, 0.8F, 1, 2, 1);
    this.RightAnkle.setRotationPoint(-1.0F, 20.8F, 2.0F);
    setRotation(this.RightAnkle, -0.4461433F, 0.0F, 0.0F);
    this.LeftFoot = new ModelRenderer(this, 17, 19);
    this.LeftFoot.addBox(-1.0F, 3.2F, -1.6F, 2, 0, 3);
    this.LeftFoot.setRotationPoint(1.0F, 20.8F, 2.0F);
    setRotation(this.LeftFoot, 0.0F, 0.0F, 0.0F);
  }

  @Override
  public void render(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor) {
    super.render(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scaleFactor);

    setAnimation(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scaleFactor, entity);
      this.Tail.render(scaleFactor);
      this.Body.render(scaleFactor);
      this.Head.render(scaleFactor);
      this.UpperBeak.render(scaleFactor);
      this.LeftWing.render(scaleFactor);
      this.RightWing.render(scaleFactor);
      this.LowerBeak.render(scaleFactor);
      this.LeftLeg.render(scaleFactor);
      this.RightFoot.render(scaleFactor);
      this.LeftAnkle.render(scaleFactor);
      this.RightLeg.render(scaleFactor);
      this.RightAnkle.render(scaleFactor);
      this.LeftFoot.render(scaleFactor);

  }
  
  private void setRotation(ModelRenderer model, float x, float y, float z) {
    model.rotateAngleX = x;
    model.rotateAngleY = y;
    model.rotateAngleZ = z;
  }
  
  public void setAnimation(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor, Entity entity) {
    this.RightWing.rotateAngleY = ageInTicks + 0.2617994F;
    this.LeftWing.rotateAngleY = -ageInTicks - 0.2617994F;
  }
}
