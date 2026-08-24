package jiekoulianxi;

public class Test {
    public static void main(String[] args) {
        PingPangSporter pps=new PingPangSporter("liushiwen",23);
        System.out.println(pps.getName()+","+pps.getAge());
        pps.study();
        pps.speakEnglish();
    }
}
