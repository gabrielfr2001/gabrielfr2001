package br.com.scheid.model;

import java.io.Serializable;

public abstract class AbstractModel<T extends Serializable> {
	
	public abstract T getId();

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof AbstractModel)) {
			return false;
		}
		if (getId() == null || ((AbstractModel<?>) obj).getId() == null) {
			return false;
		}
		if (!getId().equals(((AbstractModel<?>) obj).getId())) {
			return false;
		}
		return true;
	}

	@Override
	public int hashCode() {
		return getId() == null ? super.hashCode() : getId().hashCode();
	}
}
