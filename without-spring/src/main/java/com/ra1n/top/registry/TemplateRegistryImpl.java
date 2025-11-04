package com.ra1n.top.registry;

import com.ra1n.top.notifications.NotificationType;
import com.ra1n.top.template.EmailTemplate;

import io.github.classgraph.ClassGraph;
import io.github.classgraph.ClassInfo;
import io.github.classgraph.ScanResult;

import java.lang.reflect.Modifier;
import java.util.EnumMap;
import java.util.Map;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Default implementation of {@link TemplateRegistry} that automatically discovers and registers
 * {@link EmailTemplate} implementations at runtime using classpath scanning.
 *
 * <h3>Overview</h3>
 * <p>
 * This registry scans a specified base package for concrete classes that implement {@link EmailTemplate}.
 * Each discovered template is instantiated via its public no-argument constructor and registered
 * under the {@link NotificationType} returned by {@link EmailTemplate#type()}.
 * </p>
 *
 * <h3>Requirements for Template Classes</h3>
 * <ul>
 *   <li>Must be a non-abstract, non-interface class</li>
 *   <li>Must implement {@link EmailTemplate}</li>
 *   <li>Must have a <strong>public no-argument constructor</strong></li>
 *   <li>Must return a non-null {@link NotificationType} from {@code type()}</li>
 *   <li>Only one template per {@link NotificationType} is allowed — duplicates result in an exception</li>
 * </ul>
 *
 * <h3>Thread Safety</h3>
 * <p>
 * This class is thread-safe after construction. The internal {@link EnumMap} is populated during
 * initialization and never modified afterward.
 * </p>
 *
 * <h3>Usage Example</h3>
 * <pre>{@code
 * TemplateRegistry registry = new TemplateRegistryImpl("com.myapp.email.templates");
 * EmailTemplate template = registry.get(NotificationType.WELCOME);
 * }</pre>
 *
 * <h3>Logging</h3>
 * <p>
 * Registration events and errors are logged using {@link java.util.logging.Logger} at appropriate levels:
 * </p>
 * <ul>
 *   <li>{@code INFO} — successful registration</li>
 *   <li>{@code WARNING} — skippable issues (missing constructor, null type, etc.)</li>
 *   <li>{@code SEVERE} — instantiation failures</li>
 * </ul>
 *
 * @author Travkin Andrii
 * @version 03.11.2025
 * @see EmailTemplate
 * @see NotificationType
 * @see TemplateRegistry
 * @since 1.0
 */
public final class TemplateRegistryImpl implements TemplateRegistry {
    private static final Logger LOG = Logger.getLogger(TemplateRegistryImpl.class.getName());

    private final Map<NotificationType, EmailTemplate> registryMap = new EnumMap<>(NotificationType.class);

    /**
     * Constructs a new registry and scans the specified package for email templates.
     *
     * @param basePackage the base package to scan for {@link EmailTemplate} implementations;
     *                    must not be {@code null} or empty
     * @throws NullPointerException     if {@code basePackage} is {@code null}
     * @throws IllegalArgumentException if {@code basePackage} is blank or no valid templates are found
     *                                  (optional — can be customized per use case)
     * @throws IllegalStateException    if duplicate {@link NotificationType}s are detected
     */
    public TemplateRegistryImpl(String basePackage) {
        Objects.requireNonNull(basePackage, "basePackage must not be null");
        scanAndRegister(basePackage);
    }

    private void scanAndRegister(String basePackage) {
        try (ScanResult scanResult = new ClassGraph()
                .enableClassInfo()
                .acceptPackages(basePackage)
                .scan()) {

            for (ClassInfo classInfo : scanResult.getClassesImplementing(EmailTemplate.class.getName())) {

                if (classInfo.isAbstract() || classInfo.isInterface()) {
                    continue;
                }

                Class<?> templateClass = classInfo.loadClass();

                try {
                    if (!hasPublicNoArgConstructor(templateClass)) {
                        LOG.log(Level.WARNING,
                                "Class {0} does not have a public no-arg constructor — skipping",
                                templateClass.getName());
                        continue;
                    }

                    EmailTemplate templateInstance =
                            (EmailTemplate) templateClass.getDeclaredConstructor().newInstance();

                    NotificationType templateType = templateInstance.type();

                    if (templateType == null) {
                        LOG.log(Level.WARNING,
                                "Template {0} returned null for type() — skipping",
                                templateClass.getName());
                        continue;
                    }

                    if (registryMap.containsKey(templateType)) {
                        LOG.log(Level.WARNING,
                                "Template for type {0} is already registered. Class {1} will be ignored.",
                                new Object[]{templateType, templateClass.getName()});

                        throw new IllegalArgumentException(
                                "Notification type already registered. Existing: "
                                        + registryMap.get(templateType).getClass().getName()
                                        + " | Duplicate: "
                                        + templateClass.getName()
                        );
                    }

                    registryMap.put(templateType, templateInstance);

                    LOG.log(Level.INFO,
                            "Registered template: {0} -> {1}",
                            new Object[]{templateType, templateClass.getName()});

                } catch (Exception e) {
                    LOG.log(Level.SEVERE,
                            "Failed to create template instance for " + templateClass.getName(), e);
                }
            }
        }
    }

    private boolean hasPublicNoArgConstructor(Class<?> clazz) {
        try {
            return clazz.getDeclaredConstructor().getModifiers() == Modifier.PUBLIC;
        } catch (NoSuchMethodException e) {
            return false;
        }
    }

    /**
     * Retrieves the email template for the specified notification type.
     *
     * @param type the notification type; must not be {@code null}
     * @return the corresponding {@link EmailTemplate}; never {@code null}
     * @throws NullPointerException if {@code type} is {@code null}
     * @throws IllegalArgumentException if no template is registered for the given type
     */
    @Override
    public EmailTemplate get(NotificationType type) {
        return registryMap.get(type);
    }
}
