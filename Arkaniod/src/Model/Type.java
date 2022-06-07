package Model;

public enum Type {
    FAST("FA"),
    SLOW("SL"),
    LONGER("LO"),
    SHORTER("SH"),
    MULTI("MU"),
    FIRED("FI"),
    CONFUSED("CO"),
    RANDOM("RA");

    String write;
    Type(String write) {
        this.write = write;
    }

    @Override
    public String toString() {
        return write ;
    }

    public static Type findType(String write){
        if (write.equals("FA")){
            return FAST;
        }
        if (write.equals("SL")){
            return SLOW;
        }
        if (write.equals("LO")){
            return LONGER;
        }
        if (write.equals("SH")){
            return SHORTER;
        }
        if (write.equals("MU")){
            return MULTI;
        }
        if (write.equals("FI")){
            return FIRED;
        }
        if (write.equals("CO")){
            return CONFUSED;
        }
        if (write.equals("RA")){
            return RANDOM;
        }

        return null;
    }
}
