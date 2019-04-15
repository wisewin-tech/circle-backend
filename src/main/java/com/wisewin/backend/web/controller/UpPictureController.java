package com.wisewin.backend.web.controller;

import com.aliyun.oss.OSSClient;
import com.wisewin.backend.entity.dto.ResultDTOBuilder;
import com.wisewin.backend.util.JsonUtils;
import com.wisewin.backend.util.ReadPropertiesUtil;
import com.wisewin.backend.web.controller.base.BaseCotroller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

/**
 * 图片上传
 */
@Controller("/upImage")
public class UpPictureController extends BaseCotroller{

    @RequestMapping("/upImage")
    public void upImage(HttpServletRequest request, HttpServletResponse response, MultipartFile image)
            throws IOException {
        if (image == null){
            String json = JsonUtils.getJsonString4JavaPOJO
                    (ResultDTOBuilder.failure("0000001","数据异常"));
            super.safeJsonPrint(response, json);

        }
        //读取配置文件里的信息上传信息
        ReadPropertiesUtil rp=ReadPropertiesUtil.getInstance();
        rp.setPropertiesDataSource("system_config.properties");
        String endpoint    =    rp.getString("endpoint");
        String accessKeyId =    rp.getString("accessKeyId");
        String accessKeySecret = rp.getString("accessKeySecret");
        String bucketName  =    rp.getString("bucketName");
        String urlname     =    rp.getString("urlname");
        // 创建OSSClient实例。
        OSSClient ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);
        // 上传内容到指定的存储空间（bucketName）并保存为指定的文件名称（objectName）。
        InputStream inputStream = image.getInputStream();//FileInputStream("<yourlocalFile>");
        String name = image.getOriginalFilename().substring(image.getOriginalFilename().lastIndexOf("."));
        name = UUID.randomUUID().toString() + name;
        ossClient.putObject(bucketName, name, inputStream);
        String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success
                (urlname+ name));
        super.safeJsonPrint(response,json);
    }

}
