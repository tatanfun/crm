package service.impl;

import mapper.ProductTypeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseBody;
import pojo.ProductType;
import pojo.ProductTypeExample;
import service.ProductTypeService;

import java.util.List;

@Service
public class ProductTypeServiceImpl implements ProductTypeService {

    //创建数据访问层对象
    @Autowired
    ProductTypeMapper productTypeMapper;

    @Override
    public List<ProductType> getAll() {
        return productTypeMapper.selectByExample(new ProductTypeExample());
    }
}
