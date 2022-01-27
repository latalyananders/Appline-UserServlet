package com.example.servlet2;

import com.example.servlet2.logic.Model;
import com.example.servlet2.logic.Result;
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

@WebServlet(name = "delete", value = "/delete")
public class ServletDelete extends HttpServlet {

    Gson gson = new GsonBuilder().setPrettyPrinting().create();

    Model model = Model.getInstance();

    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws IOException{
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

        PrintWriter pw = response.getWriter();

        response.setContentType("application/json;charset=utf-8");
        int id = jsonObject.get("id").getAsInt();

        if(model.isExists(id)) {
            model.deleteUser(id);

            pw.println(gson.toJson(new Result("Пользователь с id " + id + "удалён")));
        }
        else {

            pw.println(gson.toJson(new Result("Пользователь с id " + id + " не существует")));
        }
    }
}
