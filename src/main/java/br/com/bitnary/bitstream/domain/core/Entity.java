package br.com.bitnary.bitstream.domain.core;

import br.com.bitnary.bitstream.shared.StringUtils;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.UUID;

@SuperBuilder(toBuilder = true)
@Getter
@Setter
@ToString
public class Entity {
    protected String id;
    protected LocalDateTime createdAt;
    protected LocalDateTime updatedAt;

    public Entity() {
        LocalDateTime dateNow = LocalDateTime.now();

        this.id = null;
        this.createdAt = dateNow;
        this.updatedAt = dateNow;
    }

    public UUID getUuid() {
        if (this.id != null) {
            return UUID.fromString(this.id);
        }

        return null;
    }

    public void updateTimestamps() {
        LocalDateTime dateNow = LocalDateTime.now();

        if (id == null || createdAt == null) {
            this.setCreatedAt(dateNow);
        }

        this.setUpdatedAt(dateNow);
    }

    public void validate() {
        Method[] methods = this.getClass().getDeclaredMethods();

        Arrays.stream(methods)
                .filter(method -> method.getName().startsWith("set") && method.getParameterCount() == 1)
                .forEach(method -> {
                    String propertyName = StringUtils.lowercaseFirst(method.getName().substring(3));
                    Object fieldValue = getFieldValue(propertyName);

                    if (fieldValue != null) {
                        try {
                            method.invoke(this, fieldValue);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
    }

    private boolean isPrimitive(Class<?> type) {
        return type.isPrimitive() || type == Integer.class || type == Long.class || type == Double.class ||
                type == Float.class || type == Boolean.class || type == Character.class ||
                type == Byte.class || type == Short.class || type == String.class;
    }

    private Object getFieldValue(String fieldName) {
        try {
            String getterName = "get" + StringUtils.uppercaseFirst(fieldName);
            Method getterMethod = this.getClass().getDeclaredMethod(getterName);

            if (getterMethod.getParameterCount() == 0) {
                return getterMethod.invoke(this);
            }
        } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
        }

        return null;
    }

}
