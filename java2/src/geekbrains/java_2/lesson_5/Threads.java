package geekbrains.java_2.lesson_5;

public class Threads {
    private static final int size = 10000000;
    private static final int h = size / 2;
    private float[] arr = new float[size];

    Threads() {
        for (int i = 0; i < arr.length; i++) {
            arr[i] = 1;
        }
    }

    public void OneThread() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                long start_time = System.currentTimeMillis();
                for (int i = 0; i < arr.length; i++) {
                    arr[i] = (float) (arr[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
                }
                long stop_time = System.currentTimeMillis();
                System.err.println(Thread.currentThread().getName()+" Выполнение в один поток "+Thread.currentThread().getName()+":");
                System.err.println(Thread.currentThread().getName()+" Старт в " + start_time);
                System.err.println(Thread.currentThread().getName()+" Окончание в " + stop_time);
                System.err.println(Thread.currentThread().getName()+" Время выполнения: " + (double) (stop_time - start_time) / 1000 + " сек.");
            }
        }).start();
    }

    public void TwoThreads() {
        float[] a1=new float[h];
        float[] a2=new float[h];
        long start_time = System.currentTimeMillis();
        System.err.println("Выполнение в два потока:");
        System.err.println("Старт в " + start_time);
        Thread mass_split = new Thread(new Runnable() {
            @Override
            public void run() {
                long start_time = System.currentTimeMillis();
                System.arraycopy(arr,0,a1,0,h);
                System.arraycopy(arr,h,a2,0,h);
                long stop_time = System.currentTimeMillis();
                System.err.println("Время разбивки на два массива: " + (double) (stop_time - start_time) / 1000 + " сек.");
            }
        });
        mass_split.start();
        try {
            mass_split.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Thread first_t=new Thread(new Runnable() {
            @Override
            public void run() {
                long start_time = System.currentTimeMillis();
                for (int i = 0; i < a1.length; i++) {
                    a1[i] = (float) (a1[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
                }
                long stop_time = System.currentTimeMillis();
                System.err.println(Thread.currentThread().getName()+" Выполнение в первом потоке:");
                System.err.println(Thread.currentThread().getName()+" Старт в " + start_time);
                System.err.println(Thread.currentThread().getName()+" Окончание в " + stop_time);
                System.err.println(Thread.currentThread().getName()+" Время выполнения: " + (double) (stop_time - start_time) / 1000 + " сек.");
            }
        });

        Thread second_t=new Thread(new Runnable() {
            @Override
            public void run() {
                long start_time = System.currentTimeMillis();
                for (int i = 0; i < a2.length; i++) {
                    a2[i] = (float) (a2[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
                }
                long stop_time = System.currentTimeMillis();
                System.err.println(Thread.currentThread().getName()+" Выполнение во втором потоке:");
                System.err.println(Thread.currentThread().getName()+" Старт в " + start_time);
                System.err.println(Thread.currentThread().getName()+" Окончание в " + stop_time);
                System.err.println(Thread.currentThread().getName()+" Время выполнения: " + (double) (stop_time - start_time) / 1000 + " сек.");
            }
        });

        first_t.start();
        second_t.start();
        try {
            first_t.join();
            second_t.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Thread mass_merge = new Thread(new Runnable() {
            @Override
            public void run() {
                long start_time = System.currentTimeMillis();
                System.arraycopy(a1,0,arr,0,h);
                System.arraycopy(a2,0,arr,h,h);
                long stop_time = System.currentTimeMillis();
                System.err.println("Время склейки двух массивов: " + (double) (stop_time - start_time) / 1000 + " сек.");
            }
        });
        mass_merge.start();
        try {
            mass_merge.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        long stop_time = System.currentTimeMillis();
        System.err.println("Выполнение в два потока закончено.\nЗатраченное время: " + (double) (stop_time - start_time) / 1000 + " сек.");
    }
}
