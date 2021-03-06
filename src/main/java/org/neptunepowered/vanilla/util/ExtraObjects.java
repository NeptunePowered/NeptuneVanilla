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

import java.util.function.Consumer;
import java.util.function.Supplier;

public final class ExtraObjects {

    public static <R> R nullable(final Object obj, final Supplier<R> function) {
        if (obj == null) return null;
        return function.get();
    }

    public static <R> void nullable(final R obj, final Consumer<R> function) {
        if (obj == null) return;
        function.accept(obj);
    }

    public static void nullable(final Object obj, final Runnable runnable) {
        if (obj != null) runnable.run();
    }

    public static <R> R nullableOrElse(final R obj, final Supplier<R> orElse) {
        if (obj == null) return orElse.get();
        return obj;
    }

    public static void ifttt(final Supplier<Boolean> condition, final Runnable runnable) {
        if (condition.get()) runnable.run();
    }

    public static <R> R conditionNull(final boolean condition, final Supplier<R> function) {
        if (condition) return function.get();
        return null;
    }

    private ExtraObjects() {
    }

}
