import java.util.ArrayList;

public class Tax extends Property {
    String[] locations = {"City","Large Town","Small Town","Village","Countryside"};
    int[] rates = { 100, 80, 60, 50, 25 };
    double accumTax;
    public static ArrayList<Tax> taxlist = new ArrayList<Tax>();
        public Tax(String owners, String address, String eircode, int value, String locationCategory, char ppr) {
            super(owners,address,eircode,value,locationCategory,ppr);
        }
        
public double getMarketValueTax () {
        double taxrate;

        if(value < 150000) {
            taxrate = 0;
        }
        else if(value < 400000 ) {
            taxrate = .01;
        }
        else if(value < 650000) {
            taxrate = .02;
        }
        else {
            taxrate = .04;
        }
        return taxrate;
    }
    
    public double getLocationTax() {
        int locationTax=0;
        for(int i=0;i<locations.length;i++) {
            if(locations[i].contentEquals( locationCategory)) {
                locationTax = rates[i];
                break;
            }
        }
        return locationTax;
    }

    public double accumTax(boolean sub, double subbedValue) {
    	if(sub) {
    		return subbedValue;
    	}
        this.accumTax = 100;
        accumTax = (getLocationTax() + value*getMarketValueTax());
        if(getPPR() == 'N') {
            accumTax = accumTax + 100;
        }

        return accumTax;
    }
    
    public static void addTaxList(Tax taxProp){
    	taxlist.add(taxProp);
    }
    }

