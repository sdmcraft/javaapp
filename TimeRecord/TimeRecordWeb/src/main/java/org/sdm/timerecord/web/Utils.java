package org.sdm.timerecord.web;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Map;


public class Utils
{
    public static JSONObject mapToJSON(Map<String, Object> map) throws JSONException
    {
        JSONObject jsonObject = new JSONObject();

        for (Map.Entry<String, Object> entry : map.entrySet())
        {
            if (entry.getValue() instanceof String[])
            {
                JSONArray jsonArray = new JSONArray();

                for (String value : (String[]) entry.getValue())
                {
                    JSONObject obj = new JSONObject();
                    obj.put(entry.getKey(), value);
                    jsonArray.put(obj);
                }

                jsonObject.put(entry.getKey(), jsonArray);
            }
            else
            {
                jsonObject.put(entry.getKey(), entry.getValue().toString());
            }
        }

        return jsonObject;
    }
}
