package com.toney.core.test;
import org.junit.Ignore;
import org.mockito.Mockito;
import org.springframework.beans.factory.FactoryBean;


/**
 * Factory bean for easy use of mocks in when testing auto-wired code
 *
 * @author Lars Tackmann
 */
@Ignore
public class MocksFactory<T> implements FactoryBean<T> {
    private Class<T> type;// the created object type

    public void setType(final Class<T> type) {
        this.type = type;
    }

    @Override
    public T getObject() throws Exception {
        return (T) Mockito.mock(type);
    }

    @Override
    public Class<T> getObjectType() {
        return type;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }
}

