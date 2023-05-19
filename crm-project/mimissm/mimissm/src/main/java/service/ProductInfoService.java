package service;

import com.github.pagehelper.PageInfo;
import pojo.ProductInfo;

import java.util.List;

public interface ProductInfoService {

    //显示全部商品不分页
    List<ProductInfo> getAll();

    //实现分页功能
    PageInfo splitPage(int pageNum,int pageSize);

    //增加商品
    int save(ProductInfo info);

    //按主键id查询商品
    ProductInfo getById(int id);

    //更新商品
    int update(ProductInfo info);

    //删除单个商品
    int delete(int pid);

    //批量删除商品
    int deleteBatch(String []ids);
}
