import java.lang.reflect.*;
import java.util.*;

public class ArrayGrowTest {

    public static void main(String[] args) {

        int[] a = {1, 2, 3};
        a = (int[]) goodArrayGrow(a);
        arrayPrint(a);

        String[] b = {"Tom", "Dick", "Harry"};
        b = (String[]) goodArrayGrow(b);
        arrayPrint(b);

        System.out.println("The following call will generate an exception.");
        b = (String[]) badArrayGrow(b);
    }

    /**
     * Этот метод пытается увеличить размер массива, создавая
     * новый массив и копируя туда все элементы старого.
     * @param a массив, подлежащий увеличению
     * @return увеличенный массив, содержащий все элементы a.
     * Возвращаемый массив имеет ти Object[],
     * который совпадает с типом массива a.
     */

    static Object[] badArrayGrow(Object[] a){
        int newLength = a.length*11/10+10;
        Object[] newArray = new Object[newLength];
        System.arraycopy(a, 0, newArray, 0, a.length);
        return newArray;
    }

    /**
     * Этот метод увеличивает размер массива, создавая новый
     * массив того же типа и копируя туда все имеющиеся элементы.
     * @param a массив, подлежащий увеличению. Может состоять из
     *          объектов или значений простых типов.
     *          return увеличенный массив, содержащий все элементы
     *          массива a.
     */

    static Object goodArrayGrow(Object a){
        Class cl = a.getClass();
        if (!cl.isArray()) return null;
        Class componentType = cl.getComponentType();
        int length = Array.getLength(a);
        int newLength = length*11/10+10;

        Object newArray = Array.newInstance(componentType, newLength);
        System.arraycopy(a, 0, newArray, 0, length);
        return newArray;
    }

    /**
     * Удобный метод вывода всех элементов массива
     * @param a Массив для вывода. Может состоять из
     *          объектов или значений простых типов.
     */

    static void arrayPrint(Object a){
        Class cl = a.getClass();
        if (!cl.isArray()) return;
        Class componentType = cl.getComponentType();
        int length = Array.getLength(a);
        System.out.print(componentType.getName() + "[" + length + "] = { ");
        for (int i = 0; i < Array.getLength(a); i++) {
            System.out.print(Array.get(a, i) + " ");
            System.out.println();
        }
    }
}
