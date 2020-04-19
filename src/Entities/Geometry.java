/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Geometry {

private String type;
private List<Double> coordinates = null;
private Map<String, Object> additionalProperties = new HashMap<String, Object>();

public String getType() {
return type;
}

public void setType(String type) {
this.type = type;
}

public List<Double> getCoordinates() {
return coordinates;
}

public void setCoordinates(List<Double> coordinates) {
this.coordinates = coordinates;
}

public Map<String, Object> getAdditionalProperties() {
return this.additionalProperties;
}

public void setAdditionalProperty(String name, Object value) {
this.additionalProperties.put(name, value);
}

    @Override
    public String toString() {
        return "Geometry{" + "type=" + type + ", coordinates=" + coordinates + ", additionalProperties=" + additionalProperties + '}';
    }

}