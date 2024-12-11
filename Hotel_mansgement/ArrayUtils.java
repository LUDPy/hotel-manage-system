package Hotel_mansgement;

import java.util.Objects;
import java.util.Scanner;

public class ArrayUtils {
    public static int findCustomerByRoomId()
    {
        int iForReturn=0;
        Scanner scanner=new Scanner(System.in);
        System.out.println("房间号");
        //判断是否存在
        boolean flag=true;
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
                System.out.println("请输入正确的房间号");
        }
       return iForReturn;
    }
    public static void findCustomerByName()
    {

        Scanner scanner=new Scanner(System.in);
        System.out.println("请输入姓名");
        //判断是否存在
        boolean flag=true;
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
            System.out.println("请输入正确的姓名");
        }
    }
    public static void findCustomerByIdNumber()
    {
        Scanner scanner=new Scanner(System.in);
        System.out.println("请输入身份证号");
        //判断是否存在
        boolean flag=true;
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
            System.out.println("请输入正确的身份证号");

        }
    }
}
