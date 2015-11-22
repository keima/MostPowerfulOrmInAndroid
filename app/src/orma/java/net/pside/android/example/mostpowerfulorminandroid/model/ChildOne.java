package net.pside.android.example.mostpowerfulorminandroid.model;

import com.github.gfx.android.orma.annotation.Column;
import com.github.gfx.android.orma.annotation.Table;

@Table
public class ChildOne {

    @Column
    public ChildTwo childTwo;
}
