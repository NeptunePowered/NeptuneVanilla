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
import net.canarymod.api.entity.vehicle.ChestMinecart;
import net.canarymod.api.inventory.InventoryType;
import net.minecraft.entity.item.EntityMinecartChest;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(EntityMinecartChest.class)
public abstract class MixinEntityMinecartChest extends MixinEntityMinecartContainer implements ChestMinecart {

    @Override
    public InventoryType getInventoryType() {
        return InventoryType.MINECART_CHEST;
    }

    @Override
    public EntityType getEntityType() {
        return EntityType.CHESTMINECART;
    }

    @Override
    public String getFqName() {
        return "ChestMinecart";
    }

}
