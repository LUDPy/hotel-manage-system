package Hotel_mansgement;

import java.util.Objects;

public class Customer {
    public static final int MAX=50;
    public static Customer[] arrCustomer=new Customer[MAX];
    public static int index=0;
    private Integer roomId;
    private String name;
    private String gender;
    private String idNumber;
    public Customer()
    {
    }
    public Customer(int roomId,String name,String gender,String idNumber)
    {
        this.roomId=new Integer(roomId);
        this.name=name;
        this.gender=gender;
        this.idNumber=idNumber;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    public int getRoomId() {
        return roomId;
    }

    public String getName() {
        return name;
    }

    public String getGender() {
        return gender;
    }

    public String getIdNumber() {
        return idNumber;
    }

    @Override
    public String toString() {
        return "房间号:"+roomId+"\t"+"顾客姓名:"+name+"\t"+"性别:"+gender+"\t"+"身份证号码:"+idNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return Objects.equals(roomId, customer.roomId) && Objects.equals(name, customer.name) && Objects.equals(gender, customer.gender) && Objects.equals(idNumber, customer.idNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(roomId, name, gender, idNumber);
    }
}
