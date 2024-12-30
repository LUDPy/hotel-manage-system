package Hotel_mansgement;
import java.util.Scanner;

/**
 *视图界面类
 *该类包含5个方法，分别是启动界面，以及对顾客信息增删改查操作
 *
 * @author 马毓聪 卢东鹏 刘彬宏 马浩然
 * @version 2.0
 * @since
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

    /**
     * 添加顾客信息
     *
     */
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

    /**
     * 修改顾客信息
     *
     */
    public static void updateCustomer() {
        System.out.print("请输入房间号");
        int roomId= sc.nextInt();
        //获取到要修改顾客的下标
        int index=ArrayUtils.findIndexByRoomId(roomId);
        //根据输入的房间号找不到这个人
        if(index==-1)
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

    /**
     *删除客户信息
     *通过arraycopy函数，对存有顾客信息的数组进行修改
     */
    public static void deleteCustomer() {
        System.out.println("顾客退房,请输入房间号");
        //输入的房间号
        int roomId = sc.nextInt();
        //通过findIndexByRoomId()查找房间是否入住，入住返回的是顾客在arrCustomer[]中的位置,否则返回-1
        int index =ArrayUtils.findIndexByRoomId(roomId);

        if (index == -1) {
            System.out.println("房间号错误!");

        } else {
            //源数组中位置在 srcPos 到 srcPos+length-1 之间的组件被分别复制到目标数组中的 destPos 到 destPos+length-1 位置。
            System.arraycopy(Customer.arrCustomer, index + 1, Customer.arrCustomer, index, Customer.count - index);
            //将最后一个客户重复的信息删掉
            Customer.arrCustomer[Customer.count] = null;
            Customer.count--;
            Customer.room[index + 1] = false;
            System.out.println("退房成功,欢迎下次光临");
        }
    }

    /**
     * 查询顾客信息
     *
     */
    public static void view() 
    {
        
        for (int i = 0; i < Customer.count; i++) {
            int index=ArrayUtils.findIndexByRoomId(i);
            if(index!=-1){
                System.out.println(Customer.arrCustomer[i].toString());
            }
            else
                System.out.println("尊贵的顾客你好，本店目前所有房间都空着，没有顾客信息");
            
        }
    }
}
