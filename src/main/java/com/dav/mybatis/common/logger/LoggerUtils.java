package com.dav.mybatis.common.logger;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;

/**
 * The Class LoggerUtils.
 */
public class LoggerUtils {

    /** The root logger. */
    private static Logger rootLogger = Logger.getRootLogger();

    private static HashMap<String, Logger> LOGGER_CACHE = new HashMap<String, Logger>();

    /**
     * Instantiates a new logger utils.
     */
    private LoggerUtils() {
    }

    private static synchronized Logger getLogger(Object instance) {
        String loggerName = instance.getClass().getName();
        Logger logger = (Logger) LOGGER_CACHE.get(loggerName);
        if (logger == null) {
            logger = Logger.getLogger(loggerName);
            LOGGER_CACHE.put(loggerName, logger);
        }
        return logger;
    }

    /**
     * Log fatal.
     *
     * @param throwEx
     *            the throw ex
     */
    public static void logFatal(Throwable throwEx) {

        StringBuffer strEx = getContentException(throwEx);

        String template = "#************BATCH************[Errors] " + strEx;
        rootLogger.fatal(template);
    }

    /**
     * Log error.
     *
     * @param throwEx
     *            the throw ex
     */
    public static void logError(Object instance, Throwable throwEx) {

        StringBuffer strEx = getContentException(throwEx);
        String template = "#************BATCH************[ErrorData] " + strEx;
        getLogger(instance).error(template);
    }

    /**
     * Log start.
     *
     * @param job
     *            the job
     * @param step
     *            the step
     */
    public static void logStart(Object instance, String job, String step) {

        String template = "#************BATCH************[Start Batch] Job : " + job + " --- Step : " + step;
        getLogger(instance).info(template);
    }

    /**
     * Log end.
     *
     * @param job
     *            the job
     * @param step
     *            the step
     */
    public static void logEnd(Object instance, String job, String step) {

        String template = "#************BATCH************[End Batch] Job : " + job + " --- Step : " + step;
        getLogger(instance).info(template);
    }

    /**
     * Log info.
     *
     * @param content
     *            the content
     */
    public static void logInfo(Object instance, String content) {

        String template = "#************BATCH************[Info Batch] " + content;
        getLogger(instance).info(template);
    }

    /**
     * Log debug.
     *
     * @param content
     *            the content
     */
    public static void logDebug(Object instance, String content) {

        String template = "#************BATCH************[Debug Batch] " + content;
        getLogger(instance).debug(template);
    }

    /**
     * Log sql.
     *
     * @param parameter
     *            the parameter
     * @param sqlName
     *            the sql name
     * @param contentSql
     *            the content sql
     */
    public static void logSql(Object instance, MapSqlParameterSource parameter, String sqlName, String contentSql) {

        StringBuffer template = new StringBuffer();

        template.append("#************BATCH************[SQL Batch] " + sqlName);
        template.append(System.lineSeparator());
        template.append(contentSql);
        template.append(System.lineSeparator());
        if (parameter != null) {
            template.append("==========Parameter==========");
            template.append(System.lineSeparator());
            for (Map.Entry<String, Object> param : parameter.getValues().entrySet()) {

                template.append(param.getKey() + " = " + param.getValue());
                template.append(System.lineSeparator());
            }

            template.append("==========Parameter==========");
        }
        getLogger(instance).info(template);
    }

    /**
     * Gets the content exception.
     *
     * @param throwEx
     *            the throw ex
     * @return the content exception
     */
    private static StringBuffer getContentException(Throwable throwEx) {
        StringBuffer strEx = new StringBuffer();
        strEx.append(throwEx);
        strEx.append(System.lineSeparator());

        for (int i = 0; i < throwEx.getStackTrace().length; i++) {
            strEx.append(throwEx.getStackTrace()[i]);
            strEx.append(System.lineSeparator());
        }
        return strEx;
    }
}
