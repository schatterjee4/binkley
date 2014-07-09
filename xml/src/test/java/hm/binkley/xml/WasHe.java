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

package hm.binkley.xml;

import javax.annotation.Nonnull;
import java.math.BigDecimal;
import java.net.URI;
import java.time.Instant;

/**
 * {@code WasHe} <b>needs documentation</b>.
 *
 * @author <a href="mailto:binkley@alumni.rice.edu">B. K. Oxley (binkley)</a>
 * @todo Needs documentation.
 */
@Fuzzy
public interface WasHe {
    @Fuzzy.Field("//wasHe/needsNoConversion/text()")
    String needsNoConversion();

    @Fuzzy.Field("//wasHe/isAPrimitive/text()")
    int isAPrimitive();

    @Fuzzy.Field("//wasHe/usesParse/text()")
    Instant usesParse();

    @Fuzzy.Field("//wasHe/usesConstructor/text()")
    BigDecimal usesConstructor();

    @Fuzzy.Field("//wasHe/nullOk/text()")
    String nullOk();

    @Fuzzy.Field("//wasHe/throwsAnException/text()")
    @Nonnull
    URI throwsAnException();
}
