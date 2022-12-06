public class Oxygen implements Runnable{
	
    private int atomId;
    private WaterBuilder waterBuilder;

    public Oxygen(int atomId, WaterBuilder waterBuilder) {
        this.atomId = atomId;
        this.waterBuilder = waterBuilder;   
    }
    
    @Override
    public void run() {
        waterBuilder.createOxygen(atomId);
    }
}
