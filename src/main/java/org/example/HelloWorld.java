package org.example;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;
import java.io.IOException;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.handler.AbstractHandler;

public class HelloWorld extends AbstractHandler
{
    public void handle(String target,
                       Request baseRequest,
                       HttpServletRequest request,
                       HttpServletResponse response)
            throws IOException, ServletException
    {
        response.setContentType("text/html;charset=utf-8");
        response.setStatus(HttpServletResponse.SC_OK);
        baseRequest.setHandled(true);

        String popover = "<!-- this is used for first time display of clickstart - a template-->\n" +
                "    <div id=\"clickstart_content\" style=\"display:none\">\n" +
                "    <p>\n" +
                "      Congratulations, your <a href=\"#CS_docUrl\"><span>#CS_name</span></a> application is now running.<br />\n" +
                "      To modify it, <a href=\"https://grandcentral.cloudbees.com/user/ssh_keys\">\n" +
                "      upload your public key (for git) here</a> if you haven't already.\n" +
                "      <br>Then clone your project:\n" +
                "    </p>\n" +
                "    <div class=\"CB_codeSample\">\n" +
                "      git clone #CS_source #CS_appName<br/>\n" +
                "          cd #CS_appName<br/>\n" +
                "          ---- do your magic edits ----<br/>\n" +
                "          git commit -m \"This is now even better\"<br/>\n" +
                "          git push origin master\n" +
                "    </div>\n" +
                "    <p>That is it ! This will trigger your build/deploy pipline and publish your change</p>\n" +
                "    <p>We have set up all the moving parts for you - the management urls can be found on the following urls:</p>\n" +
                "    <ul>\n" +
                "      <li><strong>App console:</strong> <a href=\"#CS_appManageUrl\">#CS_appManageUrl</a></li>\n" +
                "      <li><strong>Jenkins Build System:</strong> <a href=\"#CS_jenkinsUrl\">#CS_jenkinsUrl</a></li>\n" +
                "      <li><strong>Source repositories:</strong> <a href=\"#CS_forgeUrl\">#CS_forgeUrl</a></li>\n" +
                "    </ul>\n" +
                "  </div>\n" +
                "  <script type=\"text/javascript\" src=\"https://ajax.googleapis.com/ajax/libs/jquery/1.4.0/jquery.min.js\"></script>\n" +
                "  <script type=\"text/javascript\" src=\"https://s3.amazonaws.com/cloudbees-downloads/clickstart/clickstart_intro.js\"></script>\n" +
                "  <!-- end clickstart intro section -->";
        response.getWriter().println("<h1><code>Jetty on CloudBees</code></h1><a href='https://github.com/CloudBees-community/jetty-clickstart'>more</a>" + popover);
    }

    public static void main(String[] args) throws Exception
    {
        Server server = new Server(Integer.parseInt(System.getProperty("app.port", "8080")));
        server.setHandler(new HelloWorld());
        server.start();
        server.join();
    }
}