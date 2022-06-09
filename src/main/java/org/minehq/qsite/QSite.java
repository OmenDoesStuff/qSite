package org.minehq.qsite;

import io.javalin.Javalin;

public class QSite {
    public static void main(String[] args) {
        Javalin app = Javalin.create().start(8080);
    }
}
