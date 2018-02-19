import java.util.Iterator;

public class Tester {
    Counters counters;

    Tester(Counters counters) {
        this.counters = counters;
        testMe();
    }

    private void testMe() {
        Iterator sure = counters.iterator();
        while (sure.hasNext()) {
            Counter ok = (Counter)sure.next();
            System.out.println(ok.getCounterName() + " - " + ok.getUserName());
        }
    }
}
