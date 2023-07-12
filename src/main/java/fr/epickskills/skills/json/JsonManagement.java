package fr.epickskills.skills.json;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.util.Locale;
import java.util.UUID;

public class JsonManagement {
    public static String ReturnPath() {
        return "world/epicka/skills/";
    }


    public static JSONObject loadJson(UUID playerID) {
        JSONParser parser = new JSONParser();
        JSONObject jsonObject = null;
        try {
            jsonObject = (JSONObject) parser.parse(new FileReader(ReturnPath() + playerID + ".json"));
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        assert jsonObject != null;
        return jsonObject;
    }

    public static void overwriteJson(String playerName, UUID playerID, JSONObject jsonObject) {
        try (FileWriter file = new FileWriter(ReturnPath() + playerID + ".json")) {
            file.write(jsonObject.toJSONString());
            file.flush();
        } catch (IOException e) {
            WriteNewUserAccount(playerName, playerID);
            e.printStackTrace();
        }
    }


    public static void SearchUserAccount(String playerName, UUID playerID) {
        JSONParser parser = new JSONParser();
        String file = ReturnPath() + playerID + ".json";
        try {
            FileReader reader = new FileReader(file);
            File f = new File(file);

            JSONObject jsonObject = (JSONObject) parser.parse(reader);
            String name = String.valueOf(jsonObject.get("Player-Name"));

            if (!playerName.equals(name)) {
                ChangeName(playerName, playerID);
            }


        } catch (FileNotFoundException e) {
            WriteNewUserAccount(playerName, playerID);
        } catch (IOException e) {
            System.err.println("EPICKEXP : Fichier non lisible !");
        } catch (Exception e) {
            System.err.println("EPICKEXP : ERREUR !");
        }
    }


    public static void WriteNewUserAccount(String playerName, UUID playerID) {
        JSONObject obj = new JSONObject();
        String currentId = playerID.toString();
        obj.put("Player-Name", playerName);
        obj.put("UUID", currentId);
        obj.put("classe", "default");
        obj.put("skill1", 0);
        obj.put("skill2", 0);
        obj.put("skill3", 0);
        obj.put("ulti", 0);
        obj.put("pts", 0);

        try (FileWriter file = new FileWriter(ReturnPath() + currentId + ".json")) {
            file.write(obj.toJSONString());
            file.flush();
        } catch (IOException e) {
            System.err.println("EPICKEXP : Emplacement du fichier invalide !");
        }
    }

    public static void ChangeName(String name, UUID playerID) {
        JSONParser parser = new JSONParser();
        JSONObject obj = null;
        try {
            obj = (JSONObject) parser.parse(new FileReader(ReturnPath() + playerID + ".json"));
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        assert obj != null;
        obj.replace("PlayerName", name);
        try (FileWriter file = new FileWriter(ReturnPath() + playerID + ".json")) {
            file.write(obj.toJSONString());
            file.flush();
        } catch (IOException e) {
            JsonManagement.WriteNewUserAccount(name, playerID);
            e.printStackTrace();
        }
    }


    //SET CLASSE
    public static void setClasse(String playerName, UUID playerID, String newClasse) {
        JSONObject jsonObject = loadJson(playerID);
        jsonObject.replace("classe", newClasse.toLowerCase(Locale.ROOT));
        overwriteJson(playerName, playerID, jsonObject);
    }

    //GET CLASSE
    public static String getClasse(String playerName, UUID playerID) {
        JSONObject jsonObject = loadJson(playerID);
        return jsonObject.get("classe").toString().toLowerCase(Locale.ROOT);
    }


    //SET SKILL1
    public static void setSkill1(String playerName, UUID playerID, int state) {
        JSONObject jsonObject = loadJson(playerID);
        jsonObject.replace("skill1", state);
        overwriteJson(playerName, playerID, jsonObject);
    }

    //UPGRADE SKILL1
    public static void upgradeSkill1(String playerName, UUID playerID, int state) {
        JSONObject jsonObject = loadJson(playerID);
        jsonObject.replace("skill1", getSkill1(playerID) + state);
        overwriteJson(playerName, playerID, jsonObject);
    }

    //DOWNGRADE SKILL1
    public static void downgradeSkill1(String playerName, UUID playerID, int state) {
        JSONObject jsonObject = loadJson(playerID);
        jsonObject.replace("skill1", getSkill1(playerID) - state);
        overwriteJson(playerName, playerID, jsonObject);
    }

    //GET SKILL1
    public static int getSkill1(UUID playerID) {
        JSONObject jsonObject = loadJson(playerID);
        return Integer.parseInt(jsonObject.get("skill1").toString());
    }

    //SET SKILL2
    public static void setSkill2(String playerName, UUID playerID, int state) {
        JSONObject jsonObject = loadJson(playerID);
        jsonObject.replace("skill2", state);
        overwriteJson(playerName, playerID, jsonObject);
    }

    //UPGRADE SKILL2
    public static void upgradeSkill2(String playerName, UUID playerID, int state) {
        JSONObject jsonObject = loadJson(playerID);
        jsonObject.replace("skill2", getSkill2(playerID) + state);
        overwriteJson(playerName, playerID, jsonObject);
    }

    //DOWNGRADE SKILL2
    public static void downgradeSkill2(String playerName, UUID playerID, int state) {
        JSONObject jsonObject = loadJson(playerID);
        jsonObject.replace("skill2", getSkill3(playerID) - state);
        overwriteJson(playerName, playerID, jsonObject);
    }

    //GET SKILL3
    public static int getSkill3(UUID playerID) {
        JSONObject jsonObject = loadJson(playerID);
        return Integer.parseInt(jsonObject.get("skill3").toString());
    }

    //SET SKILL3
    public static void setSkill3(String playerName, UUID playerID, int state) {
        JSONObject jsonObject = loadJson(playerID);
        jsonObject.replace("skill3", state);
        overwriteJson(playerName, playerID, jsonObject);
    }

    //UPGRADE SKILL3
    public static void upgradeSkill3(String playerName, UUID playerID, int state) {
        JSONObject jsonObject = loadJson(playerID);
        jsonObject.replace("skill3", getSkill3(playerID) + state);
        overwriteJson(playerName, playerID, jsonObject);
    }

    //DOWNGRADE SKILL3
    public static void downgradeSkill3(String playerName, UUID playerID, int state) {
        JSONObject jsonObject = loadJson(playerID);
        jsonObject.replace("skill3", getSkill3(playerID) - state);
        overwriteJson(playerName, playerID, jsonObject);
    }

    //GET SKILL2
    public static int getSkill2(UUID playerID) {
        JSONObject jsonObject = loadJson(playerID);
        return Integer.parseInt(jsonObject.get("skill2").toString());
    }


    //SET ULTI
    public static void setUlti(String playerName, UUID playerID, int state) {
        JSONObject jsonObject = loadJson(playerID);
        jsonObject.replace("ulti", state);
        overwriteJson(playerName, playerID, jsonObject);
    }

    //UPGRADE Ulti
    public static void upgradeUlti(String playerName, UUID playerID, int state) {
        JSONObject jsonObject = loadJson(playerID);
        jsonObject.replace("ulti", getUlti(playerID) + state);
        overwriteJson(playerName, playerID, jsonObject);
    }

    //DOWNGRADE Ulti
    public static void downgradeUlti(String playerName, UUID playerID, int state) {
        JSONObject jsonObject = loadJson(playerID);
        jsonObject.replace("ulti", getUlti(playerID) - state);
        overwriteJson(playerName, playerID, jsonObject);
    }

    //GET ULTI
    public static int getUlti(UUID playerID) {
        JSONObject jsonObject = loadJson(playerID);
        return Integer.parseInt(String.valueOf(jsonObject.get("ulti")));
    }


    //ADD SKILL PT
    public static void addSkillPT(String playerName, UUID playerID, int amount) {
        JSONObject jsonObject = loadJson(playerID);
        jsonObject.replace("pts", getSkillPT(playerID) + amount);
        overwriteJson(playerName, playerID, jsonObject);
    }

    //REMOVE SKILL PT
    public static void removeSkillPT(String playerName, UUID playerID, int amount) {
        JSONObject jsonObject = loadJson(playerID);
        jsonObject.replace("pts", getSkillPT(playerID) - amount);
        overwriteJson(playerName, playerID, jsonObject);
    }

    //SET SKILL PT
    public static void setSkillPT(String playerName, UUID playerID, int amount) {
        JSONObject jsonObject = loadJson(playerID);
        jsonObject.replace("pts", amount);
        overwriteJson(playerName, playerID, jsonObject);
    }

    //GET SKILL PT
    public static int getSkillPT(UUID playerID) {
        JSONObject jsonObject = loadJson(playerID);
        return Integer.parseInt(String.valueOf(jsonObject.get("pts")));
    }

}
