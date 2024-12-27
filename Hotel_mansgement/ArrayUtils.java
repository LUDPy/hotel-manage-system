package Hotel_mansgement;

import java.util.Objects;
import java.util.Scanner;

public class ArrayUtils //工具类
{
    public static int findCustomerByRoomId()//通过房间号查找
    {
        int iForReturn=-1;//用来返回房间号，若房间没人则返回初始值：-1
        Scanner scanner=new Scanner(System.in);
        System.out.println("请输入房间号");
        //判断是否存在
        boolean flag=true;//标志房间是否没有人
        int roomId= scanner.nextInt();
        for (int i=0;i<Customer.index;i++)
        {
            if (roomId == Customer.arrCustomer[i].getRoomId())
            {
                flag=false;
                System.out.println(Customer.arrCustomer[i].toString());
                iForReturn= i;
            }

        }
        if(flag){
                System.out.println("查无此人");
        }
       return iForReturn;
    }
    public static void findCustomerByName()//通过顾客名查找
    {

        Scanner scanner=new Scanner(System.in);
        System.out.println("请输入姓名");
        //判断是否存在
        boolean flag=true;//标志是否不存在此人
        String  name= scanner.next();
        for (int i=0;i<Customer.index;i++)
        {
            if(Objects.equals(name,Customer.arrCustomer[i].getName()))
            {
                flag=false;
                System.out.println(Customer.arrCustomer[i].toString());

            }

        }
        if(flag){
            System.out.println("查无此人");
        }
    }
    public static void findCustomerByIdNumber()//通过身份证号码查询
    {
        Scanner scanner=new Scanner(System.in);
        System.out.println("请输入身份证号");
        //判断是否存在
        boolean flag=true;//标志是否不存在此人
        String  number= scanner.next();
        for (int i=0;i<Customer.index;i++)
        {
            if(Objects.equals(number,Customer.arrCustomer[i].getIdNumber()))
            {
                flag=false;
                System.out.println(Customer.arrCustomer[i].toString());
            }

        }
        if(flag){
            System.out.println("查无此人");

        }
    }
}
