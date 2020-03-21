package com.student.community.controller;

import com.student.community.enums.StatusCode;
import com.student.community.service.IUserService;
import com.student.community.vo.User;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/person")
public class PersonalInformationController {

    //图片上传的真实路径
    @Value("${file.uploadFolder}")
    private String uploadFolder;
    //图片读取的请求路径
    @Value("${file.staticAccessPath}")
    private String staticAccessPath;

    @Autowired
    private IUserService userService;

    @RequestMapping(value = "/show")
    public String show() {
        return "personalInformation";
    }

    @RequestMapping(value="/selectUserMessage",method = RequestMethod.GET)
    public @ResponseBody
    Map<String, Object> selectUserMessage(HttpServletRequest request){
        Map<String,Object> result=new HashMap<>();
        User user=(User)request.getSession().getAttribute("user");
        if (user==null){
            result.put("code", StatusCode.USER_IS_NOT_EXISTS.getType());
            result.put("msg", StatusCode.USER_IS_NOT_EXISTS.getDesc());
            return result;
        }
        User dbUser=userService.selectUserById(user);
        if (dbUser==null){
            result.put("code", StatusCode.SELECT_WRONG.getType());
            result.put("msg", StatusCode.SELECT_WRONG.getDesc());
            return result;
        }
        result.put("code", StatusCode.SUCCESS.getType());
        result.put("msg", StatusCode.SUCCESS.getDesc());
        result.put("user",dbUser);
        return result;
    }

    @RequestMapping(value = "/uploadAvatar", method = RequestMethod.POST)
    public @ResponseBody
    Map<String, Object> uploadAvatar(@RequestParam(value = "imgUploadFormInput", required = true) MultipartFile file,
                                     HttpServletRequest request) {
        Map<String, Object> result = new HashMap<>();
        String trueFileName = file.getOriginalFilename();
        String suffix = trueFileName.substring(trueFileName.lastIndexOf("."));
        String fileName = System.currentTimeMillis() + "_" + RandomStringUtils.randomNumeric(10) + suffix;
        String path = uploadFolder;
        File targetFile = new File(path, fileName);
        if (!targetFile.exists()) {
            try {
                targetFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            file.transferTo(targetFile);
        } catch (Exception e) {
            e.printStackTrace();
        }

        //更新数据库中用户的头像地址
        User user = (User) request.getSession().getAttribute("user");
        user.setAvatarUrl(staticAccessPath + fileName);
        int flag=userService.updateUseById(user);
        if (flag>0){
            result.put("code", StatusCode.SUCCESS.getType());
            result.put("msg", StatusCode.SUCCESS.getDesc());
            result.put("url", staticAccessPath + fileName);
            return result;
        }
        result.put("code", StatusCode.UPDATE_WRONG.getType());
        result.put("msg", StatusCode.UPDATE_WRONG.getDesc());
        return result;
    }


    @RequestMapping(value = "/upadteUserAndPwd", method = RequestMethod.POST)
    public @ResponseBody
    Map<String, Object> upadteUserAndPwd(@RequestBody User user,
                                     HttpServletRequest request) {
        Map<String, Object> result = new HashMap<>();
        user.setUpdateTime(System.currentTimeMillis());
        User userSession=(User)request.getSession().getAttribute("user");
        if (userSession==null){
            result.put("code", StatusCode.USER_IS_NOT_EXISTS.getType());
            result.put("msg", StatusCode.USER_IS_NOT_EXISTS.getDesc());
            return result;
        }
        user.setId(userSession.getId());
        int flag=userService.updateUserAndPwdById(user);
        if (flag==1){
            result.put("code", StatusCode.SUCCESS.getType());
            result.put("msg", StatusCode.SUCCESS.getDesc());
            return result;
        }else{
            result.put("code", StatusCode.UPDATE_WRONG.getType());
            result.put("msg", StatusCode.UPDATE_WRONG.getDesc());
            return result;
        }
    }

}
