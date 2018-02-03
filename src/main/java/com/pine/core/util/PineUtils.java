package com.pine.core.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.pine.core.dto.UserLoginSession;
import lombok.extern.slf4j.Slf4j;

import javax.activation.MimetypesFileTypeMap;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.Closeable;
import java.io.File;
import java.io.FileOutputStream;

/**
 * Created by chenbupang on 2017-11-23 16:39
 */
@Slf4j
public class PineUtils {
    /**
     * 获取session中登录用户信息
     * @return
     */
    public static UserLoginSession getLoginUser(HttpServletRequest request){
        HttpSession session = request.getSession();
        if(session == null){
            return null;
        }
        UserLoginSession userLoginSession = (UserLoginSession) session.getAttribute(PineConst.USER_LOGIN_SESSION);
        return userLoginSession;
    }

    /**
     * 关闭数据流
     * @param closeable
     */
    public static void closeQuietly(Closeable closeable) {
        try {
            if (null == closeable) {
                return;
            }

            closeable.close();
        } catch (Exception var2) {
            log.error("Close closeable error", var2);
        }
    }

    /**
     * 上传文件
     * @param file
     * @param filePath
     * @param fileName
     * @throws Exception
     */
    public static void uploadFile(byte[] file, String filePath, String fileName) throws Exception {
        File targetFile = new File(filePath);
        if(!targetFile.exists()){
            targetFile.mkdirs();
        }
        if(!filePath.endsWith(File.separator)){
            filePath += File.separator;
        }
        FileOutputStream out = new FileOutputStream(filePath+fileName);
        out.write(file);
        out.flush();
        out.close();
    }

    public static void deleteFile(String filePath, String fileName){
        if(!filePath.endsWith(File.separator)){
            filePath += File.separator;
        }
        File targetFile = new File(filePath+fileName);
        if(targetFile.exists()){
            targetFile.delete();
        }
    }
    public static boolean isImage(File file){
        MimetypesFileTypeMap mtftp = new MimetypesFileTypeMap();
        mtftp.addMimeTypes("image png tif jpg jpeg bmp");//不添加下面的类型会造成误判
        String mimetype= mtftp.getContentType(file);
        String type = mimetype.split("/")[0];
        return type.equals("image");
    }

    public static String toJSONString(Object object,boolean format){
        if(object == null){
            return "";
        }
        if(format){
            return JSON.toJSONString(
                    object, SerializerFeature.PrettyFormat,
                    SerializerFeature.WriteNullStringAsEmpty,
                    SerializerFeature.WriteDateUseDateFormat);
        }else{
            return JSON.toJSONString(object);
        }
    }
}
