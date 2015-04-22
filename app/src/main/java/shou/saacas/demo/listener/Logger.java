package shou.saacas.demo.listener;

import android.util.Log;

class Logger {
    /** Tag that is used to identify the source of a log message. */
    private static final String TAG = "saacassdk-demo-listener";

    /**
     * Initialize a new instance.
     */
    private Logger() {
    }

    /**
     * Output debug log message.
     *
     * @param format A format string.
     * @param args A list of arguments passed to the formatter.
     */
    static void debug(String format, Object... args) {
        if (Log.isLoggable(TAG, Log.DEBUG)) {
            Log.d(TAG, String.format(format, args));
        }
    }

    /**
     * Output info log message.
     *
     * @param format A format string.
     * @param args A list of arguments passed to the formatter.
     */
    static void info(String format, Object... args) {
        if (Log.isLoggable(TAG, Log.INFO)) {
            Log.i(TAG, String.format(format, args));
        }
    }

    /**
     * Output warning log message.
     *
     * @param format A format string.
     * @param args A list of arguments passed to the formatter.
     */
    static void warn(String format, Object... args) {
        if (Log.isLoggable(TAG, Log.WARN)) {
            Log.w(TAG, String.format(format, args));
        }
    }

    /**
     * Output error log message.
     *
     * @param format A format string.
     * @param args A list of arguments passed to the formatter.
     */
    static void error(String format, Object... args) {
        if (Log.isLoggable(TAG, Log.ERROR)) {
            Log.e(TAG, String.format(format, args));
        }
    }

    /**
     * Output error.
     *
     * @param th A thrown error.
     */
    static void error(Exception th) {
        if (Log.isLoggable(TAG, Log.ERROR)) {
            Log.e(TAG, "Error occurred.", th);
        }
    }

    /**
     * Check whether or not an application can write a log that is the specified log level.
     *
     * @param level A log level.
     * @return True if an application can write log, otherwise false.
     */
    static boolean isLoggable(int level) {
        return Log.isLoggable(TAG, level);
    }
}
