/*
 * This is free and unencumbered software released into the public domain.
 *
 * Please see https://github.com/binkley/binkley/blob/master/LICENSE.md.
 */

package hm.binkley.inject;

import com.google.inject.Injector;
import joptsimple.OptionDeclarer;
import joptsimple.OptionSet;
import joptsimple.OptionSpec;
import org.aeonbits.owner.Config;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.ServiceLoader;

import static com.google.common.base.Joiner.on;
import static com.google.common.collect.Iterables.getOnlyElement;
import static com.google.inject.Guice.createInjector;
import static hm.binkley.inject.OwnerModule.ownerModule;
import static java.util.Collections.unmodifiableMap;

/**
 * {@code Main} is a sample guing together the Guice modules for a trivial application bootstrap.
 * Applications extends {@code Main} and specify {@linkplain #addOptions(OptionDeclarer) command
 * line options} with the template pattern.
 *
 * For a full-featured library see <a href="https://github.com/Netflix/governator/wiki">Governator</a>.
 *
 * @author <a href="mailto:binkley@alumni.rice.edu">B. K. Oxley (binkley)</a>
 */
public abstract class Main<C extends Config> {
    private final Class<C> configType;
    private final String prefix;

    /**
     * Main entry point called by the JVM. Order of operation: <ol> <li>Find the
     * <strong>only</strong> implementation of {@code Main} via JDK service loader.</li> <li>Create
     * a bootstrap injector to manage command line options with {@linkplain JOptSimpleModule
     * jopt-simple}.</li> <li>Create the application injector with {@linkplain OwnerModule OWNER
     * API} for configuration.</li> <li>Include {@linkplain LifecycleModule lifecycle support}.</li>
     * <li>Scan for {@linkplain MetaInfServicesModule annotated application modules}.</li>
     * <li>Inject the instance of {@code Main} triggering lifecycle events, if any.</li> </ol>
     *
     * @param args the command line arguments
     */
    public static void main(final String... args) {
        final Main main = getOnlyElement(ServiceLoader.load(Main.class));
        final JOptSimpleModule.Builder builder = JOptSimpleModule.builder();
        main.addOptions(builder);
        final Injector preGuice = createInjector(builder.parse(args));
        final OptionSet options = preGuice.getInstance(OptionSet.class);
        final OwnerModule ownerModule = ownerModule(main.configType, mapOf(options, main.prefix));
        preGuice.createChildInjector(ownerModule, new LifecycleModule(),
                new MetaInfServicesModule()).injectMembers(main);
    }

    /**
     * Constructs a new {@code Main}. Use this form when mappings of command line options are
     * one-to-one with properties configuration.
     *
     * @param configType the OWNER API config type, never missing
     */
    protected Main(@Nonnull final Class<C> configType) {
        this(configType, null);
    }

    /**
     * Constructs a new {code Main}. Use this form when command line options need a
     * <var>prefix</var> to match properties configuration, e.g., "debug" on the command line is
     * "my.app.debug" in properties.
     *
     * @param configType the OWNER API config type, never missing
     * @param prefix the optional prefix for command line options
     */
    protected Main(@Nonnull final Class<C> configType, @Nullable final String prefix) {
        this.configType = configType;
        this.prefix = prefix;
    }

    private static Map<String, String> mapOf(final OptionSet options, final String prefix) {
        final Map<String, String> map = new HashMap<>();
        for (final Entry<OptionSpec<?>, List<?>> entry : options.asMap().entrySet()) {
            final OptionSpec<?> spec = entry.getKey();
            map.put(asPropertyKey(prefix, spec),
                    asPropertValue(entry.getValue(), options.has(spec)));
        }
        return unmodifiableMap(map);
    }

    private static String asPropertyKey(final String prefix, final OptionSpec<?> spec) {
        final Collection<String> flags = spec.options();
        for (final String flag : flags)
            if (1 < flag.length())
                return null == prefix ? flag : (prefix + '.' + flag);
        throw new IllegalArgumentException("No usable non-short flag: " + flags);
    }

    private static String asPropertValue(final List<?> values, final boolean present) {
        return values.isEmpty() ? String.valueOf(present) : on(',').join(values);
    }

    /**
     * Configure command line parsing with JOpt-Simple.
     *
     * @param optionDeclarer the options parser, never mising
     */
    protected abstract void addOptions(@Nonnull final OptionDeclarer optionDeclarer);
}
