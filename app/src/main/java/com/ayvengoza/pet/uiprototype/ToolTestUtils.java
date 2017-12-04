package com.ayvengoza.pet.uiprototype;

import java.util.ArrayList;
import java.util.Locale;
import java.util.Random;

/**
 * Created by ang on 04.12.17.
 */

public class ToolTestUtils {
    private static final String[] BRANDS ={
            "Ace", "Bosch", "DeWalt", "Irwin", "Jet", "Kerg",
            "Makita", "Porter Cable", "Skil", "Stanley", "Stihi"
    };
    private static final String[] DETAILS_HP = {
            "1/4 HP", "1/2 HP",  " 3/4 HP", "1 HP", " 1 1/2 HP", "2 HP"
    };
    private static final String[] DETAILS_CLAMP_TYPE = {
            "Bar", "Spring", "Quick-Grip", "Pipe", "Parallel"
    };
    private static final String[] DETAILS_INCHES = {
            "2\"", "5\"", "12\"", "18\"", "24\"", "36\"", "48\""
    };
    private static final String[] DETAILS_BATTERY = {
            "12V", "18V", "20V", "24V", "32V", "48V"
    };

    private static final String[] DETAILS_BLADE_SIZE = {
            "Small", "Medium", "Large"
    };
    private static final String[] TYPES_SAWS_STATIONARY = {
            "Stationary saw"
    };
    private static final String[] TYPES_SAWS_NOT_STATIONARY = {
            "Not stationary saw"
    };
    private static final String[] TYPES_DRILLS_STATIONARY = {
            "Stationary drills"
    };
    private static final String[] PRICE_LOW = {
            "2", "3", "4", "5"
    };
    private static final String[] PRICE_HIGH = {
            "20", "30", "40", "50"
    };
    private static final String[] PRICE_MEDIUM = {
            "8", "10", "12", "14"
    };

    private final Random mRandom;

    public ToolTestUtils(){
        this(0);
    }

    public ToolTestUtils(long seed){
        mRandom = new Random(seed);
    }

    public Tool getNewTool(ToolType toolType, ToolPagerAdapter.Tab tab){
        final String brand = getRandom(BRANDS);
        String name = brand + " ";
        String price = null;
        final String[] details = new String[3];
        switch (toolType){
            case CLAMPS:
                details[0] = getRandom(DETAILS_CLAMP_TYPE);
                details[1] = getRandom(DETAILS_INCHES);
                name += details[1] + " " + details[0] + " Clamp";
                details[1] += " opening";
                price = getRandom(PRICE_LOW);
                break;
            case SAWS:
                details[0] = getRandom(DETAILS_BLADE_SIZE);
                details[1] = getRandom(DETAILS_INCHES);
                if(tab == ToolPagerAdapter.Tab.BATTERY){
                    details[2] = getRandom(DETAILS_BATTERY);
                }
                if(tab == ToolPagerAdapter.Tab.STATIONARY){
                    name = getRandom(TYPES_SAWS_STATIONARY);
                } else {
                    name += getRandom(TYPES_SAWS_NOT_STATIONARY);
                }
                break;
            case DRILLS:
                details[0] = getRandom(DETAILS_HP);
                if(tab == ToolPagerAdapter.Tab.BATTERY){
                    details[1] = getRandom(DETAILS_BATTERY);
                }
                if(tab == ToolPagerAdapter.Tab.STATIONARY){
                    details[2] = getRandom(DETAILS_INCHES) + " throat";
                    name += getRandom(TYPES_DRILLS_STATIONARY);
                } else {
                    name += "Drill";
                }
                break;
            case SANDERS:
                name += "Sander";
                break;
            case ROUTERS:
                name += "Router";
                break;
            case MORE:
                name += "Tool";
        }
        if(price == null){
            if(tab == ToolPagerAdapter.Tab.STATIONARY){
                price = getRandom(PRICE_HIGH);
            } else {
                price = getRandom(PRICE_MEDIUM);
            }
        }

        String description = "The latest and greatest form " + brand + " takes " +
                toolType.name().toLowerCase(Locale.getDefault()) +
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit. " +
                "Nulla hendrerit dictum lectus eget malesuada. " +
                "Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere " +
                "cubilia Curae; Donec ac diam egestas mi euismod bibendum id eget diam. " +
                "Integer porta ut sem at auctor. " +
                "Sed fermentum laoreet egestas. " +
                "Etiam faucibus ac quam eu tristique. " +
                "Sed mattis a nunc a congue. " +
                "Morbi sagittis bibendum nibh, " +
                "quis semper mi fringilla posuere.\n" +
                "\n" +
                "Morbi auctor dui erat, ac tincidunt mauris hendrerit quis. " +
                "Curabitur elementum diam mollis tortor suscipit, sed porta turpis porttitor. " +
                "Mauris tempor quam eget purus convallis tincidunt. " +
                "Mauris mattis gravida luctus. " +
                "Interdum et malesuada fames ac ante ipsum primis in faucibus. " +
                "Phasellus volutpat urna vitae mauris porttitor, ut vulputate mi tristique. " +
                "Curabitur ultrices justo eget erat efficitur, " +
                "vel lacinia metus scelerisque. " +
                "Aliquam id lacinia massa.\n" +
                "\n" +
                "Maecenas pretium varius sapien. " +
                "Curabitur dui quam, laoreet non malesuada sit amet, iaculis ut ante. " +
                "Curabitur turpis ligula, ullamcorper et magna ut, faucibus efficitur tellus. " +
                "Aenean id lectus dui. Nunc dapibus cursus ex sed aliquet. " +
                "Phasellus bibendum accumsan felis nec sollicitudin. " +
                "Suspendisse potenti. Nulla volutpat, augue eleifend aliquam feugiat, " +
                "purus urna tempor arcu, sed pretium mauris risus accumsan est. " +
                "Nam nunc neque, bibendum quis gravida eu, placerat et.";

        return new Tool(name, price, details, description);
    }

    public ArrayList<Tool> getNewTools(ToolType toolType, ToolPagerAdapter.Tab tab, int count){
        final ArrayList<Tool> results = new ArrayList<>(count);
        for (int i = 0; i < count; i++){
            results.add(getNewTool(toolType, tab));
        }

        return results;
    }


    private String getRandom(String[] strings){
        return strings[mRandom.nextInt(strings.length)];
    }
}
