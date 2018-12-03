public class Tank {
    // переименовал об. бензина/топлива
    private Float fuel;
    public  Float max_fuel;   // хмм

    Tank(Float max_fuel) {
        this.max_fuel = max_fuel;
        this.setFuel(100.0F);
    }

    //Fuel setter
    public void setFuel(Float fuel_q) {
        if (fuel_q > 200F) {
            System.out.println("TANK's fuel can't be more 200 litrs. 200 litrs quantity will be generated");
            fuel=200F;
        } else {
            fuel = fuel_q;
        }
    }

    //Fuel getter
    public Float getFuel() {
        return fuel;
    }
}
