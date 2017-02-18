package com.apps2life.mystudentsevaluator;

/**
 * Created by Eljeddi Oussema on 2/1/2017.
 */


public class Question {
    private String qst;
    private String op1;
    private String op2;
    private String op3;

    public Question(){

    }
    public void setQst(String qst){
        this.qst=qst;
    }
    public void setOp1(String op1){
        this.op1=op1;
    }
    public void setOp2(String op2){
        this.op2=op2;
    }
    public void setOp3(String op3){
        this.op3=op3;
    }
    public  String getQst(){
        return qst;
    }
    public String getOp1(){
        return op1;
    }
    public String getOp2(){
        return op2;
    }
    public  String getOp3(){
        return op3;
    }
    public String toString(){return qst;}

}
