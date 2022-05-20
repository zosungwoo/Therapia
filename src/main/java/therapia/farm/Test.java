package therapia.farm;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Test {
    private int a;

    public static void main(String[] args) {
        Test test = new Test();
        test.setA(5);
        System.out.println(test.getA());
    }
}
