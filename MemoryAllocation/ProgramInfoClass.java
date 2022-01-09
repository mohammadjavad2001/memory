package MemoryAllocation;

public class ProgramInfoClass {
    private int PId, PLimit, PBase;

    public ProgramInfoClass(int PId, int PLimit, int PBase) {
        this.PId = PId;
        this.PLimit = PLimit;
        this.PBase = PBase;
    }

    public int getPId() {
        return PId;
    }

    public int getPBase() {
        return PBase;
    }

    public int getPLimit() {
        return PLimit;
    }

    public void setPId(int PId) {
        this.PId = PId;
    }

    public void setPBase(int PBase) {
        this.PBase = PBase;
    }

    public void setPLimit(int PLimit) {
        this.PLimit = PLimit;
    }
}
