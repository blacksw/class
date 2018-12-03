public class Car{
    private String brand;
    // я бы внедрил 3 составляющие обьекты в об. Автомобиля
    // бензин передается вниз по цепочке от бака до двигателя
    private Tank   tank;
    private Pump   pump;
    private Engine engine;

    public Car(String brand){
        // этот метом моделирует сборку авто - тут ставим все инструкции по сборке
        this.brand = brand;
        // тут мы закручиваем компоненты авто в шаси )
        this.tank   = new Tank(200F); // макс литров - объем
        this.pump   = new Pump(0.5F); // макс литров - объем
        this.engine = new Engine(2.0F); // солько объем двигателя
        // тут мы соединяем компоненты между собой
        this.pump.setTank(this.tank);
        this.pump.setEngine(this.engine);
        // вот теперь насос знает откуда забирать и куда отдавать топливо

    }

    public void start(){
        // этот метом моделирует работу авто - тут ставим все инструкции
        this.tank.setFuel(100.0F);     // залили туда 100 литров топлива
        System.out.println("100 liters of fuel -> tank");
        // стартуем двигатель и насос - не забываем!!! - это 2 трида
        // тоесть логика тридов должна быть в методах run()!!!
        System.out.println("CAR started!");
        this.pump.start();
        this.engine.start();
        try {
            pump.join();
            engine.join();
        } catch (Exception e) {}
        // ПОСЛЕ! завершения тридов вызвавший их метод тоже завершается
        // т е - авто останавливется
        System.out.println("CAR stopped!");
    }
}