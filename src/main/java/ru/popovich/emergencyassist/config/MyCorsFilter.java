package ru.popovich.emergencyassist.config;

import com.sun.net.httpserver.Filter;
import com.sun.net.httpserver.HttpExchange;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


//@Component
//@Order(1)
public class MyCorsFilter extends Filter {

    @Override
    public void doFilter(HttpExchange httpExchange, Chain chain) throws IOException {

        HttpServletResponse response = (HttpServletResponse) httpExchange.getResponseBody();
        HttpServletRequest request = (HttpServletRequest) httpExchange.getRequestBody();
//        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Origin", "http://localhost:3000");
        response.setHeader("Access-Control-Allow-Credentials", "true");
        response.setHeader("Access-Control-Allow-Methods",
                "ACL, CANCELUPLOAD, CHECKIN, CHECKOUT, COPY, DELETE, GET, HEAD, LOCK, MKCALENDAR, MKCOL, MOVE, OPTIONS, POST, PROPFIND, PROPPATCH, PUT, REPORT, SEARCH, UNCHECKOUT, UNLOCK, UPDATE, VERSION-CONTROL");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Headers",
                "Origin, X-Requested-With, Content-Type, Accept, Key, Authorization");

        if ("OPTIONS".equalsIgnoreCase(request.getMethod())) {
            response.setStatus(HttpServletResponse.SC_OK);
        } else {
            chain.doFilter(httpExchange);
        }

    }

    @Override
    public String description() {
        return null;
    }
}
