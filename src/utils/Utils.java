package utils;

import java.util.List;

public interface Utils {

    public static <T> String doStringBuilder(List<T> list){
        StringBuilder stringBuilder = new StringBuilder();

        for(T item : list){
            stringBuilder.append(item);
            stringBuilder.append(",");
        }

        return stringBuilder.toString();
    }

}
