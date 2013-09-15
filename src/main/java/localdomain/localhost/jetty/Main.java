/*
 * Copyright 2010-2013, CloudBees Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package localdomain.localhost.jetty;

import org.eclipse.jetty.server.*;
import org.eclipse.jetty.server.handler.AbstractHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;

/**
 * Start a Jetty 9 Embedded App.
 *
 * @author Michael Neale
 * @author <a href="mailto:cleclerc@cloudbees.com">Cyrille Le Clerc</a>
 */
public class Main extends AbstractHandler {

    public static void main(String[] args) throws Exception {
        // The "app.port" system property is injected by CloudBees Java ClickStack
        // See http://developer.cloudbees.com/bin/view/RUN/Java+Container
        int port = Integer.parseInt(System.getProperty("app.port", "8080"));

        Server server = new Server();
        server.setHandler(new Main());

        // Add support for x-forwarded-for and x-forwarded-proto headers
        // These headers are injected by the NGinx routing layer on CloudBees platform
        HttpConfiguration httpConfiguration = new HttpConfiguration();
        httpConfiguration.addCustomizer(new ForwardedRequestCustomizer());

        HttpConnectionFactory httpConnectionFactory = new HttpConnectionFactory(httpConfiguration);
        ServerConnector httpConnector = new ServerConnector(server, httpConnectionFactory);
        httpConnector.setPort(port);
        server.addConnector(httpConnector);

        // Start Jetty
        server.start();
        server.join();
    }

    @Override
    public void handle(String target,
                       Request baseRequest,
                       HttpServletRequest request,
                       HttpServletResponse response)
            throws IOException, ServletException {
        response.setContentType("text/html;charset=utf-8");
        response.setStatus(HttpServletResponse.SC_OK);
        baseRequest.setHandled(true);

        System.out.println("request: " +
                "url=" + request.getRequestURL() + ", " +
                "remoteAddr=" + request.getRemoteAddr() + ", " +
                "isSecure=" + request.isSecure());

        Enumeration<String> headers = request.getHeaderNames();
        while (headers.hasMoreElements()) {
            String headerName = headers.nextElement();
            String headerValue = request.getHeader(headerName);
            System.out.println(headerName + ": " + headerValue);
        }


        String html = "<html>" +
                "<head><title>Jetty 9 Embedded Clickstart</title></head>" +
                "<body>" +
                "<h1><code>Jetty 9 Embedded on CloudBees</code></h1>" +
                "<a href='https://github.com/CloudBees-community/jetty9-embedded-clickstart'>Fork me on Github</a>" +
                "</body>" +
                "</html>";
        response.getWriter().println(html);
    }
}
