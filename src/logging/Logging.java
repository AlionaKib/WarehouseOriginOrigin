package logging;

import java.util.function.Supplier;

public interface Logging {

    LogLevel getLogLevel();

    void setLogLevel(LogLevel logLevel);

    void log(LogLevel logLevel, String message);

    void log(LogLevel logLevel, Exception e);

    void log(LogLevel logLevel, Supplier<String> messageSupplier);

}
