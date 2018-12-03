public class Pump extends Thread {
    // переименовал об. бензина/топлива
    private Float fuel;
    public  Float max_fuel;   // хмм
    // так же добавил связи с баком и двигателем (почему? - ну присутствие бака,насоса и двигателя внутри
    // автомобиля - еще не факт того что они связаны между собой)
    // насос будет привязан к баку и к двигателю
    private Tank   tank;
    private Engine engine;

    Pump(Float max_fuel) {
        this.max_fuel = max_fuel;
        this.setFuel(0.0F);
    }

    public void setFuel(Float fuel_q) {
        if (fuel_q > 0.5F) {
            System.out.println("PUMP's fuel can't be more 0.5 litrs. 0.5 litrs quantity will be generated");
            fuel=0.5F;
        } else {
            fuel = fuel_q;
        }
    }

    public Float getFuel() {
        return fuel;
    }

    public void setTank(Tank tank) {
        this.tank = tank;
    }

    public void setEngine(Engine engine) {
        this.engine = engine;
    }

    // примерно что содержит run() насоса
    public void run(){
        System.out.println("PUMP - STARTED");
        while(this.tank.getFuel() > 0){
            this.take(0.5F); // забирает пол литра бензина из бака
            System.out.println("Pump takes from tank " + this.getFuel() + "L");
            this.put(this.getFuel());  // прокачиваем в двигетель ВЕСЬ бензин что в насосе!!!
        }
        System.out.println("PUMP - STOPPED");
    }

    public Float take(Float volume){
        // внимение - тут АТОМ ;)!!! - почему
            try {
                this.sleep(100);
            } catch (Exception e) {}

            this.tank.setFuel(this.tank.getFuel() - volume); // уменшаем кол-во топлива в баке
            this.setFuel(this.getFuel() + volume);                          // топливо уже с насосе!

            engine.toBeStopped(this.tank, this);

            return volume;
    }

    public void put(Float volume) {
        // внимение - тут АТОМ ;)!!! - почему
        System.out.println("Pump is putting in engine: " + this.getFuel());
        this.engine.setFuel(this.engine.getFuel() + this.getFuel());
        this.setFuel(0.0F);        // внури насоса уже ничего!!
        engine.toBeStopped(this.tank, this);
    }

}
