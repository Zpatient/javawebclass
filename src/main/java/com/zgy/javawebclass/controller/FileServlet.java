package com.zgy.javawebclass.controller;
/**
 * @author zgy
 * @create 2022-12-13 18:33
 */
import com.zgy.javawebclass.bean.FileInfo;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.ProgressListener;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.*;
import java.net.URLEncoder;
import java.util.List;
import java.util.UUID;

@WebServlet(name = "FileServlet", value = "/file")
public class FileServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1. 获取下载文件路径
        ServletContext context = this.getServletContext();
        String realPath = this.getServletContext().getRealPath("/WEB-INF/classes/1.doc");
        //2. 获取下载文件名
        String fileName = realPath.substring(realPath.lastIndexOf("\\")+1);
        //3. 浏览器设置下载文件方法, URLEncoder.encode解决中文乱码
        resp.setHeader("content-disposition","attachment;filename="+ URLEncoder.encode(fileName));
        //4. 获取下载文件的输入流
        FileInputStream in = new FileInputStream(realPath);
        //5. 创建缓冲区
        int len=0;
        byte[] buffer =new byte[1024];
        //6. 获取输出对象
        ServletOutputStream out = resp.getOutputStream();
        //7. 将缓冲区中的数据输出
        while ((len=in.read(buffer))>0)
            out.write(buffer,0,len);
        in.close();
        out.close();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1. 判断表单是否带上传文件
        if(!ServletFileUpload.isMultipartContent(req))
        {
            return;
        }
        //2. 创建上传文件保存在服务器中的目录，建议在WEB-INF路径下，可以一定程度上保证安全
        String uploadPath = this.getServletContext().getRealPath("/WEB-INF/upload");
        System.out.println("上传文件根目录："+uploadPath);
        File uploadFile = new File(uploadPath);
        if (!uploadFile.exists())
        {
            uploadFile.mkdir();
        }
        //3. 创建磁盘文件库
        DiskFileItemFactory diskFileItemFactory = new DiskFileItemFactory();
        //4. 获取servlet文件上传对象
        ServletFileUpload fileUpload = new ServletFileUpload(diskFileItemFactory);
        //4.1 监控上传进度
        fileUpload.setProgressListener(new ProgressListener() {
            public void update(long l, long l1, int i) {
                System.out.println("文件总大小："+l1+" 已上传："+l);
            }
        });
        //4.2 处理乱码问题
        fileUpload.setHeaderEncoding("UTF-8");
        //4.3 设置单个文件大小的最大值
        fileUpload.setFileSizeMax(1024*1024*10);
        //4.4 设置总共能上传的文件大小最大值
        fileUpload.setSizeMax(1024*1024*50);
        //5. 处理上传的文件
        try {
            //5.1 获取表单中的每一个控件
            List<FileItem> fileItems = fileUpload.parseRequest(req);
            for (FileItem fileItem : fileItems) {
                if (fileItem.isFormField()) {//当前控件为普通表单
                    String fieldName = fileItem.getFieldName();
                    String value = fileItem.getString("UTF-8");
                    System.out.println(fieldName + ":" + value);
                } else//当前控件为带文件的表单
                {
                    //5.2 获取文件路径
                    String uploadFileName = fileItem.getName();
                    //5.3 对获取的文件字符串路径进行处理
                    if (uploadFileName.trim().equals("") || uploadFileName == null)
                        continue;
                    //5.3.1  获取文件名
                    String fileName = uploadFileName.substring(0,uploadFileName.lastIndexOf("."));
                    System.out.println("文件信息："+"[文件名："+fileName+"]");
                    //5.4 生成唯一的字符串，标识文件名，保证文件不会因为重名和覆盖
                    UUID uuidName = UUID.randomUUID();
                    //5.5 为上传的文件创建一个唯一命名的文件夹
                    String realUploadPath = uploadPath + "//" + uuidName;
                    File realUploadFile = new File(realUploadPath);
                    if (!realUploadFile.exists())
                        realUploadFile.mkdir();
                    //5.6 将上传的文件保存到上面存储的唯一文件夹中
                    //5.6.1  获取上传文件的流
                    InputStream inputStream = fileItem.getInputStream();
                    //5.6.2 将文件流写出到指定服务器文件
                    FileOutputStream fos = new FileOutputStream(realUploadPath + "//" + uploadFileName);
                    byte[] buffer = new byte[1024 * 1024];
                    int len = 0;
                    while ((len = inputStream.read(buffer)) > 0) {
                        fos.write(buffer, 0, len);
                    }
                    fos.close();
                    inputStream.close();
                    fileItem.delete();//上传成功，清除临时文件
                }
            }
        } catch (FileUploadException e) {
            e.printStackTrace();
        }
        FileInfo fileInfo = new FileInfo();
        // 设置返回字符集
        resp.setContentType("charset=utf-8");
        String result = "{\"name\":\"Marydon\"}";
        // 返回数据
        resp.getWriter().print(result);
        String msg="文件上传成功";
        System.out.println(msg);
        //请求转发
        req.getRequestDispatcher("upload.jsp").forward(req,resp);
    }
}
