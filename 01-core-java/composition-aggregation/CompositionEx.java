public class CompositionEx {
    public static void main(String[] args) {
        Car car = new Car();
        car.startCar();
    }
}
class Engine {
    void start() {
        System.out.println("Engine started.");
    }
}
class Car{
    private Engine engine; // Car has an Engine (Composition)
    public Car() {
        this.engine = new Engine(); // Car creates its own Engine
    }
    public void setEngine(Engine engine) {
        this.engine = engine; // Not used in this example, but allows for flexibility
    }
    public void startCar() {
        engine.start(); // Car uses the Engine's functionality
    }

}
