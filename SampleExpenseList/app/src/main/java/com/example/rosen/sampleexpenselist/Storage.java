package com.example.rosen.sampleexpenselist;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rosen on 22.11.14.
 */
public class Storage {
    private static Storage sInstance;

    private List<ExpenceProduct> mExpenceList;

    private Storage(){
        mExpenceList = new ArrayList<ExpenceProduct>();
    }

    public static Storage getInstance(){
        if (sInstance == null){
            sInstance = new Storage();
        }
        return  sInstance;
    }
    public void remove(int index)
    {
        mExpenceList.remove(index);
    }
    public void saveExpenceProduct(ExpenceProduct expenceProduct){
        mExpenceList.add(expenceProduct);
    }

    public List<ExpenceProduct> getExpenceList(){
        return  mExpenceList;
    }
}
