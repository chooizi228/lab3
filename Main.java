import java.io.PrintStream;
import java.util.Scanner;
public class Main {
    public static Scanner in = new Scanner(System.in);
    public static PrintStream out = System.out;
    public static void main(String[] args) {
        // 1. Создание и заполнение массива
            // Ввод размерности многомерного массива
        out.print("Введите X: ");
        int x = in.nextInt();
        out.print("Введите Y: ");
        int y = in.nextInt();
        out.print("Введите Z: ");
        int z = in.nextInt();
        if(x > 0 && y > 0 && z > 0){
            // Создание многомерного массива
            int [][][] array = new int[x][y][z];
            out.print("Введите данные для многомерного массива: ");
            // Заполнение массива
            for(int i = 0; i < x; i++){
                for(int j = 0; j < y; j++){
                    for(int k = 0; k < z; k++){
                        array[i][j][k] = in.nextInt();
                    }
                }
            }
            out.println();
        // 2. Сортировка слоев
            int [] sum_layer = new int[x];
            int [] even_count = new int[x];
            for(int i = 0; i < x; i++){
                for(int j = 0; j < y; j++){
                    for(int k = 0; k < z; k++){
                        sum_layer[i] += array[i][j][k];
                        if(Math.abs(array[i][j][k]) % 2 == 0){
                            even_count[i] += 1;
                        }
                    }
                }
            }
            // Пузырковая сортировка слоев по координатам Y, Z
            for(int i = 1; i<x; i++){
                for(int j = 0; j<x-i; j++){
                    // Сравниваем суммы в слое
                    if(sum_layer[j] > sum_layer[j+1]){
                        int temp = sum_layer[j];
                        int temp1 = even_count[j];
                        int[][] temp_arr = array[j];
                        sum_layer[j] = sum_layer[j+1];
                        even_count[j] = even_count[j+1];
                        array[j] = array[j+1];
                        sum_layer[j+1] = temp;
                        even_count[j+1] = temp1;
                        array[j+1] = temp_arr;
                        // Если суммы равны рассматриваем количество четных чисел
                    } else if (sum_layer[j] == sum_layer[j+1]) {
                        if(even_count[j] > even_count[j+1]){
                            int temp = sum_layer[j];
                            int temp1 = even_count[j];
                            int[][] temp_arr = array[j];
                            sum_layer[j] = sum_layer[j+1];
                            even_count[j] = even_count[j+1];
                            array[j] = array[j+1];
                            sum_layer[j+1] = temp;
                            even_count[j+1] = temp1;
                            array[j+1] = temp_arr;
                        }
                    }
                }
            }
        // 3. Общая сумма элементов
            int summa = 0;
            for(int i = 0; i < x; i++){
                for(int j = 0; j < y; j++){
                    for(int k = 0; k < z; k++){
                        // Суммируем каждый элемент
                        summa += array[i][j][k];
                    }
                }
            }

            out.println("Общая сумма элементов: " + summa + "\n");
        // 4. Вывод элементов массива в виде срезов по оси Z
            int counter = 1;
            // Начинаем рассматривать элементы по индексам z
            for(int k = 0; k < z; k++){
                for(int j = 0; j < y; j++){
                    for(int i = 0; i < x; i++){
                        out.printf(array[i][j][k] + " ");
                    }
                    out.println();
                }
                out.println("Слой " + counter);
                counter +=1;
                out.println();
            }
        // 5. Обновленный массив с положительными числами
            out.println("Отсортированный массив без отрицательных чисел");
            out.print("[");
            for(int i = 0; i < x; i++){
                out.print("[");
                for(int j = 0; j < y; j++){
                    out.print("[");
                    for(int k = 0; k < z; k++){
                        // Заменяем отрицательное число на модуль
                        if(array[i][j][k] < 0){
                            array[i][j][k] = Math.abs(array[i][j][k]);
                        }
                        // Вывод массива (если число последнее, то не пишем запятую после числа)
                        if(k != z-1){
                            out.print(array[i][j][k] + ", ");
                        }else{
                            out.print(array[i][j][k]);
                        }
                    }
                    // Вывод массива (если число последнее, то не пишем запятую после скобки)
                    if(j != y-1){
                        out.print("], ");
                    }else{
                        out.print("]");
                    }
                }
                // Вывод массива (если число последнее, то не пишем запятую после скобки)
                if(i != x-1){
                    out.print("], ");
                }else{
                    out.print("]");
                }
            }
            out.print("]");
        } else{
            out.print("Указано некорректное значение размерности массива");
        }
    }
}