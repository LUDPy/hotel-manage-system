package Hotel_mansgement;

import java.util.Objects;
import java.util.Scanner;
/*工具类，用于实现使用房间号查找，使用名字查找，使用身份证查找三个功能*/
public class ArrayUtils 
{
    /*通过房间号查找下标*/
    public static int findIndexByRoomId(int RoomId)
    {
        int index =-1;//用来返回房间号，若房间没人则返回初始值：-1
        for (int i=0;i<Customer.count;i++)//遍历数组直到当前下标里的房间号与传入的房间号相等时，打破循环输出此时的i即为下标
        {
            if (RoomId == Customer.arrCustomer[i].getRoomId())
            {
                index = i;
                break;
            }
        }
        return index;
    }
    /*通过顾客名查找*/
    public static void findCustomerByName()
    {

        Scanner scanner=new Scanner(System.in);
        System.out.println("请输入姓名");
        //判断是否存在
        boolean flag=true;//标志是否不存在此人
        String  name= scanner.next();
        for (int i = 0; i<Customer.count; i++)
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
    /*通过身份证号查找*/
    public static void findCustomerByIdNumber()
    {
        Scanner scanner=new Scanner(System.in);
        System.out.println("请输入身份证号");
        //判断是否存在
        boolean flag=true;//标志是否不存在此人
        String  number= scanner.next();
        for (int i = 0; i<Customer.count; i++)
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
