import java.util.concurrent.Semaphore;

public class Oxygen implements Runnable{
    int elementId;
    Semaphore mutex;
    Semaphore oxyQueue;
    Semaphore hydroQueue;
    Semaphore barrier;

    public Oxygen(int elementId, Semaphore mutex, Semaphore oxyQueue, Semaphore hydroQueue, Semaphore barrier) {
        this.elementId = elementId;
        this.mutex = mutex;
        this.oxyQueue = oxyQueue;
        this.hydroQueue = hydroQueue;
        this.barrier = barrier;
    }


    @Override
    public void run() {
        try {
            mutex.acquire();
            WaterBuilder.currentOxyCount++;
            System.out.println("Oksijen Oluşturuldu ID: "+elementId+" Anlık Oksijen Sayısı: "+WaterBuilder.currentOxyCount );
            if (WaterBuilder.currenthydroCount >= 2 ){
                hydroQueue.release(2);

                oxyQueue.release();

            }
            else{
                mutex.release();
            }
            oxyQueue.acquire();
            bond();
            barrier.acquire();
            createWater();
            mutex.release();
        } catch (InterruptedException e) {
        }
    }
    public void createWater(){
        System.out.println("------------------------Su Oluşturuluyor------------------------");
        barrier.release(3);
        WaterBuilder.currenthydroCount = WaterBuilder.currenthydroCount -2;
        WaterBuilder.currentOxyCount--;
        WaterBuilder.waterCount++;
        System.out.println("Su Oluşturuldu "+ elementId + "Nolu Oksijen Tarafından.");
        System.out.println("Oluşturulan Toplam Su Sayısı: " + WaterBuilder.waterCount);
        System.out.println("-------------------------------W--------------------------------");
    }
    public void bond(){
        System.out.println(elementId+ " IDli Oksijen Bağ Kurmaya Hazır                                  O");
    }
}
