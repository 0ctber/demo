package com.byd.demo.config.EasyExcelListener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

@Slf4j
public class EasyExcelListener<T> extends AnalysisEventListener<T> {
    /**
     * 缓存的数据
     */
    private ArrayList<T> successList = new ArrayList<>();

    /**
     * 这个每一条数据解析都会来调用
     * @param o
     * @param analysisContext
     */
    @Override
    public void invoke(T o, AnalysisContext analysisContext) {
        successList.add(o);
    }

    /**
     * 所有数据解析完成了 都会来调用
     *
     * @param analysisContext
     */
    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
        log.info(EasyExcelListener.class+"excel导入访问结束");
    }

    public ArrayList<T> getSuccessDatas(){
        return successList;
    }

    public List<LinkedHashMap> getLinkedHashMap(){
        String jsonList = JSON.toJSONString(successList);
        return JSON.parseArray(jsonList,LinkedHashMap.class);
    }
}