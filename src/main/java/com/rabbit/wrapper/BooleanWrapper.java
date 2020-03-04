package com.rabbit.wrapper;

public class BooleanWrapper extends Wrapper<Boolean> {

    public BooleanWrapper() {
        this(false);
    }

    public BooleanWrapper(Boolean value) {
        super(value);
    }

    public boolean invertValue() {
        this.value = !this.value;
        return this.value;
    }

    @Override
    public int compareTo(Wrapper<Boolean> o) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public boolean equals(Object obj) {
        // TODO
        return false;
    }

}