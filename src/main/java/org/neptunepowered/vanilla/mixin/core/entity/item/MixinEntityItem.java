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
package org.neptunepowered.vanilla.mixin.core.entity.item;

import net.canarymod.api.entity.EntityType;
import net.canarymod.api.inventory.Item;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import org.neptunepowered.vanilla.mixin.core.entity.MixinEntity;
import org.spongepowered.asm.mixin.Implements;
import org.spongepowered.asm.mixin.Interface;
import org.spongepowered.asm.mixin.Intrinsic;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(EntityItem.class)
@Implements(@Interface(iface = net.canarymod.api.entity.EntityItem.class, prefix = "item$"))
public abstract class MixinEntityItem extends MixinEntity implements net.canarymod.api.entity.EntityItem {

    @Shadow private int age;
    @Shadow private int delayBeforeCanPickup;
    @Shadow private int health;

    @Shadow public abstract ItemStack getEntityItem();
    @Shadow public abstract void setEntityItemStack(ItemStack stack);
    @Shadow public abstract String getOwner();
    @Shadow public abstract void setOwner(String owner);
    @Shadow public abstract String getThrower();
    @Shadow public abstract void setThrower(String thrower);

    @Override
    public void setAge(short age) {
        this.age = age;
    }

    @Override
    public short getAge() {
        return (short) this.age;
    }

    @Override
    public int getPickUpDelay() {
        return this.delayBeforeCanPickup;
    }

    @Override
    public void setPickUpDelay(int delay) {
        this.delayBeforeCanPickup = delay;
    }

    @Override
    public short getHealth() {
        return (short) this.health;
    }

    @Override
    public void setHealth(short health) {
        this.health = health;
    }

    @Override
    public Item getItem() {
        return (Item) this.getEntityItem();
    }

    @Override
    public void setItem(Item item) {
        this.setEntityItemStack((ItemStack) item);
    }

    @Intrinsic
    public String item$getOwner() {
        return this.getOwner();
    }

    @Intrinsic
    public void item$setOwner(String owner) {
        this.setOwner(owner);
    }

    @Intrinsic
    public String item$getThrower() {
        return this.getThrower();
    }

    @Intrinsic
    public void item$setThrower(String thrower) {
        this.setThrower(thrower);
    }

    @Override
    public String getFqName() {
        return "Item";
    }

    @Override
    public boolean isItem() {
        return true;
    }

    @Override
    public EntityType getEntityType() {
        return EntityType.ENTITYITEM;
    }

}
