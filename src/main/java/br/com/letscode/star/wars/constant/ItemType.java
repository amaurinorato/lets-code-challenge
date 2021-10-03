package br.com.letscode.star.wars.constant;

public enum ItemType {

    WEAPON(4)
    , AMMUNITION(3)
    , WATER(2)
    , FOOD(1);

    Integer point;

    ItemType(Integer point) {
        this.point = point;
    }

    public Integer getPoint() {
        return point;
    }
}
