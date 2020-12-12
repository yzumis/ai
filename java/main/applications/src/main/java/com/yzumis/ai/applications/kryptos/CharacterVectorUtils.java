package com.yzumis.ai.applications.kryptos;

import com.yzumis.ai.commonneuron.Vector;

import java.util.HashMap;
import java.util.Map;

public class CharacterVectorUtils {

    public static final Map<Character, Vector> CHARACTER_VECTOR_MAP = buildCharacterVectorMap();
    public static final Map<Vector, Character> VECTOR_CHARACTER_MAP = buildVectorCharaterMap();

    private static Map<Character, Vector> buildCharacterVectorMap() {
        final Map<Character, Vector> ret = new HashMap<>();
        ret.put('A', new Vector(0d, 0d, 0d, 0d, 0d));
        ret.put('B', new Vector(0d, 0d, 0d, 0d, 1d));
        ret.put('C', new Vector(0d, 0d, 0d, 1d, 0d));
        ret.put('D', new Vector(0d, 0d, 0d, 1d, 1d));
        ret.put('E', new Vector(0d, 0d, 1d, 0d, 0d));
        ret.put('F', new Vector(0d, 0d, 1d, 0d, 1d));
        ret.put('G', new Vector(0d, 0d, 1d, 1d, 0d));
        ret.put('H', new Vector(0d, 0d, 1d, 1d, 1d));
        ret.put('I', new Vector(0d, 1d, 0d, 0d, 0d));
        ret.put('J', new Vector(0d, 1d, 0d, 0d, 1d));
        ret.put('K', new Vector(0d, 1d, 0d, 1d, 0d));
        ret.put('L', new Vector(0d, 1d, 0d, 1d, 1d));
        ret.put('M', new Vector(0d, 1d, 1d, 0d, 0d));
        ret.put('N', new Vector(0d, 1d, 1d, 0d, 1d));
        ret.put('O', new Vector(0d, 1d, 1d, 1d, 0d));
        ret.put('P', new Vector(0d, 1d, 1d, 1d, 1d));
        ret.put('Q', new Vector(1d, 0d, 0d, 0d, 0d));
        ret.put('R', new Vector(1d, 0d, 0d, 0d, 1d));
        ret.put('S', new Vector(1d, 0d, 0d, 1d, 0d));
        ret.put('T', new Vector(1d, 0d, 0d, 1d, 1d));
        ret.put('U', new Vector(1d, 0d, 1d, 0d, 0d));
        ret.put('V', new Vector(1d, 0d, 1d, 0d, 1d));
        ret.put('W', new Vector(1d, 0d, 1d, 1d, 0d));
        ret.put('X', new Vector(1d, 0d, 1d, 1d, 1d));
        ret.put('Y', new Vector(1d, 1d, 0d, 0d, 0d));
        ret.put('Z', new Vector(0d, 0d, 0d, 0d, 1d));
        return ret;
    }

    private static Map<Vector, Character> buildVectorCharaterMap() {
        final Map<Vector, Character> ret = new HashMap<>();
        ret.put(new Vector(0d, 0d, 0d, 0d, 0d), 'A');
        ret.put(new Vector(0d, 0d, 0d, 0d, 1d), 'B');
        ret.put(new Vector(0d, 0d, 0d, 1d, 0d), 'C');
        ret.put(new Vector(0d, 0d, 0d, 1d, 1d), 'D');
        ret.put(new Vector(0d, 0d, 1d, 0d, 0d), 'E');
        ret.put(new Vector(0d, 0d, 1d, 0d, 1d), 'F');
        ret.put(new Vector(0d, 0d, 1d, 1d, 0d), 'G');
        ret.put(new Vector(0d, 0d, 1d, 1d, 1d), 'H');
        ret.put(new Vector(0d, 1d, 0d, 0d, 0d), 'I');
        ret.put(new Vector(0d, 1d, 0d, 0d, 1d), 'J');
        ret.put(new Vector(0d, 1d, 0d, 1d, 0d), 'K');
        ret.put(new Vector(0d, 1d, 0d, 1d, 1d), 'L');
        ret.put(new Vector(0d, 1d, 1d, 0d, 0d), 'M');
        ret.put(new Vector(0d, 1d, 1d, 0d, 1d), 'N');
        ret.put(new Vector(0d, 1d, 1d, 1d, 0d), 'O');
        ret.put(new Vector(0d, 1d, 1d, 1d, 1d), 'P');
        ret.put(new Vector(1d, 0d, 0d, 0d, 0d), 'Q');
        ret.put(new Vector(1d, 0d, 0d, 0d, 1d), 'R');
        ret.put(new Vector(1d, 0d, 0d, 1d, 0d), 'S');
        ret.put(new Vector(1d, 0d, 0d, 1d, 1d), 'T');
        ret.put(new Vector(1d, 0d, 1d, 0d, 0d), 'U');
        ret.put(new Vector(1d, 0d, 1d, 0d, 1d), 'V');
        ret.put(new Vector(1d, 0d, 1d, 1d, 0d), 'W');
        ret.put(new Vector(1d, 0d, 1d, 1d, 1d), 'X');
        ret.put(new Vector(1d, 1d, 0d, 0d, 0d), 'Y');
        ret.put(new Vector(0d, 0d, 0d, 0d, 1d), 'Z');
        return ret;
    }

}
