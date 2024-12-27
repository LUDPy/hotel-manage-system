package Hotel_mansgement;

import java.util.Objects;
import java.util.Scanner;
/*
room 为房间 1-50 true 表示有人 false 表示无人
arrCustomer[]  存放用户信息 最大50个
cout 顾客数量
* */
public class Customer {
    public static  boolean    []room=new boolean[51];
    public static Customer[] arrCustomer=new Customer[50];
    public static int cout=0;
    private Integer roomId;
    private String name;
    private String gender;
    private String idNumber;
    private  Integer age;

    public Customer()
    {
    }
    /*有参构造*/
    public Customer(int roomId,String name,int age,String gender,String idNumber)
    {
        
        setRoomId(roomId);
        this.name=name;
        setAge(age);
        setGender(gender);
        setIdNumber(idNumber);
    }
    /*房间号码为1-50 若输入错误或房间已有顾客重新输入 直达输入合法房间号*/
    public void setRoomId(int roomId) {
        
        Scanner sc=new Scanner(System.in);
        while(roomId<1||roomId>50)
        {
                System.out.println("房间号输入错误，请重新输入(1-50):");
                roomId=sc.nextInt();
        }
        while(room[roomId]==true)
        {
            System.out.println("该房间已被使用,请重新输入:");
            roomId=sc.nextInt();
        }
        this.roomId = roomId;
        room[roomId]=true;
    }

    public void setName(String name) {
        this.name = name;
    }
    /*输入合法性别   若不合法重新输入*/
    public void setGender(String gender) {
        Scanner sc=new Scanner(System.in);
        this.gender = gender;
        boolean flag=!gender.equals("男")&&!gender.equals("女");
        while(flag)
        {
            System.out.println("输入错误，请重新输入性别(男/女)");
            gender=sc.next();
            flag=!gender.equals("男")&&!gender.equals("女");
        }
        this.gender = gender;
    }
    public Integer getAge() {
        return age;
    }
    /*输入合法年龄   若不合法重新输入*/
    public void setAge(int age) {
        Scanner sc=new Scanner(System.in);
        boolean flag=age<0||age>=150;
        while(flag)
        {
            System.out.println("输入错误，请重新输入:");
            age=sc.nextInt();
            flag=age<0||age>=150;
        }
        this.age = age;
    }

    /*输入18位身份证号码   若不合法重新输入*/
    public void setIdNumber(String idNumber) {
        Scanner sc=new Scanner(System.in);
        boolean flag=idNumber.length()!=1;
        while(idNumber.length()!=1)
        {
            System.out.println("输入错误，请重新输入身份证号码");
            idNumber=sc.next();
            flag=idNumber.length()!=1;
        }
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
        return "房间号:"+roomId+"    "+"顾客姓名:"+name+"    "+"顾客年龄:" + age+"    "+"顾客性别:"+gender+"    "+"顾客身份证号:"+idNumber;
    }
    /*重载等于号*/
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
