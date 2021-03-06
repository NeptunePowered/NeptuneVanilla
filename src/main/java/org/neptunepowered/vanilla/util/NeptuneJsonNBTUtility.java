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
package org.neptunepowered.vanilla.util;

import com.mojang.authlib.GameProfile;
import net.canarymod.api.nbt.BaseTag;
import net.canarymod.api.nbt.CompoundTag;
import net.canarymod.util.JsonNBTUtility;
import net.minecraft.nbt.JsonToNBT;
import net.minecraft.nbt.NBTException;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTUtil;

public class NeptuneJsonNBTUtility implements JsonNBTUtility {

    @Override
    public BaseTag jsonToNBT(String rawJson) {
        try {
            return (BaseTag) JsonToNBT.getTagFromJson(rawJson);
        } catch (NBTException e) {
            return null;
        }
    }

    @Override
    public String baseTagToJSON(BaseTag baseTag) {
        // wtf, but matches CanaryMod behaviour so meh
        return baseTag.toString();
    }

    @Override
    public GameProfile gameProfileFromNBT(CompoundTag tag) {
        return NBTUtil.readGameProfileFromNBT((NBTTagCompound) tag);
    }

    @Override
    public CompoundTag gameProfileToNBT(GameProfile profile) {
        return (CompoundTag) NBTUtil.writeGameProfile(new NBTTagCompound(), profile);
    }

}
