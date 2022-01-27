package com.example.servlet2;

import com.example.servlet2.logic.Model;
import com.example.servlet2.logic.Result;
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
import java.util.Map;

@WebServlet(name = "get", value = "/get")
public class ServletList extends HttpServlet {
    Model model = Model.getInstance();

    Gson gson = new GsonBuilder().setPrettyPrinting().create();

//    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
//        response.setContentType("text/html;charset=utf-8");
//        PrintWriter pw = response.getWriter();
//
//        int id = Integer.parseInt(request.getParameter("id"));
//
//        if(id == 0){
//            pw.println("<html>"+
//                    "<h3>Доступные пользователи</h3>"+
//                    "ID пользователя: "+
//                    "<ul>");
//                    //"</html>");
//            for(Map.Entry<Integer, User> entry : model.getFromList().entrySet()){
//                pw.println("<li>ID: " + entry.getKey() + " " + entry.getValue().toString() + "</li>");
//            }
//            pw.println("</ul><a href=\"index.jsp\"> Домой</a></html>");
//            return;
//        }
//        else if(id > 0) {
//            if (id < model.getFromList().size()) {
//                pw.println("<html>" +
//                        "<h3>Пользователь с ID " + id + " " + model.getFromList().get(id).toString() +
//                        "</3><br/><a href=\"index.jsp\">Домой</a></html>");
//                return;
//            }
//            else {
//                pw.println("<html>" +
//                        "<h3>Пользователь с ID " + id + " не существует" +
//                        "</3><br/><a href=\"index.jsp\">Домой</a></html>");
//                return;
//            }
//        }
//        else {
//            pw.println("<html>" +
//                    "<h3>ID должен быть больше нуля :(" +
//                    "</h3><br/><a href=\"index.jsp\"> Домой</a></html>");
//        }
//    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
//        StringBuffer sb = new StringBuffer();
//        String line;
//
//        try {
//            BufferedReader reader = request.getReader();
//            while (((line = reader.readLine()) != null))
//                sb.append(line);
//        }
//        catch (Exception e){
//            System.out.println("Error");
//        }
//
//        JsonObject jsonObject = gson.fromJson(String.valueOf(sb), JsonObject.class);
//        request.setCharacterEncoding("UTF-8");

        int id = Integer.parseInt(request.getParameter("id"));

        response.setContentType("application/json;charset=utf-8");
        PrintWriter pw = response.getWriter();

        if(id == 0){
            pw.println(gson.toJson(model.getFromList()));
            return;
        }
        else if(id > 0) {
            if (id < model.getFromList().size()) {
                pw.println(gson.toJson(model.getFromList().get(id)));
                return;
            }
            else {
                pw.println(gson.toJson(new Result("Пользователь с id " + id + "не существует")));
//                pw.println("<html>" +
//                        "<h3>Пользователь с ID " + id + " не существует" +
//                        "</3><br/><a href=\"index.jsp\">Домой</a></html>");
                return;
            }
        }
        else {
            pw.println(gson.toJson(new Result("ID должен быть больше нуля")));
//            pw.println("<html>" +
//                    "<h3>ID должен быть больше нуля :(" +
//                    "</h3><br/><a href=\"index.jsp\"> Домой</a></html>");
        }
    }
}
