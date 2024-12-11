package Hotel_mansgement;

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
    public static void modify()
    {
    }
    public static void delete()
    {
    }
    public static void view()
    {

    }

}
