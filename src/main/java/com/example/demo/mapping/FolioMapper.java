package com.example.demo.mapping;

import com.example.demo.domain.KeyValue;
import org.springframework.util.CollectionUtils;

import java.beans.Statement;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.List;

public class FolioMapper<T> {

    private Class<T> targetClass;
    private List<KeyValue> customValues;

    public FolioMapper(Class<T> mappedClass) {
        this.targetClass = mappedClass;
    }

    public FolioMapper(Class<T> mapperClass, List<KeyValue> customValues) {

        this.targetClass = mapperClass;
        this.customValues = customValues;
    }

    public T map(List<KeyValue> keyValueList) {
        T toReturn = createTargetClassInstance(targetClass);

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


    private T createTargetClassInstance(Class<T> targetClass){
        T targetClassInstance;
        boolean hasDefaultConstructor = false;

        if(targetClass == null) {
            throw new IllegalArgumentException("Target class should be not null !");
        }

        for(Constructor constructor: targetClass.getConstructors()){
            if(constructor.getParameterCount() == 0)
                hasDefaultConstructor = true;
        }

        if(!hasDefaultConstructor){
            throw new IllegalArgumentException(String.format("Target class %s doesn't have a default constructor", targetClass.getCanonicalName()));
        }

        try {
            targetClassInstance = targetClass.newInstance();
        } catch (InstantiationException e) {
            throw new IllegalArgumentException(String.format("Instantiation of Target class %s failed : %s", targetClass.getCanonicalName(), e.getMessage()));
        } catch (IllegalAccessException e) {
            throw new IllegalArgumentException(String.format("Instantiation of Target class %s failed : %s", targetClass.getCanonicalName(), e.getMessage()));
        }
        return targetClassInstance;

    }
}
