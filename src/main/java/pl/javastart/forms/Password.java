package pl.javastart.forms;

import jakarta.servlet.annotation.WebServlet;


import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Array;

@WebServlet("/verify")
public class Password extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
//        User user = createUserFromRequest(request);
        String password=request.getParameter("password");
        sendResponse(password, response);
    }

    private void checkPassword(HttpServletRequest request, String password) {
        //String password = request.getParameter("password");
        boolean passwordLength = password.length() > 4;
        System.out.println("Hasło zawiera conajmniej 5 znaków: " + passwordLength);
        System.out.println("Hasło zawiera conajmniej 1 małą literę: " + containsLowerCase(password));
        System.out.println("Hasło zawiera conajmniej 1 dużą literę: " + containsUpperCase(password));
        System.out.println("Hasło zawiera conajmniej 1 cyfrę: " + containsDigit(password));
        System.out.println("Siła hasła: " + passwordStrenght(password));

    }

    private boolean containsLowerCase(String password) {
        boolean isLowerCaseTrue = false;
        char[] passwordArray = password.toCharArray();
        for (int i = 0; i < password.length(); i++) {
            if (Character.isLowerCase(passwordArray[i])) {
                isLowerCaseTrue = true;
                break;
            }
            ;

        }
        return isLowerCaseTrue;
    }

    private boolean containsUpperCase(String password) {
        char[] passwordArray = password.toCharArray();
        boolean isUpperCaseTrue = false;
        for (int i = 0; i < password.length(); i++) {
            if (Character.isLowerCase(passwordArray[i])) {
                isUpperCaseTrue = true;
                break;
            }
            ;
        }
        return isUpperCaseTrue;
    }

    private boolean containsDigit(String password) {
        char[] passwordArray = password.toCharArray();
        boolean isDigitTrue = false;
        for (int i = 0; i < password.length(); i++) {
            if (Character.isLowerCase(passwordArray[i])) {
                isDigitTrue = true;
                break;
            }
            ;
        }
        return isDigitTrue;
    }

    private int passwordStrenght(String password) {
        int passwordStrenght = 0;
        if (password.length()>4){passwordStrenght=+25;}else {
            System.out.println("Hasło powinno być minimum 5 znakowe.");
        }
        if (containsLowerCase(password)){passwordStrenght=+25;}else {
            System.out.println("Hasło powinno zawierać minimum 1 małą literę");
        }
        if (containsUpperCase(password)){passwordStrenght=+25;}else {
            System.out.println("Hasło powinno zawierać minimum 1 dużą literę");
        }
        if (containsDigit(password)){passwordStrenght=+25;}else {
            System.out.println("Hasło powinno zawierać minimum 1 cyfrę");
        }
        System.out.println("Siła hasła to: "+ passwordStrenght + "%");
        return passwordStrenght;
    }

    private void sendResponse( String password, HttpServletResponse response) throws IOException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html");
        PrintWriter writer = response.getWriter();
        writer.println("<html>");
        writer.println("<body>");
        writer.print("<h2>Twoje hasło to: </h2>"+ password);
        writer.print("<div>");
        int passwordStrenght=0;
        if (password.length()>4){passwordStrenght=+25;}else {
            writer.println("Hasło powinno być minimum 5 znakowe.");
        }
        if (containsLowerCase(password)){passwordStrenght=+25;} else {
            writer.println("Hasło powinno zawierać minimum 1 małą literę");
        }
        if (containsUpperCase(password)){passwordStrenght=+25;}else {
            writer.println("Hasło powinno zawierać minimum 1 dużą literę");
        }
        if (containsDigit(password)){passwordStrenght=+25;}else {
            writer.println("Hasło powinno zawierać minimum 1 cyfrę");
        }
        writer.println("Siła hasła to: "+ passwordStrenght + "%");
        if (passwordStrenght==100){
            writer.println("Hasło spełnia warunki");
        }
        writer.print("</div>");
        writer.println("</body>");
        writer.println("</html>");
    }
}
