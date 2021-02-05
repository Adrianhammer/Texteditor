/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package editor;

import java.util.LinkedList;
import java.util.ListIterator;

/**
 *
 * @author evenal
 */
public class LinkedListTest {

    public static void main(String[] args) {
        LinkedList<String> list = new LinkedList();
        list.add("AAA");
        list.add("BBB");
        list.add("CCC");
        list.add("DDD");
        list.add("EEE");
        list.add("FFF");
        list.add("GGG");
        list.add("HHH");
        list.add("III");
        list.add("JJJ");
        list.add("KKK");
        list.add("LLL");
        list.add("MMM");
        list.add("NNN");
        list.add("OOO");
        list.add("PPP");
        list.add("QQQ");
        list.add("RRR");
        list.add("SSS");
        print(list);

        ListIterator<String> cursor = list.listIterator(5);
        cursor.add("XXX");
        print(list);

        System.out.println(cursor.previous());
    }

    public static void print(LinkedList<String> list) {
        for (String s : list)
            System.out.format("%s", s);
        System.out.println();
    }
}
