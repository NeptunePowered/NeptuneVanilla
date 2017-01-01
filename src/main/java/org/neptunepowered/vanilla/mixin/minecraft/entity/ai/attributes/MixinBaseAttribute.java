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
package org.neptunepowered.vanilla.mixin.minecraft.entity.ai.attributes;

import net.canarymod.api.attributes.Attribute;
import net.minecraft.entity.ai.attributes.BaseAttribute;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Implements;
import org.spongepowered.asm.mixin.Interface;
import org.spongepowered.asm.mixin.Intrinsic;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(BaseAttribute.class)
@Implements(@Interface(iface = Attribute.class, prefix = "attribute$"))
public abstract class MixinBaseAttribute implements Attribute {

    @Shadow @Final private String unlocalizedName;
    @Shadow private boolean shouldWatch;

    @Shadow public abstract BaseAttribute shadow$setShouldWatch(boolean shouldWatchIn);
    @Shadow public abstract double getDefaultValue();

    @Override
    public String getInternalName() {
        return this.unlocalizedName;
    }

    @Intrinsic
    public double attribute$getDefaultValue() {
        return this.getDefaultValue();
    }

    @Override
    public boolean shouldWatch() {
        return this.shouldWatch;
    }

    @Override
    public Attribute setShouldWatch(boolean watch) {
        return (Attribute) this.shadow$setShouldWatch(watch);
    }

}
