/*
 * This is free and unencumbered software released into the public domain.
 *
 * Please see https://github.com/binkley/binkley/blob/master/LICENSE.md.
 */

package hm.binkley.util.logging;

import org.slf4j.Logger;
import org.slf4j.Marker;
import org.slf4j.ext.LoggerWrapper;

/**
 * {@code MarkedLogger} is an SLF4J logger with a default marker applied to all methods with a
 * marker variant.
 * <p/>
 * Example: <pre>
 * final Marker marker = MarkerFactory.getMarker("ALERT");
 * final Logger alert = new MarkedLogger(LoggerFactory.getLogger(getClass()), marker);
 * alert.error("Mismatched socks: {} vs. {}", leftSock, rightSock);
 * alert.error(marker, "Mismatched socks: {} vs. {}", leftSock, rightSock);
 * </pre>
 * Marks this logging event as "ALERT".  Suitable logger configuration might redirect this event to
 * a human being for immediate action.  Both logging lines are equivalent.
 * <p/>
 * In Logback configuration use {@code %marker} to print the marker in the encoder pattern.
 *
 * @author <a href="mailto:binkley@alumni.rice.edu">B. K. Oxley (binkley)</a>
 */
public class MarkedLogger
        extends LoggerWrapper {
    private final Marker marker;

    protected MarkedLogger(final Marker marker, final Logger logger, final String fqcn) {
        super(logger, fqcn);
        this.marker = marker;
    }

    public static MarkedLogger markedLogger(final Marker marker, final Logger logger) {
        return new MarkedLogger(marker, logger, MarkedLogger.class.getName());
    }

    @Override
    public void trace(final String msg) {
        trace(marker, msg);
    }

    @Override
    public void trace(final String format, final Object arg) {
        trace(marker, format, arg);
    }

    @Override
    public void trace(final String format, final Object arg1, final Object arg2) {
        trace(marker, format, arg1, arg2);
    }

    @Override
    public void trace(final String format, final Object... args) {
        trace(marker, format, args);
    }

    @Override
    public void trace(final String msg, final Throwable t) {
        trace(marker, msg, t);
    }

    @Override
    public void debug(final String msg) {
        debug(marker, msg);
    }

    @Override
    public void debug(final String format, final Object arg) {
        debug(marker, format, arg);
    }

    @Override
    public void debug(final String format, final Object arg1, final Object arg2) {
        debug(marker, format, arg1, arg2);
    }

    @Override
    public void debug(final String format, final Object... args) {
        debug(marker, format, args);
    }

    @Override
    public void debug(final String msg, final Throwable t) {
        debug(marker, msg, t);
    }

    @Override
    public void info(final String msg) {
        info(marker, msg);
    }

    @Override
    public void info(final String format, final Object arg) {
        info(marker, format, arg);
    }

    @Override
    public void info(final String format, final Object arg1, final Object arg2) {
        info(marker, format, arg1, arg2);
    }

    @Override
    public void info(final String format, final Object... args) {
        info(marker, format, args);
    }

    @Override
    public void info(final String msg, final Throwable t) {
        info(marker, msg, t);
    }

    @Override
    public void warn(final String msg) {
        warn(marker, msg);
    }

    @Override
    public void warn(final String format, final Object arg) {
        warn(marker, format, arg);
    }

    @Override
    public void warn(final String format, final Object arg1, final Object arg2) {
        warn(marker, format, arg1, arg2);
    }

    @Override
    public void warn(final String format, final Object... args) {
        warn(marker, format, args);
    }

    @Override
    public void warn(final String msg, final Throwable t) {
        warn(marker, msg, t);
    }

    @Override
    public void error(final String msg) {
        error(marker, msg);
    }

    @Override
    public void error(final String format, final Object arg) {
        error(marker, format, arg);
    }

    @Override
    public void error(final String format, final Object arg1, final Object arg2) {
        error(marker, format, arg1, arg2);
    }

    @Override
    public void error(final String format, final Object... args) {
        error(marker, format, args);
    }

    @Override
    public void error(final String msg, final Throwable t) {
        error(marker, msg, t);
    }
}