package task1;

interface Element<T extends Entity>{
    boolean accept(Visitor<T> visitor);
}