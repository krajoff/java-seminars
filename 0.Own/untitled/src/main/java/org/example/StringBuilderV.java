package org.example;

public class StringBuilderV {
    private final StringBuilder sb;

    public StringBuilderV() {
        this.sb = new StringBuilder();
    }

    public StringBuilderV(String initial) {
        this.sb = new StringBuilder(initial);
    }

    public StringBuilderV appendLn(String str) {
        sb.append(str).append("\r\n");
        return this;
    }

    public String toString() {
        return sb.toString();
    }

    public StringBuilderV append(String str) {
        sb.append(str);
        return this;
    }

}
