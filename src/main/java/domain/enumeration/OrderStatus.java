package domain.enumeration;

public enum OrderStatus {
    PENDING,ACCEPT, SEND, RECEIVE;

    @Override
    public String toString(){
        String parseName = this.name();
        parseName = parseName.charAt(0) + parseName.substring(1);

        return parseName;
    }
}


