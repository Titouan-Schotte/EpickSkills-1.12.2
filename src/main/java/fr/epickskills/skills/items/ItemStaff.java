package fr.epickskills.skills.items;

import fr.epickskills.skills.Main;
import fr.epickskills.skills.entity.EntityAssassinSkill;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatList;
import net.minecraft.util.*;
import net.minecraft.world.World;

import java.util.Objects;

public class ItemStaff extends Item {
    private EnumParticleTypes particleTrail;
    private EnumParticleTypes particleCollision;
    private int damages;
    private int cooldown;

    public ItemStaff(String name, int cooldown, int damages, EnumParticleTypes particleTrail, EnumParticleTypes particleCollision) {
        setUnlocalizedName(name);
        setRegistryName(name);
        setCreativeTab(Main.creativeTab);
        this.damages = damages;
        this.particleCollision = particleCollision;
        this.particleTrail = particleTrail;
        this.cooldown = cooldown;
    }


    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {

        playerIn.getCooldownTracker().setCooldown(this, cooldown);
        ItemStack itemstack = playerIn.getHeldItem(handIn);

        worldIn.playSound((EntityPlayer) null, playerIn.posX, playerIn.posY, playerIn.posZ, SoundEvents.EVOCATION_ILLAGER_CAST_SPELL, SoundCategory.NEUTRAL, 0.5F, 0.4F / (itemRand.nextFloat() * 0.4F + 0.8F));

        if (!worldIn.isRemote) {
            EntityAssassinSkill entityBasicStaff = new EntityAssassinSkill(worldIn, playerIn, 10);
            entityBasicStaff.shoot(playerIn, playerIn.rotationPitch, playerIn.rotationYaw, 0.0F, 1.5F, 1.0F);
            worldIn.spawnEntity(entityBasicStaff);
        }

        playerIn.addStat(Objects.requireNonNull(StatList.getObjectUseStats(this)));
        return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, itemstack);
    }

}
