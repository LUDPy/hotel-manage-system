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
        while(true)
        {
            Scanner sc=new Scanner(System.in);
            System.out.println("选择要修改的属性");
            System.out.println("1.房间号 2.个人信息 3.退出");
            int choice=sc.nextInt();
            if(choice==3)
            {
                return;
            }
            System.out.print("请输入要修改顾客的");
            int index=ArrayUtils.findCustomerByRoomId();
            if(index==-1)
            {
                return;
            }
            System.out.println("index值="+index);
            switch (choice)
            {
                case 1:
                    if(Customer.room[Customer.arrCustomer[index].getRoomId()])
                    {System.out.println("请输入新的房间号");
                        Customer.room[Customer.arrCustomer[index].getRoomId()]=false;
                    int roomId=sc.nextInt();
                    Customer.arrCustomer[index].setRoomId(roomId);
                    break;
                    } else if (!Customer.room[Customer.arrCustomer[index].getRoomId()]) {
                        break;
                    }

                case 2:
                    if(Customer.room[Customer.arrCustomer[index].getRoomId()])
                    {
                    System.out.println("请重新填写姓名 身份证号 性别");
                    String name=sc.next();
                    String idNumber=sc.next();
                    String gender=sc.next();
                    Customer.arrCustomer[index].setName(name);
                    Customer.arrCustomer[index].setIdNumber(idNumber);
                    Customer.arrCustomer[index].setGender(gender);

                    break;
                    } else if (!Customer.room[Customer.arrCustomer[index].getRoomId()]) {

                        break;
                    }
                default:
                    System.out.println("输入错误，请重新输入");
            }
            if(Customer.room[Customer.arrCustomer[index].getRoomId()])
            {System.out.println(Customer.arrCustomer[index].toString());}
        }
    }
    public static void delete()
    {

        System.out.println("顾客退房");

        int num = ArrayUtils.findCustomerByRoomId();
        if (Customer.index > 0 && Customer.index <51) {
            //源数组中位置在 srcPos 到 srcPos+length-1 之间的组件被分别复制到目标数组中的 destPos 到 destPos+length-1 位置。
            System.arraycopy(Customer.arrCustomer, num + 1, Customer.arrCustomer, num, Customer.index - num);
            Customer.arrCustomer[Customer.index] = null;
            Customer.index--;
            Customer.room[num + 1] = false;
            System.out.println("删除成功");
        }

        else{
            System.out.println("该房间未入住!");
        }
    }
    public static int view()
    {
        int iForReturn=0;
        System.out.println("1.通过房间号查询：");
        System.out.println("2.通过姓名查询：");
        System.out.println("3.通过身份证号查询：");
        System.out.println("4.全局查询：");
        System.out.println("5.退出");
        Scanner scanner=new Scanner(System.in);
        int choice=scanner.nextInt();
        switch (choice)
        {
            case 1:
                System.out.print("请输入");
                ArrayUtils.findCustomerByRoomId();
                break;

            case 2:
                ArrayUtils.findCustomerByName();
                break;

            case 3:
                ArrayUtils.findCustomerByIdNumber();
                break;
            case 4:
                for (int i=0;i<Customer.index;i++)
                {
                    System.out.println(Customer.arrCustomer[i].toString());
                }
                break;
            default:
                System.out.println("！请输入正确的信息！");

        }
        return iForReturn;
    }
}





