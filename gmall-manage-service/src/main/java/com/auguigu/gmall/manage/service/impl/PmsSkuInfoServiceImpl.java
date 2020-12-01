package com.auguigu.gmall.manage.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.alibaba.fastjson.JSON;
import com.auguigu.gmall.bean.*;
import com.auguigu.gmall.manage.mapper.*;
import com.auguigu.gmall.service.PmsSkuInfoService;
import com.auguigu.gmall.util.RedisUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import redis.clients.jedis.Jedis;

import java.util.List;

@Service
public class PmsSkuInfoServiceImpl implements PmsSkuInfoService {
    @Autowired
    PmsSkuInfoMapper pmsSkuInfoMapper;

    @Autowired
    PmsSkuImageMapper pmsSkuImageMapper;

    @Autowired
    PmsSkuAttrValueMapper pmsSkuAttrValueMapper;

    @Autowired
    PmsSkuSaleAttrValueMapper pmsSkuSaleAttrValueMapper;

    @Autowired
    PmsProductImageMapper pmsProductImageMapper;

    @Autowired
    RedisUtil redisUtil;

    @Override
    public int saveSkuInfo(PmsSkuInfo pmsSkuInfo) {
        //当用户没有选择默认图片是，自动将图片组里的第一张图片设置为默认图片
        if (pmsSkuInfo.getSkuDefaultImg() == null) {
            pmsSkuInfo.setSkuDefaultImg(pmsSkuInfo.getPmsSkuImageList().get(0).getImgUrl());
            pmsSkuInfo.getPmsSkuImageList().get(0).setIsDefault("1");
        }
        //插入sku对象
        pmsSkuInfoMapper.insertSelective(pmsSkuInfo);
        int skuId = pmsSkuInfo.getId();
        List<PmsSkuImage> pmsSkuImageList = pmsSkuInfo.getPmsSkuImageList();
        List<PmsSkuAttrValue> pmsSkuAttrValueList = pmsSkuInfo.getPmsSkuAttrValueList();
        List<PmsSkuSaleAttrValue> pmsSkuSaleAttrValueList = pmsSkuInfo.getPmsSkuSaleAttrValueList();
        //插入sku图片对象
        for (PmsSkuImage pmsSkuImage : pmsSkuImageList) {
            //设置sku图片的相关联的skuId
            pmsSkuImage.setSkuId(skuId);
            //设置sku图片相关联的productId
            PmsProductImage pmsProductImage = new PmsProductImage();
            pmsProductImage.setImgUrl(pmsSkuImage.getImgUrl());
            PmsProductImage pmsProductImage1 = pmsProductImageMapper.selectOne(pmsProductImage);
            pmsSkuImage.setProductImgId(pmsProductImage1.getId());
            pmsSkuImageMapper.insertSelective(pmsSkuImage);
        }
        //插入sku平台属性对象
        for (PmsSkuAttrValue pmsSkuAttrValue : pmsSkuAttrValueList) {
            pmsSkuAttrValue.setSkuId(skuId);
            pmsSkuAttrValueMapper.insertSelective(pmsSkuAttrValue);
        }
        //插图sku销售属性对象
        for (PmsSkuSaleAttrValue pmsSkuSaleAttrValue : pmsSkuSaleAttrValueList) {
            pmsSkuSaleAttrValue.setSkuId(skuId);
            pmsSkuSaleAttrValueMapper.insertSelective(pmsSkuSaleAttrValue);
        }
        return 1;
    }

    //从数据库中查询Sku数据
    public PmsSkuInfo selectBySkuIdFromDB(Integer skuId) {
        PmsSkuInfo pmsSkuInfo=new PmsSkuInfo();
        pmsSkuInfo.setId(skuId);
        PmsSkuInfo pmsSkuInfo1=pmsSkuInfoMapper.selectOne(pmsSkuInfo);
        //sku的图片集合
        PmsSkuImage pmsSkuImage=new PmsSkuImage();
        pmsSkuImage.setSkuId(skuId);
        List<PmsSkuImage> pmsSkuImageList=pmsSkuImageMapper.select(pmsSkuImage);
        pmsSkuInfo1.setPmsSkuImageList(pmsSkuImageList);
        //平台属性
        PmsSkuAttrValue pmsSkuAttrValue=new PmsSkuAttrValue();
        pmsSkuAttrValue.setSkuId(skuId);
        List<PmsSkuAttrValue>pmsSkuAttrValueList=pmsSkuAttrValueMapper.select(pmsSkuAttrValue);
        pmsSkuInfo1.setPmsSkuAttrValueList(pmsSkuAttrValueList);
        //销售属性
        PmsSkuSaleAttrValue pmsSkuSaleAttrValue=new PmsSkuSaleAttrValue();
        pmsSkuSaleAttrValue.setSkuId(skuId);
        List<PmsSkuSaleAttrValue>pmsSkuSaleAttrValueList=pmsSkuSaleAttrValueMapper.select(pmsSkuSaleAttrValue);
        pmsSkuInfo1.setPmsSkuSaleAttrValueList(pmsSkuSaleAttrValueList);
        return pmsSkuInfo1;
    }

    @Override
    public PmsSkuInfo selectBySkuId(Integer skuId) {
        PmsSkuInfo pmsSkuInfo=new PmsSkuInfo();
        //连接缓存
        Jedis jedis=redisUtil.getJedis();
        //查询缓存
        String skuKey="sku:"+skuId+":info";
        String skuJson=jedis.get(skuKey);
        //缓存不为空
        if(StringUtils.isNotBlank(skuJson)){
            //通过fastjson将我们的字符串转化成我们对应的Sku对象
            pmsSkuInfo = JSON.parseObject(skuJson, PmsSkuInfo.class);
        }
        else{
            //如果缓存没有,查询mysql
            pmsSkuInfo=selectBySkuIdFromDB(skuId);
            //mysql查询结果存储到Redis
            if(pmsSkuInfo!=null){
                jedis.set("sku:"+skuId+":info", JSON.toJSONString(pmsSkuInfo));
            }
            else{
                //数据库中同样也不存在该数据
            }
        }
        jedis.close();
        return pmsSkuInfo;
    }

    @Override
    public List<PmsSkuInfo> getSkuInfosBySpu(Integer productId) {
        List<PmsSkuInfo> pmsSkuInfoList=pmsSkuInfoMapper.selectSkuInfosBySpu(productId);
        return pmsSkuInfoList;
    }

//    @Override
//    public List<PmsProductSaleAttr> setCheckedSaleAttrValue(PmsSkuInfo pmsSkuInfo, List<PmsProductSaleAttr> pmsProductSaleAttrList) {
//        List<PmsSkuSaleAttrValue>pmsSkuSaleAttrValueList=pmsSkuInfo.getPmsSkuSaleAttrValueList();
//        for(PmsSkuSaleAttrValue pmsSkuSaleAttrValue:pmsSkuSaleAttrValueList){
//            for(PmsProductSaleAttr pmsProductSaleAttr:pmsProductSaleAttrList){
//                for(PmsProductSaleAttrValue pmsProductSaleAttrValue:pmsProductSaleAttr.getPmsProductSaleAttrValueList()){
//                    if(pmsSkuSaleAttrValue.getSaleAttrValueId()==pmsProductSaleAttrValue.getId()){
//                        pmsProductSaleAttrValue.setIsChecked();
//                    }
//                }
//            }
//        }
//        return null;
//    }
}
