package com.controller;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.util.CpachaUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

/*上传*/
@Controller
@RequestMapping("/system")
public class SystemAction {

    // 后台主界面
    @RequestMapping("main.action")
    public String main(Map<String, Object> map) {
        return "admin/main";//跳转到WebContent目录下对应的JSP页面
    }


    /*上传图片*/
    @RequestMapping(value = "/image.action")
    public String upload(@RequestParam(value = "image", required = false) MultipartFile file, HttpServletRequest request, ModelMap model) {

        String path = request.getSession().getServletContext().getRealPath("image");
        String fileName = file.getOriginalFilename();
        int i = fileName.lastIndexOf(".");


        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("MMddHHmmss");
        String name = formatter.format(currentTime);

        String type = fileName.substring(i + 1);
        fileName = name + "." + type;
        File targetFile = new File(path, fileName);
        if (!targetFile.exists()) {
            targetFile.mkdirs();
        }

        // 保存
        try {
            file.transferTo(targetFile);
        } catch (Exception e) {
            e.printStackTrace();
        }
        model.addAttribute("imageFileName", fileName);
        return "Web/saveimage";
    }

    /**
     * 本系统所有的验证码均采用此方法
     * @param vcodeLen
     * @param width
     * @param height
     * @param cpachaType:用来区别验证码的类型，传入字符串
     * @param request
     * @param response
     */
    @RequestMapping(value="/getCpacha.action")
    public void generateCpacha(
            @RequestParam(name="vl",required=false,defaultValue="4") Integer vcodeLen,
            @RequestParam(name="w",required=false,defaultValue="100") Integer width,
            @RequestParam(name="h",required=false,defaultValue="30") Integer height,
            @RequestParam(name="type",required=true,defaultValue="loginCpacha") String cpachaType,
            HttpServletRequest request,
            HttpServletResponse response){
        CpachaUtil cpachaUtil = new CpachaUtil(vcodeLen, width, height);
        String generatorVCode = cpachaUtil.generatorVCode();
        request.getSession().setAttribute(cpachaType, generatorVCode);
        BufferedImage generatorRotateVCodeImage = cpachaUtil.generatorRotateVCodeImage(generatorVCode, true);
        try {
            ImageIO.write(generatorRotateVCodeImage, "gif", response.getOutputStream());
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
