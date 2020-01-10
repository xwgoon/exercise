package com.horstmann.corejava.v1ch05.enums;

import com.horstmann.corejava.v1ch09.map.Employee;
import org.apache.commons.lang3.StringUtils;

import java.io.Closeable;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * This program demonstrates enumerated types.
 *
 * @author Cay Horstmann
 * @version 1.0 2004-05-24
 */
public class EnumTest {
    public static void main(String[] args) {
//      Scanner in = new Scanner(System.in);
//      System.out.print("Enter a size: (SMALL, MEDIUM, LARGE, EXTRA_LARGE) ");
//      String input = in.next().toUpperCase();
//      Size size = Enum.valueOf(Size.class, input);
//      System.out.println("size=" + size);
//      System.out.println("abbreviation=" + size.getAbbreviation());
//      if (size == Size.EXTRA_LARGE)
//         System.out.println("Good job--you paid attention to the _.");


//      Size s=new Size("123");
//      System.out.println(Size.SMALL.equals(Size.SMALL));
//      System.out.println(Size.SMALL == Size.SMALL);

//        System.out.println(Size.LARGE.toString());
//        System.out.println(Size.LARGE);
//        System.out.println(Size.LARGE.name());
//        System.out.println(Size.LARGE.ordinal());
//        System.out.println(Size.LARGE.compareTo(Size.EXTRA_LARGE));
//
//        System.out.println(Enum.valueOf(Size.class, "LARGE"));
//        System.out.println(Size.valueOf("LARGE"));
//        System.out.println(Arrays.toString(Size.values()));

//        Arrays.asList(Size.values()).stream().filter();
//        Stream.of(Size.values()).

//        byte b = 0x7f; //正确
//        short s = 0x7fff; //正确
//        int i = 0x7fffffff; //正确
//        long l = 1; //正确
//        float f = 1; //正确
//        double d = 1; //正确
//
//        Byte B = 1; //正确
//        Short S = 1; //正确
//        Integer I = 1; //正确
//        Long L = 1L; //编译报错，须写成 1L（1l）
//        Float F = 1f; //编译报错，须写成 1f（1F）
//        Double D = 1d; //编译报错，须写成 1d（1D或1.）

//        int from=6;
//        int to=13;


//        System.out.println(StringUtils.leftPad(Integer.toBinaryString(-1 << -3 >>> -5), 32, "0"));
//        System.out.println(StringUtils.leftPad(Long.toBinaryString(-1L << (from-to-1) >>> (-1-to)), 64, "0"));
//        System.out.println(StringUtils.leftPad(Long.toBinaryString((-1L >>>  (from - to - 1)) << from), 64, "0"));
//        System.out.println(Long.bitCount(-1L << (from-to-1) >>> (-1-to)));
//        System.out.println(Integer.toBinaryString(-1<<1));
//        System.out.println(Integer.toBinaryString(-1<<-31));

//        EnumSet<Size> enumSet=EnumSet.range(Size.MEDIUM,Size.LARGE);
//        System.out.println(enumSet);

//        Set<Size>[] arr=(Set<Size>[])new Set[5];
//        arr[0]=new HashSet<>();
//        arr[0].add(Size.LARGE);

        List<Cloth> cloths=new ArrayList<>();
        cloths.add(new Cloth("T恤", Size.SMALL));
        cloths.add(new Cloth("羽绒服", Size.SMALL));
        cloths.add(new Cloth("夹克", Size.LARGE));
        cloths.add(new Cloth("保暖衣", Size.LARGE));
        cloths.add(new Cloth("毛衣", Size.EXTRA_LARGE));
        cloths.add(new Cloth("毛衣", Size.EXTRA_LARGE));

        Map<Size, List<Cloth>> map1=cloths.stream().collect(Collectors.groupingBy(Cloth::getSize));
        System.out.println("map1("+map1.getClass().getTypeName()+"):"+map1);
        Map<Size, Set<Cloth>> map2=cloths.stream().collect(Collectors.groupingBy(Cloth::getSize,Collectors.toSet()));
        System.out.println("map2("+map2.getClass().getTypeName()+"):"+map2);
        Map<Size, Set<Cloth>> map3=cloths.stream().collect(Collectors.groupingBy(Cloth::getSize,()->new EnumMap<>(Size.class),Collectors.toSet()));
        System.out.println("map3("+map3.getClass().getTypeName()+"):"+map3);

//        Map<Size, Cloth> map4=cloths.stream().collect(Collectors.toMap(
//                Cloth::getSize,
//                it->it
//                ,
//                (x,y)->y
//                ,
//                ()->new EnumMap<>(Size.class)
//                ));
//        System.out.println("map4("+map4.getClass().getTypeName()+"):"+map4);

        Map<Size, Set<Cloth>> map5=cloths.stream().collect(Collectors.toMap(
                Cloth::getSize,
                it->{
                    Set<Cloth> list=new HashSet<>();
                    list.add(it);
                    return list;
                },
                (x,y)->{
                    x.addAll(y);
                    return x;
                },
                ()->new EnumMap<>(Size.class)
        ));
        System.out.println("map5("+map5.getClass().getTypeName()+"):"+map5);

        Phase.Transition.from(Phase.GAS,Phase.SOLID);



    }
}

class Cloth{
    private String name;
    private Size size;

    public Cloth(String name, Size size) {
        this.name = name;
        this.size = size;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Size getSize() {
        return size;
    }

    public void setSize(Size size) {
        this.size = size;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Cloth)) return false;
        Cloth cloth = (Cloth) o;
        return Objects.equals(name, cloth.name) &&
                size == cloth.size;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, size);
    }

    @Override
    public String toString() {
        return "Cloth{" +
                "name='" + name + '\'' +
                ", size=" + size +
                '}';
    }
}

enum Size {
    SMALL("S"), MEDIUM("M"), LARGE("L"), EXTRA_LARGE("XL");

    Size(String abbreviation) {
        this.abbreviation = abbreviation;
    }

    public String getAbbreviation() {
        return abbreviation;
    }

    private String abbreviation;
}

enum Phase {
    SOLID, LIQUID, GAS;
    public enum Transition {
        MELT(SOLID, LIQUID), FREEZE(LIQUID, SOLID),
        BOIL(LIQUID, GAS), CONDENSE(GAS, LIQUID),
        SUBLIME(SOLID, GAS), DEPOSIT(GAS, SOLID);
        private final Phase from;
        private final Phase to;
        Transition(Phase from, Phase to) {
            this.from = from;
            this.to = to;
        }
        // Initialize the phase transition map
        private static final Map<Phase, Map<Phase, Transition>>
                m = Stream.of(values()).collect(Collectors.groupingBy(t -> t.from,
                () -> new EnumMap<>(Phase.class),
                Collectors.toMap(t -> t.to, t -> t,
                        (x, y) -> y, () -> new EnumMap<>(Phase.class))));
        public static Transition from(Phase from, Phase to) {
            return m.get(from).get(to);
        }
    }
}
