package db;

import entities.Mission;
import java.util.HashMap;
import java.util.Map;
import java.util.Collection;

public class MissionDatabase {
    private Map<Integer, Mission> missionMap; 

    public MissionDatabase() {
        missionMap = new HashMap<>();
    }

    public void insertMission(Mission mission) {
        int missionId = missionMap.size() + 1; 
        mission.setMissionId(missionId); 
        missionMap.put(missionId, mission); 
    }

    public Mission getMissionById(int missionId) {
        return missionMap.get(missionId); 
    }

    public Mission getMissionByName(String name) {
        for (Mission mission : missionMap.values()) {
            if (mission.getMissionName().equalsIgnoreCase(name)) {
                return mission;
            }
        }
        return null; 
    }

    public Collection<Mission> getAllMissions() {
        return missionMap.values(); 
    }

    public void updateMission(Mission mission) {
        missionMap.put(mission.getMissionId(), mission); 
    }

    public void deleteMission(int missionId) {
        missionMap.remove(missionId); 
    }

    public boolean isMissionAvailable(String missionName) {
        for (Mission mission : missionMap.values()) {
            if (mission.getMissionName().equalsIgnoreCase(missionName)) {
                return true; 
            }
        }
        return false; 
    }

    public boolean isMissionAvailableById(int missionId) {
        return missionMap.containsKey(missionId);
    }
}
