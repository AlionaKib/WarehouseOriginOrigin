package logging;

import exceptions.DevelopmentException;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

public class LoggingFactory {

    private Map<Class<? extends Logging>, Logging> loggingMap = new HashMap<>();

    @SuppressWarnings("unchecked")
    public <T extends Logging> T getLogging(Class<T> daoClass) {
        return (T) loggingMap.computeIfAbsent(daoClass, this::createLogging);
    }

    private <T extends Logging> T createLogging(Class<T> loggingClass) {
        try {
            return loggingClass.getConstructor().newInstance();
        } catch (NoSuchMethodException e) {
            throw new DevelopmentException(loggingClass.getSimpleName() + " doesn't have a default constructor");
        } catch (IllegalAccessException e) {
            throw new DevelopmentException("The default constructor in the " + loggingClass.getSimpleName() + " is unavailable");
        } catch (InstantiationException | InvocationTargetException e) {
            throw new DevelopmentException("Can't create " + loggingClass.getSimpleName());
        }
    }

}
