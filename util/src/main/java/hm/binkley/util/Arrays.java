/*
 * This is free and unencumbered software released into the public domain.
 *
 * Anyone is free to copy, modify, publish, use, compile, sell, or
 * distribute this software, either in source code form or as a compiled
 * binary, for any purpose, commercial or non-commercial, and by any
 * means.
 *
 * In jurisdictions that recognize copyright laws, the author or authors
 * of this software dedicate any and all copyright interest in the
 * software to the public domain. We make this dedication for the benefit
 * of the public at large and to the detriment of our heirs and
 * successors. We intend this dedication to be an overt act of
 * relinquishment in perpetuity of all present and future rights to this
 * software under copyright law.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
 * EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
 * MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT.
 * IN NO EVENT SHALL THE AUTHORS BE LIABLE FOR ANY CLAIM, DAMAGES OR
 * OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE,
 * ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR
 * OTHER DEALINGS IN THE SOFTWARE.
 *
 * For more information, please refer to <http://unlicense.org/>.
 */

package hm.binkley.util;

import javax.annotation.Nonnull;
import java.lang.reflect.Array;
import java.util.Collection;

import static java.lang.System.arraycopy;

/**
 * {@code Arrays} holds simple functions for working with arrays.
 *
 * @author <a href="mailto:binkley@alumni.rice.edu">B. K. Oxley (binkley)</a>
 * @todo Replace with OSS solution
 */
public final class Arrays {
    /**
     * Returns the variadic parameter list as an array, relying on <a
     * href="https://docs.oracle .com/javase/specs/jls/se7/html/jls-15.html#jls-15.12.4.2">the
     * compiler to pack the parameters into an array</a>.
     *
     * @param elements the elements, never missin
     * @param <T> the element type
     *
     * @return the parameters as an array, never missing
     */
    @Nonnull
    @SafeVarargs
    public static <T> T[] array(final T... elements) {
        return elements;
    }

    /**
     * Returns the catenation onto <var>initial</var> of <var>rest</var>.
     *
     * @param initial the initial elements, never missing
     * @param rest the elements to catenate
     * @param <T> the array type
     *
     * @return the catenated array, never missing
     */
    @Nonnull
    @SafeVarargs
    @SuppressWarnings("unchecked")
    public static <T> T[] cat(@Nonnull final T[] initial, final T... rest) {
        if (0 == rest.length)
            return initial;
        final T[] array = (T[]) Array
                .newInstance(initial.getClass().getComponentType(),
                        initial.length + rest.length);
        arraycopy(initial, 0, array, 0, initial.length);
        arraycopy(rest, 0, array, initial.length, rest.length);
        return array;
    }

    /**
     * Returns the <var>elements</var> as an array using reflection to
     * construct the containing array.
     *
     * @param elements the elements, never missing
     * @param type the array component type token, never missing
     * @param <U> the array component type
     * @param <T> the collection element type
     *
     * @return the collection as an array, never missing
     */
    @Nonnull
    @SuppressWarnings("unchecked")
    public static <U, T extends U> U[] array(
            @Nonnull final Collection<T> elements,
            @Nonnull final Class<U> type) {
        return elements
                .toArray((U[]) Array.newInstance(type, elements.size()));
    }
}
