package org.minehq;

import org.minehq.base.Server;
import org.minehq.route.main.MainRoute;
import org.minehq.util.qLogger;

public class qSite {

    private static qLogger logger = new qLogger("qSite");

    public static void main(String[] args) {
        Server server = Server.create();

        server.get("/", new MainRoute());

        server.start(80);
        server.start(443);


    }

    public static qLogger getLogger() {
        return logger;
    }
}