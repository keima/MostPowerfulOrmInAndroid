package net.pside.android.example.mostpowerfulorminandroid.model;

import com.github.gfx.android.orma.annotation.Column;
import com.github.gfx.android.orma.annotation.Table;

@Table
public class Parent {

    @Column
    public ChildOne childOne;
}
