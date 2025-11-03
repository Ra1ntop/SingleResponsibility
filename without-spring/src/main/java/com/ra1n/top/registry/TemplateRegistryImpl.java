package com.ra1n.top.registry;

import com.ra1n.top.notifications.NotificationType;
import com.ra1n.top.template.EmailTemplate;

import io.github.classgraph.ClassGraph;
import io.github.classgraph.ClassInfo;
import io.github.classgraph.ScanResult;
import java.lang.reflect.Modifier;
import java.util.EnumMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Travkin Andrii
 * @version 03.11.2025
 */
public final class TemplateRegistryImpl implements TemplateRegistry {
    private static final Logger LOG = Logger.getLogger(TemplateRegistryImpl.class.getName());

    private final Map<NotificationType, EmailTemplate> map = new EnumMap<>(NotificationType.class);

    /**
     * basePackage — пакет, где искать реализации (например "com.myapp.email.templates").
     */
    public TemplateRegistryImpl(String basePackage) {
        scanAndRegister(basePackage);
    }

    private void scanAndRegister(String basePackage) {
        try (ScanResult sr = new ClassGraph().enableClassInfo().acceptPackages(basePackage).scan()) {
            for (ClassInfo ci : sr.getClassesImplementing(EmailTemplate.class.getName())) {
                if (ci.isAbstract() || ci.isInterface()) continue;
                Class<?> clazz = ci.loadClass();
                try {
                    // проверяем доступность конструктора
                    if (!hasPublicNoArgConstructor(clazz)) {
                        LOG.log(Level.WARNING, "Класс {0} не имеет public no-arg конструктора — пропускаем", clazz.getName());
                        continue;
                    }
                    EmailTemplate tpl = (EmailTemplate) clazz.getDeclaredConstructor().newInstance();
                    NotificationType t = tpl.type();
                    if (t == null) {
                        LOG.log(Level.WARNING, "Шаблон {0} вернул null type — пропускаем", clazz.getName());
                        continue;
                    }
                    if (map.containsKey(t)) {
                        LOG.log(Level.WARNING, "Шаблон для типа {0} уже зарегистрирован, класс {1} будет проигнорирован", new Object[]{t, clazz.getName()});
                        throw new IllegalArgumentException("this type is already registered. Registered class:" + map.get(t).getClass().getName() + "dublicate type: " + clazz.getName());
                    }
                    map.put(t, tpl);
                    LOG.log(Level.INFO, "Registered template: {0} -> {1}", new Object[]{t, clazz.getName()});
                } catch (Exception e) {
                    LOG.log(Level.SEVERE, "Failed to create template instance for " + clazz.getName(), e);
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

    @Override
    public EmailTemplate get(NotificationType t) {
        return map.get(t);
    }
}
