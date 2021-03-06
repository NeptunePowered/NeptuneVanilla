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
package org.neptunepowered.vanilla.mixin.core.scoreboard;

import net.canarymod.api.entity.living.humanoid.Player;
import net.canarymod.api.scoreboard.ScoreObjectiveCriteria;
import net.canarymod.api.scoreboard.ScorePosition;
import net.canarymod.api.scoreboard.Scoreboard;
import net.canarymod.api.world.World;
import net.minecraft.scoreboard.IScoreObjectiveCriteria;
import net.minecraft.scoreboard.ScoreObjective;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Implements;
import org.spongepowered.asm.mixin.Interface;
import org.spongepowered.asm.mixin.Intrinsic;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(ScoreObjective.class)
@Implements(@Interface(iface = net.canarymod.api.scoreboard.ScoreObjective.class, prefix = "objective$"))
public abstract class MixinScoreObjective implements net.canarymod.api.scoreboard.ScoreObjective {

    @Shadow @Final private net.minecraft.scoreboard.Scoreboard theScoreboard;
    @Shadow @Final private IScoreObjectiveCriteria objectiveCriteria;
    @Shadow @Final private String name;

    @Shadow public abstract String getDisplayName();
    @Shadow public abstract void setDisplayName(String nameIn);

    @Override
    public String getProtocolName() {
        return this.name;
    }

    @Override
    public ScoreObjectiveCriteria getScoreObjectiveCriteria() {
        return (ScoreObjectiveCriteria) this.objectiveCriteria;
    }

    @Intrinsic
    public String objective$getDisplayName() {
        return this.getDisplayName();
    }

    @Intrinsic
    public void objective$setDisplayName(String name) {
        this.setDisplayName(name);
    }

    @Override
    public void setScoreboardPosition(ScorePosition type) {
        this.getScoreboard().setScoreboardPosition(type, this);
    }

    @Override
    public void setScoreboardPosition(ScorePosition type, Player player) {
        this.getScoreboard().setScoreboardPosition(type, this, player);
    }

    @Override
    public void setScoreboardPosition(ScorePosition type, World world) {
        this.getScoreboard().setScoreboardPosition(type, this, world);
    }

    @Override
    public Scoreboard getScoreboard() {
        return (Scoreboard) this.theScoreboard;
    }
    
}
