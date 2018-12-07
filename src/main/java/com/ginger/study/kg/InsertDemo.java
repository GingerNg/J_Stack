package com.ginger.study.kg;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import org.apache.jena.rdf.model.*;
import org.apache.jena.ontology.OntClass;
import org.apache.jena.ontology.OntModel;
import org.apache.jena.ontology.OntModelSpec;
import org.apache.jena.ontology.OntProperty;
import org.apache.jena.ontology.SomeValuesFromRestriction;

public class InsertDemo {


    public static void main(String args[]) throws FileNotFoundException{

        final OntModel ontModel = ModelFactory.createOntologyModel(OntModelSpec.OWL_MEM);
        ontModel.read(new FileInputStream("f://go-minus.owl"), "");

        String exNs = "http://purl.obolibrary.org/obo/";

        //创建class
        OntClass ontclass = ontModel.createClass(exNs+"GO_0045019");

        //创建ontclass的父类
        OntClass ontclassDad1 = ontModel.createClass(exNs+"GO_0031327");


        OntProperty p1 = ontModel.createOntProperty(exNs+"RO_0002212");
        Resource r1 = ontModel.createResource(exNs+"GO_0006809");
        SomeValuesFromRestriction hr1 = ontModel.createSomeValuesFromRestriction("owl:", p1, r1);

        ontclass.addSuperClass(hr1);
        ontclass.addSuperClass(ontclassDad1);

        //创建标签
        ontclass.addLabel("negative regulation of nitric oxide biosynthetic process", "");

        ontModel.write(new FileOutputStream("f://go-minus1.owl"));

    }

}