package controller;

import com.github.pagehelper.PageInfo;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import pojo.ProductInfo;
import service.ProductInfoService;
import utils.FileNameUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/prod")
public class ProductInfoAction {
    //每页显示的条数
    public static final int PAGE_SIZE = 5;

    //异步上传的图片名称
    String saveFileName="";

    //在界面层一定有业务逻辑层的对象
    @Autowired
    ProductInfoService productInfoService;

    //显示全部商品不分页
    @RequestMapping("/getAll")
    public String getAll(HttpServletRequest request){
        List<ProductInfo> list = productInfoService.getAll();
        request.setAttribute("list",list);
        return "product";
    }

    //显示第一页的五条记录
    @RequestMapping("/split")
    public String split(HttpServletRequest request){
        //获取第一页数据
        PageInfo info = productInfoService.splitPage(1,PAGE_SIZE);
        request.setAttribute("info",info);
        return "product";
    }

    //ajax分页处理
    @ResponseBody
    @RequestMapping("/ajaxsplit")
    public void ajaxSplit(int page, HttpSession session){
        //取得当前page参数的页面的数据
        PageInfo info = productInfoService.splitPage(page,PAGE_SIZE);
        session.setAttribute("info",info);
    }

    //异步ajax文件上传处理
    @ResponseBody
    @RequestMapping("/ajaxImg")
    public Object ajaxImg(MultipartFile pimage , HttpServletRequest request){

        //提取生成文件名UUID+上传图片的后缀.jpg、.png
        saveFileName = FileNameUtil.getUUIDFileName() + FileNameUtil.getFileType(pimage.getOriginalFilename());
        //得到项目中图片存储的路径
        String path  = request.getServletContext().getRealPath("/image_big");
        //转存
        try {
            pimage.transferTo(new File(path + File.separator+saveFileName));
        } catch (IOException e) {
            e.printStackTrace();
        }

        //返回客户端json对象，封装图片的路径，实现页面立即回显
        JSONObject object = new JSONObject();
        object.put("imgurl",saveFileName);

        return object.toString();
    }

    @RequestMapping("/save")
    public String save(ProductInfo info ,HttpServletRequest request){

        info.setpImage(saveFileName);
        info.setpDate(new Date());

        int num = -1;
        try {
            num = productInfoService.save(info);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if(num > 0){
            request.setAttribute("msg","增加成功");
        }else{
            request.setAttribute("msg","增加失败");
        }
        //清空saveFileName变量中的内容，为了下次增加或修改的异步ajax的上传处理
        saveFileName= "";
        //增加成功后重新访问数据库，跳转到分页显示的action上

        return "forward:/prod/split.action";
    }

    @RequestMapping("/one")
    public String one(int pid, Model model){
        ProductInfo info = productInfoService.getById(pid);
        model.addAttribute("prod",info);
        return "update";
    }

    @RequestMapping("/update")
    public String update(ProductInfo info,HttpServletRequest request){
        //如果ajax异步上传过图片，则saveFileName里有上传的图片名称；
        // 如果没有异步上传过，saveFileName="",实体类info就使用隐藏表单域提交上来的pImage原始图片的名称;
        if (!saveFileName.equals("")){
            info.setpImage(saveFileName);
        }

        //完成更新处理
        int num = -1;
        try {
            num = productInfoService.update(info);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (num > 0){
            //更新成功
            request.setAttribute("msg","更新成功");
        }else{
            //更新失败
            request.setAttribute("msg","更新失败");
        }

        //处理完更新后，saveFileName里可能有数据，下一次更新时使用这个变量就会出错，需要手动清空saveFileName.
        saveFileName="";

        //重定向:redirect，请求转发：forward
        return "forward:/prod/split.action";
    }

    @RequestMapping("/delete")
    public String delete(int pid,HttpServletRequest request){
        int num = -1;

        try {
            num = productInfoService.delete(pid);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (num > 0){
            request.setAttribute("msg","删除成功");
        }else{
            request.setAttribute("msg","删除失败");
        }

        //删除完成后跳转到分页显示
        return "forward:/prod/deleteAjaxSplit.action";
    }

    @ResponseBody
    @RequestMapping(value = "/deleteAjaxSplit",produces = "text/html;charset=UTF-8")
    public Object deleteAjaxSplit(HttpServletRequest request){
        //取出第一页的数据
        PageInfo info = productInfoService.splitPage(1,PAGE_SIZE);
        request.getSession().setAttribute("info",info);
        return request.getAttribute("msg");
    }

    //批量删除商品
    @RequestMapping("/deleteBatch")
    public String deleteBatch(String pids,HttpServletRequest request){
        //pids="1,4,5..."---> ps[1,4,5]
        //截取前端提交的商品id的字符串，转换成数组
        String []ps = pids.split(",");
        try {
            int num = productInfoService.deleteBatch(ps);
            if (num > 0){
                request.setAttribute("msg","批量删除成功");
            }else{
                request.setAttribute("msg","批量删除失败");
            }
        } catch (Exception e) {
            request.setAttribute("msg","不可以删除哦");
        }
        return "forward:/prod/deleteAjaxSplit.action";
    }

}


