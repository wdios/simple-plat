package com.wis.common;

import java.io.Serializable;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

/**
 * 抽象实体基类
 */
public abstract class AbstractModel<ID extends Serializable> {

    public abstract ID getId();

    /**
     * Sets the id of the entity.
     * @param id the id to set
     */
    public abstract void setId(final ID id);

    /*
     * (non-Javadoc)
     * @see org.springframework.data.domain.Persistable#isNew()
     */
    public boolean isNew() {
        return null == getId();
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {
        if (null == obj) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        if (!getClass().equals(obj.getClass())) {
            return false;
        }
        AbstractModel<?> that = (AbstractModel<?>) obj;
        return null == this.getId() ? false : this.getId().equals(that.getId());
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        int hashCode = 17;
        hashCode += null == getId() ? 0 : getId().hashCode() * 31;
        return hashCode;
    }

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }
    
}
