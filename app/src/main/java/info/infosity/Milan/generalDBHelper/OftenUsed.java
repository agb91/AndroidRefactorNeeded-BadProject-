package info.infosity.Milan.generalDBHelper;

import java.util.Vector;

import info.infosity.Milan.Attrazioni;

/**
 * Created by andrea on 13/09/16.
 */
public class OftenUsed {

    public boolean inVector(String needle, Vector<String> haystack)
    {
        boolean risp = false;
        for(int i=0; i<haystack.size(); i++)
        {
            if( needle.equalsIgnoreCase ( haystack.get(i) ) )
            {
                risp = true;
            }
        }
        return risp;
    }

    public boolean inVector(Attrazioni needle, Vector<Attrazioni> haystack)
    {
        boolean risp = false;
        for(int i=0; i<haystack.size(); i++)
        {
            Attrazioni actual = haystack.get(i);
            if( needle.getName().equalsIgnoreCase ( actual.getName() ) )
            {
                risp = true;
            }
        }
        return risp;
    }

}
