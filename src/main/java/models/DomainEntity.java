package models;

import jakarta.persistence.*;

@Entity
@Access(AccessType.PROPERTY)
public class DomainEntity {

    //Constructor
    public DomainEntity(){
        super();
    }
    private int id;
    private int version;

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    public int getId() {
        return id;
    }


    public void setId(int id) {
        this.id = id;
    }

   @Version
    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    @Override
    public int hashCode(){
        return this.id;
    }

    @Override
    public boolean equals(Object object){
        boolean result;
        if(object == null)
            return false;

        if(this.getClass() != object.getClass())
            return false;


        if(this==object)
            result = true;
        else if(object instanceof Integer obj)
            result = obj == this.id;
        else
            result = (this.getId() == ((DomainEntity) object).getId());

        return result;
    }

    @Override
    public String toString(){

        return this.getClass().getName() +
                "{id=" +
                this.id +
                " version=" +
                this.getVersion() +
                "}";
    }
}
