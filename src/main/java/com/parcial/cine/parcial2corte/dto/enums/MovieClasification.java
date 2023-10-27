/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.parcial.cine.parcial2corte.dto.enums;


public enum MovieClasification {

    CLASIFICATION_TP("TP"),
    CLASIFICATION_7("7"),
    CLASIFICATION_12("12"),
    CLASIFICATION_15("15"),
    CLASIFICATION_18("18"),;

    public final String VALUE;

    private MovieClasification(String value) {
        this.VALUE = value;
    }

}
