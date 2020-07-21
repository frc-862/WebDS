package lightning.webds.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class IssueController implements ErrorController {

    @RequestMapping("/error")
    @ResponseBody
    public String handleError(HttpServletRequest request) {
        Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
        Exception exception = (Exception) request.getAttribute("javax.servlet.error.exception");
        return String.format(
            "<html xmlns:th=%s><head><title>Error</title>" 
                + "<th:block th:include=%s></th:block></head>"
                + "<body><center>"
                + "<h2>An Unexpected Error Has Been Encountered</h2>"
                + "<p>Status code: <b>%s</b></p>"
                + "<p>Exception Message: <b>%s</b></p>"
                + "<p class=%s>Please return to the main page. We will try to resolve this issue.</p>"
                + "<button class=%s><a href=%s>Home Page</a></button>"
                + "</center></body></html>",
            "http://www.thymeleaf.org", 
            "fragments/common.html :: headerfiles", 
            statusCode,
            exception == null ? "N/A" : exception.getMessage(), 
            "lead",
            "btn btn-primary",
            "/"
        );

    }

    @Override
    public String getErrorPath() {
        return "/error";
    }

}