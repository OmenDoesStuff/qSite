package org.minehq.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class qLogger {
    String prefix;

    public qLogger(String prefix) {
        this.prefix = "[" + prefix + "] ";
    }

    public void info(String s) {
        SimpleDateFormat formatter= new SimpleDateFormat("HH:mm:ss");
        Date date = new Date(System.currentTimeMillis());
        System.out.println("[" + formatter.format(date) + "] " + prefix + "[INFO] " + s);
    }

    public void debug(String s) {
        SimpleDateFormat formatter= new SimpleDateFormat("HH:mm:ss");
        Date date = new Date(System.currentTimeMillis());
        System.out.println("[" + formatter.format(date) + "] " + prefix + "[DEBUG] " + s);
    }

    public void verbose(String s) {
        SimpleDateFormat formatter= new SimpleDateFormat("HH:mm:ss");
        Date date = new Date(System.currentTimeMillis());
        System.out.println("[" + formatter.format(date) + "] " + prefix + "[VERBOSE] " + s);
    }

    public void warn(String s) {
        SimpleDateFormat formatter= new SimpleDateFormat("HH:mm:ss");
        Date date = new Date(System.currentTimeMillis());
        System.out.println("[" + formatter.format(date) + "] " + prefix + "[WARN] " + s);
    }

    public void err(String s) {
        SimpleDateFormat formatter= new SimpleDateFormat("HH:mm:ss");
        Date date = new Date(System.currentTimeMillis());
        System.out.println("[" + formatter.format(date) + "] " + prefix + "[ERROR] " + s);
    }

    public void error(String s) {
        SimpleDateFormat formatter= new SimpleDateFormat("HH:mm:ss");
        Date date = new Date(System.currentTimeMillis());
        System.err.println("[" + formatter.format(date) + "] " + prefix + "[ERROR] " + s);
    }

    public void fatal(String s) {
        SimpleDateFormat formatter= new SimpleDateFormat("HH:mm:ss");
        Date date = new Date(System.currentTimeMillis());
        System.out.println("[" + formatter.format(date) + "] " + prefix + "[FATAL] " + s);
        System.exit(0);
    }
}
