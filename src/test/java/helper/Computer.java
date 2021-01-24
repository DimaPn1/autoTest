package helper;

import java.util.List;

public class Computer {
    
    private String manufacturerName;
    private int minPrice;
    private int maxPrice;
    private double diagonal;
    private double weight;

    public Computer() {
    }

    public String getManufacturerName() {
        return manufacturerName;
    }

    public void setManufacturerName(String manufacturerName) {
        this.manufacturerName = manufacturerName;
    }

    public int getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(int minPrice) {
        this.minPrice = minPrice;
    }

    public int getMaxPrice() {
        return maxPrice;
    }

    public void setMaxPrice(int maxPrice) {
        this.maxPrice = maxPrice;
    }

    public double getDiagonal() {
        return diagonal;
    }

    public void setDiagonal(double diagonal) {
        this.diagonal = diagonal;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public static Computer maxDiagonal(List<Computer> allComputer){

        Computer result = allComputer.get(0);

        for (int i = 1; i != allComputer.size(); i++){

            Computer pc0 = allComputer.get(i);

            if(pc0.getDiagonal() > result.getDiagonal()){
                result = pc0;
            }else if(pc0.getDiagonal() == result.getDiagonal()){
                if(pc0.getWeight() > result.getWeight()){
                    result = pc0;
                }
            }
        }

        return result;
    }

    public static String toString(Computer notebook){
        return "Производитель: " + notebook.manufacturerName + "\n" + "Диагональ экрана: " + notebook.diagonal + " дюймов" +
                "\n" + "Вес: " + notebook.weight + " кг" + "\n";
    }


}
