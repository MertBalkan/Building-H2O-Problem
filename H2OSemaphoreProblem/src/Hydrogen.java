public class Hydrogen implements Runnable{
    private int atomId;
    private WaterBuilder waterBuilder;

    public Hydrogen(int atomId, WaterBuilder waterBuilder) {
        this.atomId = atomId;
        this.waterBuilder = waterBuilder;
    }

    @Override
    public void run() {
    	waterBuilder.createHydrogen(atomId);
    }
}
