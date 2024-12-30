package Hotel_mansgement;

import java.util.Objects;
import java.util.Scanner;
/**
 * 工具类，用于实现使用房间号查找，使用名字查找，使用身份证查找三个功能
 * */
public class ArrayUtils 
{
    /**
     * 通过房间号查找下标
     */
    public static int findIndexByRoomId(int RoomId)
    {
        //用来返回房间号，若房间没人则返回初始值：-1
        int index =-1;
        //遍历数组直到当前下标里的房间号与传入的房间号相等时，打破循环输出此时的i即为下标
        for (int i=0;i<Customer.count;i++)
        {
            if (RoomId == Customer.arrCustomer[i].getRoomId())
            {
                index = i;
                break;
            }
        }
        return index;
    }
}
