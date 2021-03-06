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
package org.neptunepowered.vanilla.mixin.core.block;

import net.canarymod.api.entity.Entity;
import net.canarymod.api.world.World;
import net.canarymod.api.world.blocks.BlockBase;
import net.canarymod.api.world.blocks.BlockMaterial;
import net.canarymod.api.world.blocks.MapColor;
import net.canarymod.api.world.position.Position;
import net.canarymod.hook.world.BlockDropXpHook;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.item.EntityXPOrb;
import net.minecraft.util.BlockPos;
import org.neptunepowered.vanilla.util.converter.PositionConverter;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Implements;
import org.spongepowered.asm.mixin.Interface;
import org.spongepowered.asm.mixin.Intrinsic;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

import java.util.Random;

@Mixin(Block.class)
@Implements(@Interface(iface = BlockBase.class, prefix = "block$"))
public abstract class MixinBlock implements BlockBase {

    @Shadow @Final protected Material blockMaterial;
    @Shadow protected boolean needsRandomTick;

    @Shadow public abstract boolean isBlockNormalCube();
    @Shadow public abstract int tickRate(net.minecraft.world.World worldIn);
    @Shadow public abstract int getComparatorInputOverride(net.minecraft.world.World worldIn, BlockPos pos);
    @Shadow public abstract float getExplosionResistance(net.minecraft.entity.Entity exploder);
    @Shadow public abstract boolean isReplaceable(net.minecraft.world.World worldIn, BlockPos pos);
    @Shadow public abstract float getBlockHardness(net.minecraft.world.World worldIn, BlockPos pos);

    /**
     * @author jamierocks - 26th February 2017
     * @reason Fire BlockDropXpHook
     */
    @Overwrite
    protected void dropXpOnBlockBreak(net.minecraft.world.World worldIn, BlockPos pos, int amount) {
        if (!worldIn.isRemote) {
            // Neptune: start
            final Block block = worldIn.getBlockState(pos).getBlock(); // TODO: test
            final BlockDropXpHook hook = (BlockDropXpHook) new BlockDropXpHook((net.canarymod.api.world.blocks.Block) block, amount).call();
            if (hook.isCanceled()) return;
            amount = hook.getXp();
            // Neptune: end

            while (amount > 0) {
                int i = EntityXPOrb.getXPSplit(amount);
                amount -= i;
                worldIn.spawnEntityInWorld(new EntityXPOrb(worldIn, pos.getX() + 0.5D, pos.getY() + 0.5D, pos.getZ() + 0.5D, i));
            }
        }
    }

    @Intrinsic
    public boolean block$isFullBlock() {
        return this.isFullBlock();
    }

    @Intrinsic
    public int block$getLightOpacity() {
        return this.getLightOpacity();
    }

    @Intrinsic
    public int block$getLightValue() {
        return this.getLightValue();
    }

    @Intrinsic
    public boolean block$getUseNeighborBrightness() {
        return this.getUseNeighborBrightness();
    }

    @Override
    public BlockMaterial getMaterial() {
        return (BlockMaterial) this.blockMaterial;
    }

    @Override
    public MapColor getMapColor(net.canarymod.api.world.blocks.Block block) {
        return null;
    }

    @Override
    public boolean isSolidFullCube() {
        return this.isBlockNormalCube();
    }

    @Intrinsic
    public boolean block$isNormalCube() {
        return this.isNormalCube();
    }

    @Intrinsic
    public boolean block$isVisuallyOpaque() {
        return this.isVisuallyOpaque();
    }

    @Intrinsic
    public boolean block$isFullCube() {
        return this.isFullCube();
    }

    @Override
    public boolean isPassable(net.canarymod.api.world.blocks.Block block, Position pos) {
        return false;
    }

    @Intrinsic
    public int block$getRenderType() {
        return this.getRenderType();
    }

    @Override
    public boolean isReplaceable(World worldIn, Position pos) {
        return this.isReplaceable((net.minecraft.world.World) worldIn, PositionConverter.of(pos));
    }

    @Override
    public float getBlockHardness(World worldIn, Position pos) {
        return this.getBlockHardness((net.minecraft.world.World) worldIn, PositionConverter.of(pos));
    }

    @Override
    public boolean ticksRandomly() {
        return this.needsRandomTick;
    }

    @Intrinsic
    public boolean block$hasTileEntity() {
        return this.hasTileEntity();
    }

    @Intrinsic
    public boolean block$isOpaqueCube() {
        return this.isOpaqueCube();
    }

    @Intrinsic
    public boolean block$isCollidable() {
        return this.isCollidable();
    }

    @Override
    public int tickRate(World worldIn) {
        return this.tickRate((net.minecraft.world.World) worldIn);
    }

    @Intrinsic
    public int block$quantityDropped(Random random) {
        return this.quantityDropped(random);
    }

    @Override
    public int damageDropped(net.canarymod.api.world.blocks.Block block) {
        return 0;
    }

    @Override
    public float getExplosionResistance(Entity exploder) {
        return this.getExplosionResistance((net.minecraft.entity.Entity) exploder);
    }

    @Intrinsic
    public double block$getBlockBoundsMinX() {
        return this.getBlockBoundsMinX();
    }

    @Intrinsic
    public double block$getBlockBoundsMaxX() {
        return this.getBlockBoundsMaxX();
    }

    @Intrinsic
    public double block$getBlockBoundsMinY() {
        return this.getBlockBoundsMinY();
    }

    @Intrinsic
    public double block$getBlockBoundsMaxY() {
        return this.getBlockBoundsMaxY();
    }

    @Intrinsic
    public double block$getBlockBoundsMinZ() {
        return this.getBlockBoundsMinZ();
    }

    @Intrinsic
    public double block$getBlockBoundsMaxZ() {
        return this.getBlockBoundsMaxZ();
    }

    @Intrinsic
    public boolean block$canProvidePower() {
        return this.canProvidePower();
    }

    @Intrinsic
    public String block$getLocalizedName() {
        return this.getLocalizedName();
    }

    @Intrinsic
    public boolean block$getEnableStats() {
        return this.getEnableStats();
    }

    @Intrinsic
    public int block$getMobilityFlag() {
        return this.getMobilityFlag();
    }

    @Intrinsic
    public boolean block$requiresUpdates() {
        return this.requiresUpdates();
    }

    @Intrinsic
    public boolean block$hasComparatorInputOverride() {
        return this.hasComparatorInputOverride();
    }

    @Override
    public int getComparatorInputOverride(World worldIn, Position pos) {
        return this.getComparatorInputOverride((net.minecraft.world.World) worldIn, PositionConverter.of(pos));
    }

}
