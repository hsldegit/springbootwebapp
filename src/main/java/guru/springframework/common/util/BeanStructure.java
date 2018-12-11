package guru.springframework.common.util;


import java.util.HashMap;
import java.util.Map;

/**
 *
 */
public class BeanStructure {
    //
    private Map<String, BeanProperty> properties = new HashMap<String, BeanProperty>();

    public Map<String, BeanProperty> getProperties() {
        return properties;
    }

    public void setProperties(Map<String, BeanProperty> properties) {
        this.properties = properties;
    }

}
