/*
 * This file is part of NeptuneVanilla, licensed under the MIT License (MIT).
 *
 * Copyright (c) 2015-2017, Jamie Mansfield <https://github.com/jamierocks>
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package org.neptunepowered.vanilla.factory;

import com.google.common.base.Predicate;
import net.canarymod.api.ai.AIArrowAttack;
import net.canarymod.api.ai.AIAttackOnCollide;
import net.canarymod.api.ai.AIAvoidEntity;
import net.canarymod.api.ai.AIBeg;
import net.canarymod.api.ai.AIBreakDoor;
import net.canarymod.api.ai.AIControlledByPlayer;
import net.canarymod.api.ai.AICreeperSwell;
import net.canarymod.api.ai.AIDefendVillage;
import net.canarymod.api.ai.AIEatGrass;
import net.canarymod.api.ai.AIFindEntityNearest;
import net.canarymod.api.ai.AIFindEntityNearestPlayer;
import net.canarymod.api.ai.AIFleeSun;
import net.canarymod.api.ai.AIFollowGolem;
import net.canarymod.api.ai.AIFollowOwner;
import net.canarymod.api.ai.AIFollowParent;
import net.canarymod.api.ai.AIHarvestFarmland;
import net.canarymod.api.ai.AIHurtByTarget;
import net.canarymod.api.ai.AILeapAtTarget;
import net.canarymod.api.ai.AILookAtTradePlayer;
import net.canarymod.api.ai.AILookAtVillager;
import net.canarymod.api.ai.AILookIdle;
import net.canarymod.api.ai.AIMate;
import net.canarymod.api.ai.AIMoveIndoors;
import net.canarymod.api.ai.AIMoveThroughVillage;
import net.canarymod.api.ai.AIMoveTowardsRestriction;
import net.canarymod.api.ai.AIMoveTowardsTarget;
import net.canarymod.api.ai.AINearestAttackableTarget;
import net.canarymod.api.entity.Entity;
import net.canarymod.api.entity.living.EntityLiving;
import net.canarymod.api.entity.living.IronGolem;
import net.canarymod.api.entity.living.LivingBase;
import net.canarymod.api.entity.living.animal.EntityAnimal;
import net.canarymod.api.entity.living.animal.Tameable;
import net.canarymod.api.entity.living.animal.Wolf;
import net.canarymod.api.entity.living.humanoid.Villager;
import net.canarymod.api.entity.living.monster.Creeper;
import net.canarymod.api.entity.living.monster.EntityMob;
import net.canarymod.api.entity.living.monster.RangedAttackMob;
import net.canarymod.api.factory.AIFactory;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAIArrowAttack;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.ai.EntityAIAvoidEntity;
import net.minecraft.entity.ai.EntityAIBeg;
import net.minecraft.entity.ai.EntityAIBreakDoor;
import net.minecraft.entity.ai.EntityAIControlledByPlayer;
import net.minecraft.entity.ai.EntityAICreeperSwell;
import net.minecraft.entity.ai.EntityAIDefendVillage;
import net.minecraft.entity.ai.EntityAIEatGrass;
import net.minecraft.entity.ai.EntityAIFindEntityNearest;
import net.minecraft.entity.ai.EntityAIFindEntityNearestPlayer;
import net.minecraft.entity.ai.EntityAIFleeSun;
import net.minecraft.entity.ai.EntityAIFollowGolem;
import net.minecraft.entity.ai.EntityAIFollowOwner;
import net.minecraft.entity.ai.EntityAIFollowParent;
import net.minecraft.entity.ai.EntityAIHarvestFarmland;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILeapAtTarget;
import net.minecraft.entity.ai.EntityAILookAtTradePlayer;
import net.minecraft.entity.ai.EntityAILookAtVillager;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIMate;
import net.minecraft.entity.ai.EntityAIMoveIndoors;
import net.minecraft.entity.ai.EntityAIMoveThroughVillage;
import net.minecraft.entity.ai.EntityAIMoveTowardsRestriction;
import net.minecraft.entity.ai.EntityAIMoveTowardsTarget;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.monster.EntityIronGolem;
import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.passive.EntityWolf;

public class NeptuneAIFactory implements AIFactory {

    protected NeptuneAIFactory() {}

    @Override
    public AIArrowAttack newAIArrowAttack(RangedAttackMob mob, double moveSpeed, int attackTimeModifier,
            int maxRangedAttackTime, int maxAttackDistance) {
        return (AIArrowAttack) new EntityAIArrowAttack(null, moveSpeed, attackTimeModifier, maxRangedAttackTime, maxAttackDistance);
    }

    @Override
    public AIAttackOnCollide newAIAttackOnCollide(EntityMob creature, Class<? extends LivingBase> targetClass,
            double moveSpeed, boolean persistant) {
        return (AIAttackOnCollide) new EntityAIAttackOnCollide(
                (EntityCreature) creature,
                (Class<? extends net.minecraft.entity.Entity>) targetClass,
                moveSpeed,
                persistant
        );
    }

    @Override
    public AIAvoidEntity newAIAvoidEntity(EntityMob mob, Predicate predicate, float radius, double farSpeed, double nearSpeed) {
        return (AIAvoidEntity) new EntityAIAvoidEntity((EntityCreature) mob, mob.getClass(), predicate, radius, farSpeed, nearSpeed);
    }

    @Override
    public AIBeg newAIBeg(Wolf wolf, float minBegDistance) {
        return (AIBeg) new EntityAIBeg((EntityWolf) wolf, minBegDistance);
    }

    @Override
    public AIBreakDoor newAIBreakDoor(EntityLiving entity) {
        return (AIBreakDoor) new EntityAIBreakDoor((net.minecraft.entity.EntityLiving) entity);
    }

    @Override
    public AIControlledByPlayer newAIControlledByPlayer(EntityLiving entity, float speed) {
        return (AIControlledByPlayer) new EntityAIControlledByPlayer((net.minecraft.entity.EntityLiving) entity, speed);
    }

    @Override
    public AICreeperSwell newAICreeperSwell(Creeper creeper) {
        return (AICreeperSwell) new EntityAICreeperSwell((EntityCreeper) creeper);
    }

    @Override
    public AIDefendVillage newAIDefendVillage(IronGolem ironGolem) {
        return (AIDefendVillage) new EntityAIDefendVillage((EntityIronGolem) ironGolem);
    }

    @Override
    public AIEatGrass newAIEatGrass(EntityLiving entity) {
        return (AIEatGrass) new EntityAIEatGrass((net.minecraft.entity.EntityLiving) entity);
    }

    @Override
    public AIFindEntityNearest newAIFindEntityNearest(EntityLiving entityLiving, Class<? extends Entity> entityClass) {
        return (AIFindEntityNearest) new EntityAIFindEntityNearest(
                (net.minecraft.entity.EntityLiving) entityLiving,
                (Class<? extends EntityLivingBase>) entityClass
        );
    }

    @Override
    public AIFindEntityNearestPlayer newAIFindEntityNearestPlayer(EntityLiving entityLiving) {
        return (AIFindEntityNearestPlayer) new EntityAIFindEntityNearestPlayer((net.minecraft.entity.EntityLiving) entityLiving);
    }

    @Override
    public AIFleeSun newAIFleeSun(EntityMob mob, double speed) {
        return (AIFleeSun) new EntityAIFleeSun((EntityCreature) mob, speed);
    }

    @Override
    public AIFollowGolem newAIFollowGolem(Villager villager) {
        return (AIFollowGolem) new EntityAIFollowGolem((EntityVillager) villager);
    }

    @Override
    public AIFollowOwner newAIFollowOwner(Tameable entity, double speed, float minDistance, float maxDistance) {
        return (AIFollowOwner) new EntityAIFollowOwner((EntityTameable) entity, speed, minDistance, maxDistance);
    }

    @Override
    public AIFollowParent newAIFollowParent(EntityAnimal animal, double speed) {
        return (AIFollowParent) new EntityAIFollowParent((net.minecraft.entity.passive.EntityAnimal) animal, speed);
    }

    @Override
    public AIHarvestFarmland newAIHarvestFarmland(Villager villager, double speed) {
        return (AIHarvestFarmland) new EntityAIHarvestFarmland((EntityVillager) villager, speed);
    }

    @Override
    public AIHurtByTarget newAIHurtByTarget(EntityMob entity, boolean callForHelp, Class<? extends Entity>... targets) {
        return (AIHurtByTarget) new EntityAIHurtByTarget((EntityCreature) entity, callForHelp, targets);
    }

    @Override
    public AILeapAtTarget newAILeapAtTarget(EntityLiving entity, float leapMotionY) {
        return (AILeapAtTarget) new EntityAILeapAtTarget((net.minecraft.entity.EntityLiving) entity, leapMotionY);
    }

    @Override
    public AILookAtTradePlayer newAILookAtTradePlayer(Villager villager) {
        return (AILookAtTradePlayer) new EntityAILookAtTradePlayer((EntityVillager) villager);
    }

    @Override
    public AILookAtVillager newAILookAtVillager(IronGolem golem) {
        return (AILookAtVillager) new EntityAILookAtVillager((EntityIronGolem) golem);
    }

    @Override
    public AILookIdle newAILookIdle(EntityLiving entity) {
        return (AILookIdle) new EntityAILookIdle((net.minecraft.entity.EntityLiving) entity);
    }

    @Override
    public AIMate newAIMate(EntityAnimal animal, double speed) {
        return (AIMate) new EntityAIMate((net.minecraft.entity.passive.EntityAnimal) animal, speed);
    }

    @Override
    public AIMoveIndoors newAIMoveIndoors(EntityMob entity) {
        return (AIMoveIndoors) new EntityAIMoveIndoors((EntityCreature) entity);
    }

    @Override
    public AIMoveThroughVillage newAIMoveThroughVillage(EntityMob entity, double speed, boolean isNoctournal) {
        return (AIMoveThroughVillage) new EntityAIMoveThroughVillage((EntityCreature) entity, speed, isNoctournal);
    }

    @Override
    public AIMoveTowardsRestriction newAIMoveTowardsRestriction(EntityMob entity, double speed) {
        return (AIMoveTowardsRestriction) new EntityAIMoveTowardsRestriction((EntityCreature) entity, speed);
    }

    @Override
    public AIMoveTowardsTarget newAIMoveTowardsTarget(EntityMob entity, double speed, float maxDistance) {
        return (AIMoveTowardsTarget) new EntityAIMoveTowardsTarget((EntityCreature) entity, speed, maxDistance);
    }

    @Override
    public AINearestAttackableTarget newAINearestAttackableTarget(EntityMob entity, Class<? extends Entity> target,
            int targetChanve, boolean shouldCheckSight, boolean nearbyOnly) {
        return (AINearestAttackableTarget) new EntityAINearestAttackableTarget((EntityCreature) entity, target, targetChanve,
                shouldCheckSight, nearbyOnly, null);
    }

}
