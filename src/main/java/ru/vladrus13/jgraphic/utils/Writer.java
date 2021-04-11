package ru.vladrus13.jgraphic.utils;

import ru.vladrus13.jgraphic.basic.Frame;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Writer class
 */
public class Writer {
    /**
     * Print to logger stacktrace
     *
     * @param logger logger
     * @param e      exception
     */
    public static void printStackTrace(Logger logger, Exception e) {
        StringWriter stringWriter = new StringWriter();
        e.printStackTrace(new PrintWriter(stringWriter));
        logger.severe(stringWriter.toString());
    }

    public static void printFullWarning(Logger logger, Frame frame, Level level, String message) {
        StringWriter stringWriter = new StringWriter();
        ArrayList<String> names = new ArrayList<>();
        Frame temp = frame;
        while (temp != null) {
            names.add(temp.toString());
            temp = temp.getParent();
        }
        Collections.reverse(names);
        stringWriter.write(String.join("->", names));
        stringWriter.write(System.lineSeparator());
        stringWriter.write(message);
        logger.log(level, stringWriter.toString());
    }
}
