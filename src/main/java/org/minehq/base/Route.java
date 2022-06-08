package org.minehq.base;

@FunctionalInterface
public interface Route {
    void handle(Request request, Response response);
}
