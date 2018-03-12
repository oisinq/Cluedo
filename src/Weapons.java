/*  Cluedo - Sprint 2
    Team: auroraBorealis
    Members: Oisin Quinn (16314071), Darragh Clarke (16387431), Charlie Kelly (16464276)
    "Aurora Borealis! At this time of year? At this time of day? In this part of the country? Localized entirely within your kitchen?" */

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class Weapons implements Iterable<Weapon>, Iterator<Weapon> {

    private final ArrayList<Weapon> weapons = new ArrayList<>();
    private Iterator<Weapon> iterator;
    private int[][] wepLocation = new int[][]{{40, 70}, {554, 70}, {40, 371}, {554, 235}, {40, 585}, {554, 585}, {554, 393}, {365, 99}, {254, 574}};
    //this will say what room each coord is linked to  kitchen   conservatory   dining  billiard    lounge      study        library   ball room       hall

    /**
     * Randomly allocates a location to weapons on each game launch
     */
    Weapons() {
        weaponLocationRandomiser();
        weapons.add(new Weapon("Rope", wepLocation[0][0], wepLocation[0][1], "/rope.PNG"));
        weapons.add(new Weapon("Dagger", wepLocation[1][0], wepLocation[1][1], "/dagger.PNG"));
        weapons.add(new Weapon("Wrench", wepLocation[2][0], wepLocation[2][1], "/wrench.PNG"));
        weapons.add(new Weapon("Pistol", wepLocation[3][0], wepLocation[3][1], "/revolver.PNG"));
        weapons.add(new Weapon("Candlestick", wepLocation[4][0], wepLocation[4][1], "/candlestick.PNG"));
        weapons.add(new Weapon("Lead Pipe", wepLocation[5][0], wepLocation[5][1], "/lead_pipe.PNG"));
    }

    public Weapon get(String name) {
        for (Weapon weapon : weapons) {
            if (weapon.hasName(name)) {
                return weapon;
            }
        }
        return null;
    }

    public boolean hasNext() {
        return iterator.hasNext();
    }

    public Weapon next() {
        return iterator.next();
    }

    public Iterator<Weapon> iterator() {
        iterator = weapons.iterator();
        return iterator;
    }

    private void weaponLocationRandomiser() {
        Random rnd = ThreadLocalRandom.current();//creates random numbers
        for (int i = wepLocation.length - 1; i > 0; i--)//run this loop for the length of the array
        {
            int index = rnd.nextInt(i + 1);
            int a = wepLocation[index][0];//a and b are placeholders for values in the original array
            int b = wepLocation[index][1];
            wepLocation[index][0] = wepLocation[i][0];//the original values get replaced with a random other value
            wepLocation[index][1] = wepLocation[i][1];
            wepLocation[i][0] = a;//the replaced values move to where their replacement had been
            wepLocation[i][1] = b;
        }
    }
}