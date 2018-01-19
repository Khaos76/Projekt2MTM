package pl.edu.pg.eti.mtm.s169301.projekt_2.util;

import android.content.Context;
import android.content.res.Resources;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by Khaos on 18.01.2018.
 */

public class TextResourceReader {

    public static String readTextFileFromResource(Context context, int resourceId){
        StringBuilder body = new StringBuilder();

        try{
            InputStream inputStream = context.getResources().openRawResource(resourceId);
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

            String nextLine;

            while ((nextLine = bufferedReader.readLine()) != null){
                body.append(nextLine);
                body.append('\n');
            }
        }catch (IOException e){
            throw new RuntimeException(
                    "Nie można otwożyć zasobu: " + resourceId, e);
        }catch (Resources.NotFoundException nfe){
            throw new RuntimeException("Zasób niznaleziony: " + resourceId, nfe);
        }
        return body.toString();
    }
}
