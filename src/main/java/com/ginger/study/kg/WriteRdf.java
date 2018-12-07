package com.ginger.study.kg;

import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.Property;
import org.apache.jena.rdf.model.RDFNode;
import org.apache.jena.rdf.model.Resource;

/**
 * SPO 三元组SPO(subject,property,object)
 */

public class WriteRdf {

    public static void main(String[] args) {
        // 函数示例
        Model model = ModelFactory.createDefaultModel();
        writeAnTripleWhenObjectIsNode(model, "周杰伦", "妻子", "昆凌");
        model.write(System.out, "N3");
    }

    public static void writeAnTripleWhenObjectIsNotNode(Model model, String s, String p, String o) {

        Property property = model.createProperty(p);
        model.createResource(s).addProperty(property, o);
    }

    /**
     * 例子： <周杰伦> <妻子> <昆凌>
     */
    public static void writeAnTripleWhenObjectIsNode(Model model, String s, String p, String o) {
        Resource resource = model.createResource(s);
        Property property = model.createProperty(p);
        RDFNode object = model.createResource(o);
        model.add(resource, property, object);
    }

}