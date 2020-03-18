package xmu.graph.entity;

import java.util.HashMap;
import java.util.Map;

/**
 * @author 张铭翔
 * @date 1:13 2020/1/14
 */
public class Object {
    /**
     * 节点标签
     */
    private String label;

    /**
     * 节点属性键值对
     */
    private Map<String,Object> properties;

    public Object(){properties=new HashMap<>();}

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public Map<String, Object> getProperties() {
        return properties;
    }

    public void setProperties(Map<String, Object> properties) {
        this.properties = properties;
    }

    /**
     * 增加属性
     */
    public void addProperty(String key,Object value){
        properties.put(key, value);
    }

    /**
     * 查找属性
     */
    public Object getPropertyByKey(String key){return properties.get(key);}

    /**
     * 删除属性
     */
    public void removeProperty(String key){properties.remove(key);}
}
