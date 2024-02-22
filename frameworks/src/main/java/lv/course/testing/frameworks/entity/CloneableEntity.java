package lv.course.testing.frameworks.entity;

import lv.course.testing.frameworks.annotations.Connection;
import lv.course.testing.frameworks.annotations.Id;
import lv.course.testing.frameworks.annotations.Version;

import java.util.Arrays;

public interface CloneableEntity<T> {

    default T cloneEntity() {
        try {
            T dest = (T) this.getClass().getDeclaredConstructor().newInstance();
            Arrays.stream(this.getClass().getFields())
                    .filter(f -> f.isAnnotationPresent(Id.class))
                    .forEach(f -> {
                        f.setAccessible(true);
                        try {
                            if (!f.isAnnotationPresent(Id.class) &&
                                    !f.isAnnotationPresent(Version.class) &&
                                    !f.isAnnotationPresent(Connection.class)) {
                                f.set(dest, f.get(this));
                            }
                        } catch (IllegalAccessException e) {
                            throw new RuntimeException(e);
                        }
                    });
            return dest;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
