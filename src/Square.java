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

    Color getNumberColor() {
        if (this.value < 16)
            return new Color(0x776e65);
        return new Color(0xf9f6f2);
    }

    Color getColor() {
        switch (this.value) {
            case 2:
                return new Color(0xeee4da);
            case 4:
                return new Color(0xede0c8);
            case 8:
                return new Color(0xf2b179);
            case 16:
                return new Color(0xf59563);
            case 32:
                return new Color(0xf67c5f);
            case 64:
                return new Color(0xf65e3b);
            case 128:
                return new Color(0xedcf72);
            case 256:
                return new Color(0xedcc61);
            case 512:
                return new Color(0xedc850);
            case 1024:
                return new Color(0xedc53f);
            case 2048:
                return new Color(0xedc22e);
        }
        return new Color(0xcdc1b4);
    }
}