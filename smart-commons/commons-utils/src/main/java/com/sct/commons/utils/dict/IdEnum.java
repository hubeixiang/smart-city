/*
 * 
 */

package com.sct.commons.utils.dict;

/**
 * Enumeration types with identity.
 *
 * @param <E> The enum type subclass
 * @author 
 * @since 1.0.0
 */
public interface IdEnum<E extends Enum<E>> {

	int getId();

	String getDescription();

	@SuppressWarnings("unchecked")
	static <E extends IdEnum> E getType(Class<E> type, Integer id) {
		if (id == null) {
			return null;
		}
		try {
			E[] values = (E[]) type.getMethod("values").invoke(null);
			for (E value : values) {
				if (id.equals(value.getId())) {
					return value;
				}
			}
		}
		catch (ReflectiveOperationException ignored) {
		}
		throw new IllegalArgumentException("No matching type for id " + id);
	}

	static <E extends IdEnum> Integer getId(E obj, Integer defaultValue) {
		return obj == null ? defaultValue : (Integer) obj.getId();
	}

	static <E extends IdEnum> Integer getId(E obj, E defaultValue) {
		return getId(obj, getId(defaultValue));
	}

	static <E extends IdEnum> Integer getId(E obj) {
		return getId(obj, (Integer) null);
	}
}
