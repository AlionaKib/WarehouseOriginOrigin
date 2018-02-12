package logging;

import java.util.function.Supplier;

public class ConsoleLogging implements Logging {

    private LogLevel logLevel;

    public ConsoleLogging() {
        this.logLevel = LogLevel.ERROR;
    }

    @Override
    public LogLevel getLogLevel() {
        return logLevel;
    }

    @Override
    public void setLogLevel(LogLevel logLevel) {
        this.logLevel = logLevel;
    }

    @Override
    public void log(LogLevel logLevel, String message) {
        if (logLevel.compareTo(this.logLevel) >= 0) {
            System.out.println(message);
        }
    }

    @Override
    public void log(LogLevel logLevel, Exception e) {
        if (logLevel.compareTo(this.logLevel) >= 0) {
            System.out.println(e.getLocalizedMessage());
            e.printStackTrace(System.out);
        }
    }

    @Override
    public void log(LogLevel logLevel, Supplier<String> messageSupplier) {
        if (logLevel.compareTo(this.logLevel) >= 0) {
            String message = messageSupplier.get();
            System.out.println(message);
        }
    }
}
