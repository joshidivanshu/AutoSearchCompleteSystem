package org.example;

import java.util.List;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        AutoSearchComplete object = new AutoSearchComplete(4);
        object.insert("iphone");
        object.insert("iphone");
        object.insert("textile");
        object.insert("textile");
        object.insert("textile");
        List<Element> result = object.fetchRelatedKey("iph");
        for(Element e : result) {
            System.out.println(e.getWord() + " " + e.getFrequency());
        }
    }
}