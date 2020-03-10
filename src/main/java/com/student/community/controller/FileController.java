package com.student.community.controller;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/file")
public class FileController {




    @RequestMapping("/imageUploadMarkDown")
    public @ResponseBody
    Map<String,Object> imageUploadMarkDown(@RequestParam(value = "editormd-image-file", required = true) MultipartFile file,
                                           HttpServletRequest request,
                                           HttpServletResponse response){
        Map<String,Object> result=new HashMap<>();

        String trueFileName = file.getOriginalFilename();
        String suffix = trueFileName.substring(trueFileName.lastIndexOf("."));
        String fileName = System.currentTimeMillis()+"_"+ RandomStringUtils.randomNumeric(10) +suffix;
//        String path = uploadPath;
        String path = this.getClass().getResource("/").getPath()+"static/image/";

        File targetFile = new File(path, fileName);
        if(!targetFile.exists()){
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

        result.put("success",1);
        result.put("message","测试");
        result.put("url", "/static/image/"+fileName);
        return result;
    }
}
