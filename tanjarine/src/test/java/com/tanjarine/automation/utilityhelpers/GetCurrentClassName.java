package com.tanjarine.automation.utilityhelpers;

/**
 * Created by ypolshchykau on 03.02.2015.
 */
public class GetCurrentClassName {
//     class created for the purpose of implementation REFLECTION mechanism

    public static String getCurrentClassName(Class classParam){
        Class<?> enclosingClass = classParam.getEnclosingClass();
        String className="";
        if (enclosingClass != null) {
            className = enclosingClass.getName();
        } else {
           className= classParam.getName();
        }

        return  className;
    }
}
