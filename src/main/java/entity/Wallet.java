package entity;

import model.Symbol;

import java.util.ArrayList;
import java.util.List;

public class Wallet {

     List<String> hystory;

     int currentState ;

     String currency;

     public Wallet(List<String> hystory, int currentState, String currency) {
          this.hystory = new ArrayList<>();
          this.currentState = currentState;
          this.currency = currency;
     }

     public void exchange(String Currency){


     }
     public void add(){

     }
     public void cashIssue(){

     }
     public void getHystory(){

     }

}
