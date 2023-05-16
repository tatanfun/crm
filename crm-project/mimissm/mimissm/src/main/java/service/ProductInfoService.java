package service;

import com.github.pagehelper.PageInfo;
import pojo.ProductInfo;

import java.util.List;

public interface ProductInfoService {

    //显示全部商品不分页
    List<ProductInfo> getAll();

    //实现分页功能
    PageInfo splitPage(int pageNum,int pageSize);

}
