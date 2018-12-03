public class Engine extends Thread  {
    // переименовал об. бензина/топлива
    private Float  fuel;
    public  Float max_fuel;   // хмм
    public Boolean stopEngine;

    Engine (Float max_fuel) {
        this.max_fuel = max_fuel;
        this.setFuel(0.0F);
        stopEngine=false;
    }

    public void setFuel(Float fuel_q) {
        if (fuel_q > 2.0F) {
            System.out.println("ENGINE's fuel can't be more 2.0 litrs. 2.0 litrs quantity will be generated");
            fuel=2.0F;
        } else {
            fuel = fuel_q;
        }
    }

    public Float getFuel() {
        return fuel;
    }

    // примерно что содержит run() двиг.
    public void run(){
        System.out.println("ENGINE - STARTED");
        while(!stopEngine){
            this.burn(); // сгорает ВЕСЬ обьем топлива что внутри двигателя
            // ВНИМАНИЕ!!! тут слабое место - если вдруг в какой-то момент
            // не будет больше топлива в двигателе - цикл прекратится!!!
        }
        System.out.println("ENGINE - STOPPED");
    }

    public void burn(){
        try {
            this.sleep(100);
        } catch (Exception e) {}

        if (!stopEngine && getFuel() == 2F) {
            System.out.println("    Engine fuel: " + this.getFuel());
            System.out.println("    Engine burning: " + this.getFuel() + "L");
            this.setFuel(0.0F);
            System.out.println("    Engine fuel: " + this.getFuel());
        }
    }

    public void toBeStopped(Tank tank, Pump pump) {
        if ( tank.getFuel()==0 && pump.getFuel()==0 && this.getFuel() <= 2.0F) stopEngine=true;
        else stopEngine=false;
    }
}
