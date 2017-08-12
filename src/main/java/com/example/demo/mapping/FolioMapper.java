package com.example.demo.mapping;

import com.example.demo.domain.KeyValue;
import org.springframework.util.CollectionUtils;

import java.beans.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by biba on 12/08/2017.
 */
public class FolioMapper<T> {

    private Class<T> mapperClass;
    private List<KeyValue> customValues;

    public FolioMapper(Class<T> mappedClass) {
        this.mapperClass = mappedClass;
    }

    public FolioMapper(Class<T> mapperClass, List<KeyValue> customValues) {
        this.mapperClass = mapperClass;
        this.customValues = customValues;
    }

    public T map(List<KeyValue> keyValueList) {
        T toReturn = null;
        try {
            toReturn = mapperClass.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        // fields
        for (KeyValue xmlValue : keyValueList) {
            mapAttribute(toReturn, xmlValue);
        }
        // custom Fields
        if(!CollectionUtils.isEmpty(customValues)){
            for (KeyValue customValue : customValues){
                mapAttribute(toReturn, customValue);
            }
        }
        return toReturn;
    }

    private void mapAttribute(T target, KeyValue xmlValue) {
        Statement statement = new Statement(target, xmlValue.getKey(), new Object[]{
                xmlValue.getValue()}

        );
        try {
            statement.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<T> mapList(List<List<KeyValue>> items) {
        List<T> toReturn = new ArrayList<T>();
        for (List<KeyValue> item: items){
            T bean = map(item);
            toReturn.add(bean);
        }
        return toReturn;
    }
}
