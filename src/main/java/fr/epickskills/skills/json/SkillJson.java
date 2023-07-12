package fr.epickskills.skills.json;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;

public class SkillJson {
    private static String fileName = "world/epicka/configSkills.json";

    public static JSONObject loadJson() {
        JSONParser parser = new JSONParser();
        JSONObject jsonObject = null;
        try {
            jsonObject = (JSONObject) parser.parse(new FileReader(fileName));
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        assert jsonObject != null;
        return jsonObject;
    }

    //GET ANY PROP
    public static double getSkillProp(String classe, String skill, String level, String prop) {
        try {
            JSONObject jsonObject = loadJson();
            if (jsonObject.get(classe) instanceof JSONObject) {
                JSONObject classeObj = (JSONObject) jsonObject.get(classe);
                JSONObject skillObj = (JSONObject) classeObj.get(skill);
                JSONObject levelObj = (JSONObject) skillObj.get(level);
                return Double.parseDouble(String.valueOf(levelObj.get(prop)));
            }
        } catch (Exception e) {
            return 0D;
        }
        return 0D;
    }
}
