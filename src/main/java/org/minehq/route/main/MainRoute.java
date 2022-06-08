package org.minehq.route.main;

import org.minehq.base.Request;
import org.minehq.base.Response;
import org.minehq.base.Route;
import org.minehq.data.GameType;
import org.minehq.placeholder.LeaderboardPlaceholder;

public class MainRoute implements Route {
    @Override
    public void handle(Request request, Response response) {

        response.SetContentType("text/plain");
        response.withBody(LeaderboardPlaceholder.fillIn("place ${global.1} ", GameType.PRACTICE));
        response.ok();
        response.flush();
    }
}
