package ru.started.javaEE;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@WebServlet(name = "Product", urlPatterns = "/product")
public class BasicServlet implements Servlet {

    private static Logger logger = LoggerFactory.getLogger(BasicServlet.class);
    private transient ServletConfig config;

    @Override
    public void init(ServletConfig servletConfig) throws ServletException {
        this.config = config;
    }

    @Override
    public ServletConfig getServletConfig() {
        return config;
    }

    @Override
    public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
        logger.info("New request");

        List<Product> list = new ArrayList<>();
        list.add(new Product("apple",  102.20));
        list.add(new Product("orange", 200.00));
        list.add(new Product("banana",  78.00));
        list.add(new Product("pineapple",  300.50));
        list.add(new Product("kiwi",  88.80));
        list.add(new Product("mango",  130.40));
        list.add(new Product("pear",  50.40));
        list.add(new Product("lemon",  175.50));
        list.add(new Product("grapes",  248.80));
        list.add(new Product("plum",  198.40));

        // получаем объект типа BufferedWriter и пишем в него ответ на пришедший запрос
        res.getWriter().println("<html>\n" +
                "<head>\n" +
                "    <title>Product</title>\n" +
                "</head>\n" +
                "<body>\n" +
                "    <h1>Product</h1>");
        for (int i = 0; i < list.size(); i++) {
            res.getWriter().println("<p>" + list.get(i).toString() + "</p>");
        }
        res.getWriter().println("</body>\n</html>");

    }

    @Override
    public String getServletInfo() {
        return "BasicServlet";
    }

    @Override
    public void destroy() {
        logger.info("Servlet {} destroyed", getServletInfo());
    }
}
