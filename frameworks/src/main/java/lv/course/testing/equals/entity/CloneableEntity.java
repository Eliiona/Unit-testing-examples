package lv.course.testing.equals.entity;

import lv.course.testing.equals.annotations.Id;

import java.util.Arrays;

public interface CloneableEntity<T> extends Cloneable {

    default T cloneEntity() {
        try {
            T dest = (T) this.clone();
            Arrays.stream(this.getClass().getFields())
                    .filter(f -> f.isAnnotationPresent(Id.class))
                    .forEach(f ->  {
                        f.setAccessible(true);
                        try {
                            f.set(dest, 0);
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
