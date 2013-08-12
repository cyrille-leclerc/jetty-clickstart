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

import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.AbstractHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Michael Neale
 * @author <a href="mailto:cleclerc@cloudbees.com">Cyrille Le Clerc</a>
 */
public class Main extends AbstractHandler {

    public static void main(String[] args) throws Exception {
        Server server = new Server(Integer.parseInt(System.getProperty("app.port", "8080")));
        server.setHandler(new Main());
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
