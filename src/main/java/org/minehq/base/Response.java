package org.minehq.base;

import rawhttp.core.RawHttp;
import rawhttp.core.RawHttpResponse;
import rawhttp.core.body.StringBody;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

public class Response {
    private RawHttpResponse<?> rawHttpResponse;

    private OutputStream outputStream;

    private StringBuilder headBuilder = new StringBuilder();

    private static final String CRLF = "\r\n";

    private String contentType = "text/html";

    private String startLine;

    private String body;

    public Response(Socket socket) throws IOException {
        this.outputStream = socket.getOutputStream();
    }

    public Response ok() {
        this.startLine = "HTTP/1.1 200 OK" + CRLF;
        return this;
    }

    public Response notFound() {
        this.startLine = "HTTP/1.1 404 Not Found" + CRLF;
        return this;
    }

    public Response SetContentType(String contentType) {
        this.contentType = "Content-Type:" + contentType + CRLF;
        return this;
    }

    public Response withBody(String body) {
        this.body = body;
        return this;
    }

    public void flush() {
        headBuilder.append(this.startLine);
        headBuilder.append(this.contentType);
        rawHttpResponse = new RawHttp().parseResponse(headBuilder.toString())
                .withBody(new StringBody(body));

        try {
            rawHttpResponse.writeTo(outputStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}