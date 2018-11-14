package generator;

import config.Config;
import javafx.geometry.Point3D;

import java.util.*;

public class RuntimeGenerator implements IGenerator {

    public static final Random RANDOM_X = new Random();
    public static final Random RANDOM_Y = new Random();
    public static final Random RANDOM_Z = new Random();

    public static final List<Thread> threads = new ArrayList<Thread>();

    public static double START_X = -75.89911555;
    public static double END_X   = -75.39911555;

    public static double START_Y = 6.31280872815;
    public static double END_Y   = 6.36280872815;

    public static double START_Z = 1300.00;
    public static double END_Z   = 1399.99;

    public List<Point3D> list = Collections.synchronizedList(new ArrayList<Point3D>());

    @Override
    public boolean make(boolean print) {

        START_X = Config.OFFSET.get("start_x");
        END_X = Config.OFFSET.get("end_x");

        START_Y = Config.OFFSET.get("start_y");
        END_Y = Config.OFFSET.get("end_y");

        START_Z = Config.OFFSET.get("start_z");
        END_Z = Config.OFFSET.get("end_z");

        int generationQuantity = Config.VALUES.get("number_of_bees");
        int threadsNumber = Config.VALUES.get("number_of_threads");

        int alpha = generationQuantity / threadsNumber;

        for(int k = 0; k < threadsNumber; k++)
            threads.add(new Thread(new Runnable(){

                @Override
                public void run() {

                    for(int i = 0; i < alpha; i++)
                        list.add(new Point3D(newX(), newY(), newZ()));

                }

            }));

        for(Thread t : threads)
            t.start();

        try {
            for(Thread t : threads)
                t.join();

        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return false;

    }

    public double newX() {
        return START_X  + (START_X - END_X) * RANDOM_X.nextDouble();
    }

    public double newY() {
        return START_Y  + (START_Y - END_Y) * RANDOM_Y.nextDouble();
    }

    public double newZ() {
        return START_Z + (START_Z - END_Z) * RANDOM_Z.nextDouble();
    }

    @Override
    public List<Point3D> getBees() {
        return list;
    }

}
