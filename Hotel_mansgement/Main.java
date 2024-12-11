package Hotel_mansgement;

import javax.swing.text.View;
import java.util.Scanner;

public class Main {
    static public void main(String[] args) {

        System.out.println("顾客你好,本店所有房间都为空");
        Scanner sc=new Scanner(System.in);
        while(true)
        {
            System.out.println("---------------------商务酒店管理系统---------------------------------");
            System.out.println("1.添加顾客信息");
            System.out.println("2.修改顾客信息");
            System.out.println("3.删除顾客信息");
            System.out.println("4.查看顾客信息");
            System.out.println("5.退出");
            System.out.println("请选择(1-5):");
            int choice=sc.nextInt();
            switch (choice)
            {
                case 1:
                    add();
                    break;
                case 2:
                    modify();
                    break;
                case 3:
                    delete();
                    break;
                case 4:
                    view();
                    break;
                case 5:
                    System.exit(0);
                default:
                    System.out.println("输入错误，请重新输入");
            }
        }
    }
    public static void add()
    {
        if(Customer.index>=Customer.arrCustomer.length)
        {
            System.out.println("无空房间");
            return;
        }
        Scanner sc=new Scanner(System.in);
        System.out.println("房间号为(1-50):");
        int roomId=sc.nextInt();
        System.out.println("请输入姓名:");
        String name=sc.next();
        System.out.println("请输入性别(男/女):");
        String gender=sc.next();
        System.out.println("请输入身份证号码:");
        String idNumber=sc.next();
        Customer.arrCustomer[Customer.index]=new Customer(roomId,name,gender,idNumber);
        System.out.println(Customer.arrCustomer[Customer.index].toString());
        Customer.index++;
        System.out.println("添加成功");
    }
    public static void modify() {
        //过房间号，查询到当前Customer顾客信息，所在对象数组中的索引，重新录入信息(需要添加一个工具类)ArrayUtils，
        // findCustomerByRoomId(数组，roomId,count),找到索引
        while(true)
        {
            Scanner sc=new Scanner(System.in);
            int index=1;//ArrayUtils.findCustomerByRoomId(Customer.arrCustomer);
            System.out.println("选择要修改的属性");
            System.out.println("1.房间号 2.个人信息 3.退出");
            int choice=sc.nextInt();
            switch (choice)
            {
                case 1:
                    Customer.room[index]=false;
                    System.out.println("请输入房间号");
                    int roomId=sc.nextInt();
                    Customer.arrCustomer[index].setRoomId(roomId);
                    break;
                case 2:
                    System.out.println("请输入姓名 身份证号 性别");
                    String name=sc.next();
                    String idNumber=sc.next();
                    String gender=sc.next();
                    Customer.arrCustomer[index].setName(name);
                    Customer.arrCustomer[index].setIdNumber(idNumber);
                    Customer.arrCustomer[index].setGender(gender);
                    break;
                case 3:
                    return;
                default:
                    System.out.println("输入错误，请重新输入");
            }
            System.out.println(Customer.arrCustomer[index].toString());
        }
    }
    public static void delete()
    {
    }
    public static int view()
    {
        int iForReturn=0;
        System.out.println("1.通过房间号查询：");
        System.out.println("2.通过姓名查询：");
        System.out.println("3.通过身份证号查询：");
        System.out.println("4.全局查询：");
        Scanner scanner=new Scanner(System.in);
        int choice=scanner.nextInt();
        switch (choice)
        {
            case 1:
                System.out.println("请输入房间号");
                int roomId= scanner.nextInt();
                for (int i=0;i<Customer.arrCustomer.length;i++)
                {
                    if(roomId==Customer.arrCustomer[i].getRoomId())
                    {
                        System.out.println(Customer.arrCustomer[i].toString());
                        iForReturn= i;
                        break;
                    }
                }
                break;

            case 2:
                System.out.println("请输入姓名");
                String  name= scanner.next();
                for (int i=0;i<Customer.arrCustomer.length;i++)
                {
                    if(name==Customer.arrCustomer[i].getName())
                    {
                        System.out.println(Customer.arrCustomer[i].toString());
                        iForReturn= i;
                        break;
                    }
                }
                break;

            case 3:
                System.out.println("请输入身份证号");
                String  number= scanner.next();
                for (int i=0;i<Customer.arrCustomer.length;i++)
                {
                    if(number==Customer.arrCustomer[i].getIdNumber())
                    {
                        System.out.println(Customer.arrCustomer[i].toString());
                        iForReturn= i;
                        break;
                    }
                }
                break;

            case 4:
                for (int i=0;i<Customer.arrCustomer.length;i++)
                {
                    System.out.println(Customer.arrCustomer[i].toString());
                    break;
                }
                break;

            default:
                System.out.println("输入错误，请重新输入");
        }
        return iForReturn;
    }
}




