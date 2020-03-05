package com.student.community.controller;

import com.student.community.enums.StatusCode;
import com.student.community.service.INotificationService;
import com.student.community.vo.Notification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/notify")
public class NotificationController {

    @Autowired
    private INotificationService notificationService;

    @RequestMapping(value = "/setStatusRead", method = RequestMethod.PUT)
    public @ResponseBody
    Map<String, Object> setStatusRead(@RequestBody Notification notification) {
        Map<String, Object> result = new HashMap<>();
        if (notification.getId() == null) {
            result.put("code", StatusCode.PARAM_ERROR.getType());
            result.put("msg", StatusCode.PARAM_ERROR.getDesc());
            return result;
        }
        int flag=notificationService.updateStatusById(notification.getId());
        if(flag==1){
            result.put("code", StatusCode.SUCCESS.getType());
            result.put("msg", StatusCode.SUCCESS.getDesc());
            return result;
        }
        result.put("code", StatusCode.SERVER_WRONG.getType());
        result.put("msg", StatusCode.SERVER_WRONG.getDesc());
        return result;
    }
}
