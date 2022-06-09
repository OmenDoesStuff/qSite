package org.minehq;

import org.minehq.base.Server;
import org.minehq.route.main.MainRoute;

public class qSite {
    public static void main(String[] args) {
        Server server = Server.create();

        server.get("/", new MainRoute());

        server.start(80);
        server.start(443);


    }
}
