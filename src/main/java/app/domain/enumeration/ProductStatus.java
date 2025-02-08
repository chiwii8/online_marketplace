package app.domain.enumeration;

public enum ProductStatus {
    STOCK, SOLD_OUT;

    @Override
    public String toString(){
        String parseName = this.name().replace("_"," ");
        parseName = parseName.charAt(0) + parseName.substring(1);

        return parseName;
    }

}
