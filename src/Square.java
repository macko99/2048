import java.awt.*;

class Square {
    private int value;

    Square() {
        this.value = 0;
    }

    Square(int value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this)
            return true;
        if (!(obj instanceof Square))
            return false;
        return ((Square) obj).value == this.value;
    }

    boolean isEmpty() {
        return this.value == 0;
    }

    int getValue() {
        return this.value;
    }

    void setValue(int value) {
        this.value = value;
    }

    int getNumberFontSize() {
        return value < 100 ? 36 : value < 1000 ? 32 : 24;
    }

    Color getNumberFontColor() {
        if (this.value < 16)
            return new Color(119, 110, 101);
        return new Color(249, 246, 242);
    }

    Color getColor() {
        switch (this.value) {
            case 2:
                return new Color(238, 228, 218);
            case 4:
                return new Color(237, 224, 200);
            case 8:
                return new Color(242, 177, 121);
            case 16:
                return new Color(245, 149, 99);
            case 32:
                return new Color(246, 124, 95);
            case 64:
                return new Color(246, 94, 59);
            case 128:
                return new Color(237, 207, 114);
            case 256:
                return new Color(237, 204, 97);
            case 512:
                return new Color(237, 200, 80);
            case 1024:
                return new Color(237, 197, 63);
            case 2048:
                return new Color(237, 194, 46);
        }
        return new Color(205, 193, 180);
    }
}