package Hotel_mansgement;
import java.util.Scanner;

/**
 *视图界面类
 *
 */
public class CustomerView {
    public static Scanner sc = new Scanner(System.in);
    public static void start() {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("---------------------商务酒店管理系统---------------------");
            System.out.println("1.添加顾客信息");
            System.out.println("2.修改顾客信息");
            System.out.println("3.删除顾客信息");
            System.out.println("4.查看顾客信息");
            System.out.println("5.退出系统");
            System.out.println("请选择(1-5):");
            int choice = sc.nextInt();
            switch (choice) {
                case 1:
                    addCustomer();
                    break;
                case 2:
                    updateCustomer();
                    break;
                case 3:
                    deleteCustomer();
                    break;
                case 4:
                    view();
                    break;
                case 5:
                    System.out.println("酒店期待你的下次到来!");
                    System.exit(0);
                default:
                    System.out.println("输入错误，请重新输入");
            }
        }
    }

    /*添加顾客*/
    public static void addCustomer() {
        Customer.arrCustomer[Customer.count] = new Customer();
        if (Customer.count >= Customer.arrCustomer.length) {
            System.out.println("当前已无空房间");
            return;
        }
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入房间号(1-50):");
        int roomId = sc.nextInt();
        Customer.arrCustomer[Customer.count].setRoomId(roomId);
        System.out.println("请输入顾客姓名:");
        String name = sc.next();
        Customer.arrCustomer[Customer.count].setName(name);
        System.out.println("请输入顾客年龄:");
        int age = sc.nextInt();
        Customer.arrCustomer[Customer.count].setAge(age);
        System.out.println("请输入性别(男/女):");
        String gender = sc.next();
        Customer.arrCustomer[Customer.count].setGender(gender);
        System.out.println("请输入顾客身份证号码:");
        String idNumber = sc.next();
        Customer.arrCustomer[Customer.count].setIdNumber(idNumber);
        Customer.count++;
        System.out.println("添加顾客信息成功!!");
    }
//修改顾客信息
    public static void updateCustomer() {
        System.out.print("请输入房间号");
        int roomId= sc.nextInt();
        int index=ArrayUtils.findIndexByRoomId(roomId);//获取到要修改顾客的下标
        if(index==-1)//根据输入的房间号找不到这个人
        {
            System.out.println("该房间未入住!");
        }
        else {
            System.out.println("请输入修改后的顾客姓名");
            String name = sc.next();
            System.out.println("请输入修改后的顾客年龄");
            int age = sc.nextInt();
            System.out.println("请输入修改后的顾客性别");
            String gender = sc.next();
            System.out.println("请输入修改后的顾客身份证号");
            String idNumber = sc.next();
            Customer.arrCustomer[index].setAge(age);
            Customer.arrCustomer[index].setName(name);
            Customer.arrCustomer[index].setIdNumber(idNumber);
            Customer.arrCustomer[index].setGender(gender);
            System.out.println("修改成功");
        }
    }

    public static void deleteCustomer() {

        System.out.println("顾客退房");

        int num =1 ;
//        ArrayUtils.findIndexByRoomId();
        if (num == -1) {
            System.out.println("该房间未入住!");

        } else {
            //源数组中位置在 srcPos 到 srcPos+length-1 之间的组件被分别复制到目标数组中的 destPos 到 destPos+length-1 位置。
            System.arraycopy(Customer.arrCustomer, num + 1, Customer.arrCustomer, num, Customer.count - num);
            Customer.arrCustomer[Customer.count] = null;
            Customer.count--;
            Customer.room[num + 1] = false;
            System.out.println("退房成功,欢迎下次光临");
        }
    }

    /*顾客查询系统，其中主要功能代码集成在工具类中实现*/
    public static void view() {

        System.out.println("1.通过房间号查询：");
        System.out.println("2.通过姓名查询：");
        System.out.println("3.通过身份证号查询：");
        System.out.println("4.全局查询：");

        Scanner scanner = new Scanner(System.in);
        int choice = scanner.nextInt();
        switch (choice)//通过switch语句来判断要求
        {
//            case 1:
//                System.out.print("请输入");
//                ArrayUtils.findCustomerByRoomId();//调用工具类完成
//                break;

            case 2:
                ArrayUtils.findCustomerByName();//调用工具类完成
                break;

            case 3:
                ArrayUtils.findCustomerByIdNumber();//调用工具类完成
                break;
            case 4:
                for (int i = 0; i < Customer.count; i++) {
                    System.out.println(Customer.arrCustomer[i].toString());
                }
                break;
            default:
                System.out.println("！请输入正确的信息！");

        }

    }
}
