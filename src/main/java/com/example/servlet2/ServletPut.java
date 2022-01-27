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

@WebServlet(name = "put", value = "/put")
public class ServletPut extends HttpServlet {
    Gson gson = new GsonBuilder().setPrettyPrinting().create();

    Model model = Model.getInstance();

    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws IOException {
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

        int id = jsonObject.get("id").getAsInt();
        String name = jsonObject.get("name").getAsString();
        String surname = jsonObject.get("surname").getAsString();
        double salary = jsonObject.get("salary").getAsDouble();


        PrintWriter pw = response.getWriter();
        response.setContentType("application/json;charset=utf-8");

        if(model.isExists(id)) {
            User result = model.updateUser(id, name, surname, salary);

            pw.println(gson.toJson(result));
        }
        else {

            pw.println(gson.toJson(new Result("Пользователь с id " + id + " не существует")));
        }
    }
}
