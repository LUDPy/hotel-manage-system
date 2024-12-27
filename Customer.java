package Hotel_mansgement;

import java.util.Objects;
import java.util.Scanner;
/**
*room 为房间 1-50 true 表示有人 false 表示无人
*arrCustomer[]  存放用户信息 最大50个
*count 顾客数量
**/
public class Customer {
    public static  boolean    []room=new boolean[51];
    public static Customer[] arrCustomer=new Customer[50];
    public static int count =0;
    private Integer roomId;
    private String name;
    private String gender;
    private String idNumber;
    private  Integer age;

    public Customer()
    {
    }

    /**
     * 有参构造
     *
     * @param roomId 房间号 从1 ~ 50
     * @param name 姓名 必须是字符串
     * @param age 年龄 必须大于 0 小于等于 150
     * @param gender 性别 必须是 男 或 女
     * @param idNumber 身份证号 必须是18位
     */
    public Customer(int roomId,String name,int age,String gender,String idNumber)
    {
        
        setRoomId(roomId);
        this.name=name;
        setAge(age);
        setGender(gender);
        setIdNumber(idNumber);
    }


    /**
     * 判断房间号是否合法
     * 房间号码为1-50 若输入错误或房间已有顾客重新输入 直达输入合法房间号
     *
     * @param roomId 房间号 从1 ~ 50
     */
    public  void setRoomId(int roomId) {
        
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

    /**
     * 判断姓名是否合法
     * 姓名必须是字符串
     *
     * @param name 姓名 必须是字符串
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 判断性别是否合法
     * 性别只能是男或者女
     *
     * @param gender 性别 必须是 男 或 女
     */
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

    /**
     * 返回年龄
     * @return 当前用户的年龄
     */
    public Integer getAge() {
        return age;
    }

    /**
     * 判断年龄是否合法
     *
     * @param age 年龄 必须大于 0 小于等于 150
     */
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

    /**
     * 判断身份证号是否合法
     *
     * @param idNumber 身份证号 必须是18位
     */
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

    /**
     * 返回房间号
     *
     * @return 房间号
     */
    public int getRoomId() {
        return roomId;
    }

    /**
     * 返回用户姓名
     *
     * @return 用户姓名
     */
    public String getName() {
        return name;
    }

    /**
     * 返回用户性别
     *
     * @return 用户性别
     */
    public String getGender() {
        return gender;
    }

    /**
     * 返回用户身份证号
     * @return 用户身份证号
     */
    public String getIdNumber() {
        return idNumber;
    }

    /**
     * 输出用户信息
     *
     * @return 依次输出当前用户的 房间号 顾客姓名 顾客年龄 顾客性别 顾客身份证号
     */
    @Override
    public String toString() {
        return "房间号:"+roomId+"    "+"顾客姓名:"+name+"    "+"顾客年龄:" + age+"    "+"顾客性别:"+gender+"    "+"顾客身份证号:"+idNumber;
    }

    /**
     * 重载  =
     * 用于比较两个对象是否“逻辑相等”
     * @param o
     * @return
     */
    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return Objects.equals(roomId, customer.roomId) && Objects.equals(age, customer.age) && Objects.equals(name, customer.name) && Objects.equals(gender, customer.gender) && Objects.equals(idNumber, customer.idNumber);
    }

    /**
     * 比较两个对象的哈希值是否相等
     * @return
     */
    @Override
    public int hashCode() {
        return Objects.hash(roomId, name,age, gender, idNumber);
    }
}
