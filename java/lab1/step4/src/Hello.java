public class Hello {
    public static void main(String args[]){
        System.out.println("Hello!");
        Dog mydog = new Dog();
        System.out.println("Dog: " + mydog.voice());
        System.out.println("2 + 2 = " + ExternalSummator.sum(2,2));
        System.out.println("Finished step 4");
    }
}