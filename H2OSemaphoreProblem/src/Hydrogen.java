import java.util.concurrent.Semaphore;

public class Hydrogen implements Runnable{
    int elementId;
    Semaphore mutex;
    Semaphore oxyQueue;
    Semaphore hydroQueue;
    Semaphore barrier;

    public Hydrogen(int elementId, Semaphore mutex, Semaphore oxyQueue, Semaphore hydroQueue, Semaphore barrier) {
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
            WaterBuilder.currenthydroCount++;
            System.out.println("Hidrojen Oluşturuldu ID: "+elementId+" Anlık Hidrojen Sayısı: "+WaterBuilder.currenthydroCount );
            if(WaterBuilder.currenthydroCount>=2 && WaterBuilder.currentOxyCount >=1){
                hydroQueue.release(2);
                oxyQueue.release();
            }
            else{
                mutex.release();
            }
            hydroQueue.acquire();
            bond();
            barrier.acquire();
        } catch (InterruptedException e) {
        }
    }
    public void bond(){
        System.out.println(elementId+ " IDli Hidrojen Bağ Kurmaya Hazır                             H");
    }
}
