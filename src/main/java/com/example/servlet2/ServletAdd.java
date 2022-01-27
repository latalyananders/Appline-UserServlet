package com.example.servlet2;

import com.example.servlet2.logic.Model;
import com.example.servlet2.logic.User;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.nio.Buffer;
import java.util.concurrent.atomic.AtomicInteger;

@WebServlet(name = "add", value = "/add")
public class ServletAdd extends HttpServlet {

    private AtomicInteger counter = new AtomicInteger(4);
    Model model = Model.getInstance();

    Gson gson = new GsonBuilder().setPrettyPrinting().create();

//    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
//        response.setContentType("text/html;charset=utf-8");
//        request.setCharacterEncoding("UTF-8");
//        PrintWriter pw = response.getWriter();
//
//        String name = request.getParameter("name");
//        String surname = request.getParameter("surname");
//        double salary = Double.parseDouble(request.getParameter("salary"));
//
//        User user = new User(name, surname, salary);
//        model.add(user, counter.getAndIncrement());
//
//        pw.println("<html>" +
//                "<h3>Пользователь " + name + " " + surname + " с зарплатой " + salary + " успешно создан :)</h3>" +
//                "<a href=\"addUser.html\"> Создать нового нользователя</a>" +
//                "<a href=\"index.jsp\"> Домой</a>" +
//                "</html>");
//    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException{
        StringBuffer sb = new StringBuffer();
        String line;

        try {
            BufferedReader reader = request.getReader();
            while (((line = reader.readLine()) != null))
                sb.append(line);
        }
        catch (Exception e){
            System.out.println("Error");
        }

        JsonObject jsonObject = gson.fromJson(String.valueOf(sb), JsonObject.class);
        request.setCharacterEncoding("UTF-8");

        String name = jsonObject.get("name").getAsString();
        String surname = jsonObject.get("surname").getAsString();
        double salary = jsonObject.get("salary").getAsDouble();

        User user = new User(name, surname, salary);
        model.add(user, counter.getAndIncrement());

        response.setContentType("application/json;charset=utf-8");
        PrintWriter pw = response.getWriter();
        pw.println(gson.toJson(model.getFromList()));

    }


}
