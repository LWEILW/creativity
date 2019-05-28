package com.example.demo.controller;

import com.example.demo.entity.ItemIntroduce;
import com.example.demo.entity.User;
import com.example.demo.server.ItemServer;
import com.example.demo.server.UserServer;
import com.example.demo.util.WorderToNewWordUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserServer userServer;
    @Autowired
    private ItemServer itemServer;

    @GetMapping("/userDetail")
    public void userDetail() {
        User user = userServer.getUserById(1);
        ItemIntroduce itemIntroduce = itemServer.getItemById(1);
        System.out.println(user);
        System.out.println(itemIntroduce);
        System.out.println("I can write code!");
    }

    // 运营日报—导出Word文档
    @GetMapping("export/{operateId}")
    public void export(HttpServletRequest request, HttpServletResponse response, @PathVariable("operateId") int operateId) {
        String oper = "export word";
        Map<String, Object> params = new HashMap<>();
        params.put("Weather", "111");
        params.put("Temperature", "222");
        params.put("WriteName", "333");


        // 工具类生成word文档（本地）
//        WorderToNewWordUtils.exportWord("C:/Users/DELL/Desktop/operateWord.docx", "C:/Users/DELL/Desktop", "operateWordTest.docx",
//                params, request, response, operationalProjectTimetables, operationalProjectDelayList, operationalProjectVehicleList, operationalProjectEventList, operationalProjectFaultList);


        // 工具类生成word文档（服务器）
        WorderToNewWordUtils.exportWord(
                "/pictures/operateWord.docx",
                "/pictures",
                "wordTest.docx", params, request, response);
    }

}
