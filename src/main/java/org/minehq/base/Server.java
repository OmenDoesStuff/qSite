package org.minehq.base;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {
    private int port = 8080;

    private boolean isStop = false;

    private Thread serverThread;

    private ServerSocket socket;

    private Map<String, Route> routes = new HashMap<>();

    private Server() {

    }

    public static Server create() {
        return new Server();
    }

    public void start() {
        this.start(this.port);
    }

    public synchronized void start(int port) {
        this.port = port;

        try {
            if (socket == null) {
                socket = new ServerSocket(this.port);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        if (serverThread == null) {
            serverThread = new Thread(() -> init());
            serverThread.start();
        }
    }

    public void stop() {
        try {
            this.isStop = true;
            this.socket.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void init() {
        try {
            ExecutorService fixedThreadPool = Executors.newFixedThreadPool(2);
            while (!isStop) {
                Socket clientSocket = socket.accept();
                HttpRequestHandler request = new HttpRequestHandler(clientSocket);
                fixedThreadPool.execute(request);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public Server get(String path, Route route) {
        routes.put("GET:" + path, route);
        return this;
    }

    public Server post(String path, Route route) {
        routes.put("POST:" + path, route);
        return this;
    }

    public Server put(String path, Route route) {
        routes.put("PUT:" + path, route);
        return this;
    }

    public Server delete(String path, Route route) {
        routes.put("DELETE:" + path, route);
        return this;
    }

    public Server patch(String path, Route route) {
        routes.put("PATCH:" + path, route);
        return this;
    }

    class HttpRequestHandler implements Runnable {

        private Socket socket;

        public HttpRequestHandler(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            try {
                Request request = new Request(socket);
                Response response = new Response(socket);

                String routeKey = String.format("%s:%s", request.getMethod(), request.getUri().getPath());
                Route route = routes.getOrDefault(routeKey, getDefaultRoute());
                route.handle(request, response);
                socket.close();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

        private Route getDefaultRoute() {
            return (req, res) -> res.notFound()
                    .SetContentType("text/plain; charset=utf-8")
                    .withBody(String.format("Can not found the path: %s", req.getUri().getPath())).flush();
        }
    }
}
