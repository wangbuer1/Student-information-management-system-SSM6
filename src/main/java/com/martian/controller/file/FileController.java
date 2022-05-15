package com.martian.controller.file;

import com.martian.common.constant.Constant;
import com.martian.common.exception.BusinessAsserts;
import com.martian.common.exception.Exceptions;
import com.martian.common.result.JSONResult;
import org.apache.commons.collections.map.HashedMap;
import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.Random;

/**
 * 文件控制器
 */
@RestController
public class FileController {
    private static final Logger logger = LoggerFactory.getLogger(FileController.class);
    /**
     * 文件上传的最大值5MB
     */
    private static final int MAX_SIZE = 5 * 1024 * 1024;//5MB

    @RequestMapping("/upload")
    public JSONResult upload(HttpServletRequest request) {
        logger.debug("into /upload");
        //获取文件类型 根据不同的文件类型不同。保存文件在不同的目录下
        String fileType = request.getParameter("fileType");
        //根据模块业务的不同保存在不同的文件目录下
        String module = request.getParameter("module");

        String baseDir = request.getSession().getServletContext().getRealPath(Constant.ATTACHMENT_TEMP_DIR);
        String physicsPath = baseDir + File.separator + module;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
        String relativeDir = "/" + fileType + "/"  + sdf.format(new Date()) + "/" ;
        physicsPath += relativeDir;
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        Map<String, MultipartFile> fileMap = multipartRequest.getFileMap();
        MultipartFile file = fileMap.entrySet().iterator().next().getValue();
        BusinessAsserts.isTrue((file != null && !file.isEmpty()), Exceptions.File.FILE_UPLOAD_NOT_EXIST);

        String fileName = file.getOriginalFilename();
        String suffix = FilenameUtils.getExtension(fileName).toLowerCase();

        // 创建文件夹
        File baseFile = new File(physicsPath);
        if (!baseFile.exists()) {
            baseFile.mkdirs();
        }

        // 检查目录写权限
        BusinessAsserts.isTrue(baseFile.canWrite(), Exceptions.File.FILE_UPLOAD_DIR_NOT_WRITE_PERMISSION);

        // 检查文件大小
        long fileLength = file.getSize();
        BusinessAsserts.isTrue(MAX_SIZE > fileLength, Exceptions.File.FILE_UPLOAD_TOO_BIG);

        String posthumous = getRandomFileName(suffix);
        File dest = new File(physicsPath, posthumous);
        try {
            dest.mkdirs();
            file.transferTo(dest);
        } catch (java.io.FileNotFoundException e) {
            logger.error("系统找不到指定的路径!上传失败!", e);
            BusinessAsserts.isTrue(true, Exceptions.File.FILE_UPLOAD_NOT_FIND_PATH);
        } catch (IOException e) {
            logger.error("文件上传出现错误!", e);
            BusinessAsserts.isTrue(true, Exceptions.File.FILE_UPLOAD_ERROR);
        }
        // db保存路径
        StringBuffer relativePath = new StringBuffer();
        relativePath.append(Constant.ATTACHMENT_TEMP_DIR).append("/").append(module).append(relativeDir).append(posthumous);
        Map<String, Object> resMap = new HashedMap();
        resMap.put("imageName", relativePath);
        return new JSONResult(resMap);
    }

    /***
     *  随机生成文件名
     * @param suffix
     * @return
     */
    public String getRandomFileName(String suffix) {
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
        String randomFileName = df.format(new Date()) + "_" + new Random().nextInt(1000) + "." + suffix;
        return randomFileName;
    }
}
