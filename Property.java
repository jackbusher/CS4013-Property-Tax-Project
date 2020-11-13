import java.util.ArrayList;

public class Property{
    public String owners;
    public String address;
    public String eircode;
    public int value;
    public String locationCategory;
    public char ppr; //Y or N
    public ArrayList<Tax> taxlist = new ArrayList<Tax>();
public Property(String owners, String address, String eircode, int value, String locationCategory, char ppr){
    this.owners = owners;
    this.address = address;
    this.eircode = eircode;
    this.value = value;
    this.locationCategory = locationCategory;
    this.ppr = ppr;
}

public void setOwners(String owners) {
this.owners = owners;
}
public void setAddress(String address){
    this.address = address;
}
public void setEircode(String eircode){
    this.eircode = eircode;
}
public void setValue(int value){
    this.value = value;
}
public void setLocationCategory(String locationCategory){
    this.locationCategory = locationCategory;
}
public void setPPR(char ppr){
    this.ppr = ppr;
}
public char getPPR(){
    return this.ppr;
}
public String getOwners(){
    return this.owners;
}
public String getAddress(){
    return this.address;
}
public String eircode(){
    return this.eircode;
}
public String locationCategory(){
    return this.locationCategory;
}
public int value(){
    return this.value;
}



}
            
    
    
