import java.util.ArrayList;

public class Tax extends Property {
    String[] locations = {"City","Large Town","Small Town","Village","Countryside"};
    int[] rates = { 100, 80, 60, 50, 25 };
    double accumTax;
    public static ArrayList<Tax> taxlist = new ArrayList<Tax>();
    
    public Tax(){
    }
    
    public Tax(String owners, String address, String eircode, int value, String locationCategory, char ppr) {
        super(owners,address,eircode,value,locationCategory,ppr);
    }

    public double getMarketValueTax () {
        return getMarketValueTax(value);
    }
    
    private double getMarketValueTax (int val) {
        double taxrate;

        if(val < 150000) {
            taxrate = 0;
        }
        else if(val < 400000 ) {
            taxrate = .01;
        }
        else if(val < 650000) {
            taxrate = .02;
        }
        else {
            taxrate = .04;
        }
        return taxrate;
    }

    public double getLocationTax() {
        return getLocationTax(locationCategory);
    }
    
    private double getLocationTax(String locationCat) {
        int locationTax=0;
        for(int i=0;i<locations.length;i++) {
            if(locations[i].contentEquals( locationCat)) {
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
    
    public double getTaxForProperty(Property property){
        double tax = 100;
        double valueTax = getMarketValueTax(property.getValue());
        double locationTax = getLocationTax(property.locationCategory());
        //An additional flat charge of €100 if the property is not the principle private residence of the owner.
        //Apply a 7% penalty, compounded for each year that a property tax is unpaid
        return (tax + valueTax + locationTax);
    }
}