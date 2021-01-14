import java.util.*;

public class Radix{

  public static void main(String[] args){
    System.out.println(nth(123,1));
    System.out.println(nth(-123,1));
    System.out.println(nth(123,2));
    System.out.println(nth(-123,2));
    System.out.println(nth(-123,8));

    System.out.println(length(0));
    System.out.println(length(15));
    System.out.println(length(-10));
    System.out.println(length(5112));

    SortableLinkedList original = new SortableLinkedList();
    for (int i = 10; i>=1; i--) {
      original.add(Integer.valueOf(i));
    }
    SortableLinkedList[] buckets = new SortableLinkedList[3];
    for(int i=0; i<buckets.length; i++){
      int st = (i+1)*10;
      SortableLinkedList m = new SortableLinkedList();
      for (int k=st+10; k>=st+1; k--) {
        m.add(Integer.valueOf(k));
      }
      buckets[i] = m;
    }
    merge(original, buckets);
    System.out.println(original.toString());
    radixSortSimple(original);
    System.out.println(original.toString());
  }

  //get nth digit of an int, where 0 is the ones column, 1 is the tens column etc.
  public static int nth(int n, int col){
    String s = Integer.toString(Math.abs(n));
    if(col >= s.length()){
      return 0;
    }
    return Character.getNumericValue(s.charAt(s.length()-col-1));
  }

  //return the number of digits in n.
  public static int length(int n){
    String s = Integer.toString(Math.abs(n));
    return s.length();
  }

  public static void merge( SortableLinkedList original, SortableLinkedList[] buckets) {
    for(SortableLinkedList m : buckets){
      for(int i=0; i<m.size(); i++){
        original.add(m.getNthNode(i).getData());
      }
    }
  }

  private static void onepath(int[] array, int col) {
    int[] temp = new int[array.length];
    int k = 0;
    for (int i = 0; i < 10; i++) {
      for (int a : array) {
        if (nth(a, col) == i){
          temp[k] = a;
          k++;
        }
      }
    }
    for (int i = 0; i < array.length; i++) {
      array[i] = temp[i];
    }
  }

  public static void radixSortSimple(SortableLinkedList data) {
    int maxNum = 0;
    for (int i = 0; i < data.size(); i++) {
      if (data.get(i) > maxNum) {
        maxNum = data.get(i);
      }
    }
    int path = Integer.toString(maxNum).length();
    int[] temp = new int[data.size()];
    for (int i = 0; i<data.size(); i++) {
      temp[i] = data.getNthNode(i).getData().intValue();
    }
    for (int i = 0; i <= path; i++) {
      onepath(temp, i);
    }
    for (int i = 0; i < data.size(); i++) {
      data.set(i, Integer.valueOf(temp[i]));
    }
  }

  public static void radixSort(SortableLinkedList data) {
    SortableLinkedList ng = new SortableLinkedList();
    SortableLinkedList ps = new SortableLinkedList();
    for (int i = 0; i < data.size(); i++) {
      if (data.getNthNode(i).getData().intValue() < 0) {
        ng.add(Integer.valueOf(data.getNthNode(i).getData().intValue()*-1));
      } else {
        ps.add(data.getNthNode(i).getData());
      }
      radixSortSimple(ng);
      radixSortSimple(ps);
      int ns = ng.size();
      for (int k = ns-1; k >= 0; k--) {
        data.set(k, Integer.valueOf(ng.getNthNode(k).getData().intValue()*-1));
      }
      for (int j = 0; j < ps.size(); j++) {
        data.set(ns+j, ps.getNthNode(j).getData());
      }
    }
  }
}
