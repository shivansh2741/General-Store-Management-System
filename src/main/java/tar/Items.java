/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tar;

/**
 *
 * @author tanzeem
 */
public class Items {

    public String getNameP() {
        return nameP;
    }

    public void setNameP(String nameP) {
        this.nameP = nameP;
    }

    public String getBatch() {
        return batch;
    }

    public void setBatch(String batch) {
        this.batch = batch;
    }

    public double getQty() {
        return qty;
    }

    public void setQty(double qty) {
        this.qty = qty;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public Items(String nameP, String batch, double qty, String amount) {
        this.nameP = nameP;
        this.batch = batch;
        this.qty = qty;
        this.amount = amount;
    }

  
String nameP;
String batch;
double qty;
String amount;
    
}
