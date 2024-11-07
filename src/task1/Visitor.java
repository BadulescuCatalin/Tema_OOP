package task1;

interface Visitor<T extends Entity>{
    boolean visit(T entity);
}