import org.json.simple.*;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException, ParseException {

        JSONParser parser = new JSONParser();

        try {

            JSONObject jsonObject = (JSONObject) parser.parse(new FileReader("response.json"));
            JSONArray bidList = (JSONArray) jsonObject.get("bids");

            double[] amntPrice = new double[bidList.size()];

            Scanner in  = new Scanner(System.in);
            String pair = "";
            System.out.println("Enter the trading pair below: eg. BTC/USDT");

            pair = in.nextLine();
            //System.out.println(pair);



            Bid[] bids = new Bid[bidList.size()];

            double currentPrice = 0;


            for (int i = 0; i < bidList.size(); i++) {
                Object o = bidList.get(i);
                String[] s = o.toString().
                        replaceAll("\"", "").
                        replaceAll("]", "").
                        replaceAll("\\[", "").
                        split(",");
                double amnt = Double.parseDouble(s[1]);
                double prc = Double.parseDouble(s[0]);

                if(i==0){
                    currentPrice = prc;
                }

                bids[i] = new Bid(prc, amnt);
                //System.out.println(bids[i].getPrice()); //testing

                amntPrice[i] = amnt * prc;
                //System.out.println(bids[i].getAmount());
                //System.out.println(amntPrice[i]);
            }



            System.out.print("Current USD price of " + pair + " trading pair set to: $");
            System.out.println(currentPrice);

            double sum = 0;
            int count = 0;


            for (int i=0; i < amntPrice.length ; i++){
                if(sum<25000){
                    sum = sum + amntPrice[i];
                    count++;
                }
                else{
                    break;
                }

            }

            //System.out.println("SUM: $" + sum);

            double perc = ((currentPrice / bids[count].getPrice()) - 1) * 100;

            System.out.println("Price will decrease by " + String.format("%.2f", perc) + "% for every $25k");
            System.out.println();
            System.out.println("Price percentage decrease for every $25k listed below:");

            int spent = 25;

            for(int i = 1;i<=5;i++){

                System.out.println("$" + spent + "k - " + String.format("%.2f", perc*i) + "%");
                spent += 25;
            }


        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }


    }
}
